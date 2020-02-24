package main

import (
	"bytes"
	"encoding/json"
	"strconv"
	"time"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/kinesis"
	"github.com/aws/aws-sdk-go/service/kinesis/kinesisiface"
	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/modules/go/clients/evergent"
	"github.com/hooqtv/monohooq/modules/go/errors"
	"github.com/hooqtv/monohooq/modules/go/events/transaction"
	"github.com/hooqtv/monohooq/modules/go/tracing"
	"github.com/sirupsen/logrus"
)

type service struct {
	kinesis  kinesisiface.KinesisAPI
	evergent evergent.Client

	appInfo      *AppInfo
	kinesisInfo  *KinesisInfo
	evergentInfo *EvergentInfo
}

func (h service) context(event TransactionEvent) *context {
	return &context{
		service: h,
		eventIn: event,
		tracing: tracing.Metadata{
			RequestID: uuid.New().String(),
			Timestamp: time.Now().UTC().Truncate(time.Millisecond),
		},
	}
}

func (h service) HandleEvent(event TransactionEvent) (TransactionEventReceipt, error) {
	ctx := h.context(event)

	// read from evergent
	if err := ctx.readFromEvergent(); err != nil {
		return ctx.returnError(err)
	}

	// write to kinesis
	if err := ctx.writeToKinesis(); err != nil {
		return ctx.returnError(err)
	}

	return ctx.returnSuccess()
}

type context struct {
	service service

	// input
	eventIn TransactionEvent

	// outputs
	eventOut            []transaction.Data
	kinesisShardIDsList []string
	entitlements        []evergent.Entitlement
	tracing             tracing.Metadata
}

func (ctx *context) readFromEvergent() *errors.Error {
	// get user detail
	resp, err := ctx.service.evergent.GetEntitlements(evergent.GetEntitlementsRequest{SpAccountID: ctx.eventIn.SpAccountID})
	if err != nil {
		var err1 *errors.Error
		if casted, ok := err.(evergent.ErrorResponse); ok {
			err1 = errors.New(casted.Error(), ErrCodeNikitaLambdaTransactionProducerReadEvergentFailure)
		} else {
			err1 = errors.New("failed to parse error message", ErrCodeNikitaLambdaTransactionProducerReadEvergentFailure)
		}

		logrus.WithFields(logrus.Fields{
			"app":      ctx.service.appInfo,
			"kinesis":  ctx.service.kinesisInfo,
			"evergent": ctx.service.evergentInfo,
			"event_in": ctx.eventIn,
			"error": map[string]interface{}{
				"code":    err1.Code,
				"message": err1.Message,
			},
		}).Error("failed to fetch entitlement")

		return err1
	}

	ctx.entitlements = resp.Entitlements
	logrus.WithFields(logrus.Fields{
		"app":          ctx.service.appInfo,
		"kinesis":      ctx.service.kinesisInfo,
		"evergent":     ctx.service.evergentInfo,
		"event_in":     ctx.eventIn,
		"entitlements": ctx.entitlements,
	}).Info("fetched entitlement successfully")

	return nil
}

func (ctx *context) writeToKinesis() *errors.Error {
	if len(ctx.entitlements) == 0 {
		logrus.WithFields(logrus.Fields{
			"app":          ctx.service.appInfo,
			"kinesis":      ctx.service.kinesisInfo,
			"evergent":     ctx.service.evergentInfo,
			"event_in":     ctx.eventIn,
			"entitlements": ctx.entitlements,
		}).Info("user has no entitlement")
		return nil
	}

	for _, entitlement := range ctx.entitlements {
		// compose full event payload
		fullEventOut := ctx.kinesisPayload(entitlement)
		ctx.eventOut = append(ctx.eventOut, fullEventOut.Data)

		// encode kinesis payload
		buff := bytes.NewBuffer(nil)
		if err := json.NewEncoder(buff).Encode(fullEventOut); err != nil {
			msg := "failed to encode kinesis payload"
			err1 := errors.Wrap(err, msg, ErrCodeNikitaLambdaTransactionAccountProducerWriteKinesisFailure)
			logrus.WithFields(logrus.Fields{
				"app":         ctx.service.appInfo,
				"kinesis":     ctx.service.kinesisInfo,
				"evergent":    ctx.service.evergentInfo,
				"event_in":    ctx.eventIn,
				"event_out":   fullEventOut,
				"entitlement": entitlement,
				"error": map[string]interface{}{
					"code":    err1.Code,
					"message": err1.Message,
				},
			}).Error(msg)

			return err1
		}

		input := kinesis.PutRecordInput{
			StreamName:                aws.String(ctx.service.kinesisInfo.StreamName),
			PartitionKey:              aws.String(ctx.service.kinesisInfo.PartitionKey),
			SequenceNumberForOrdering: aws.String(strconv.FormatInt(time.Now().UnixNano(), 10)),
			Data:                      buff.Bytes(),
		}

		output, err := ctx.service.kinesis.PutRecord(&input)
		if err != nil {
			msg := "failed to write to kinesis stream"
			err1 := errors.Wrap(err, msg, ErrCodeNikitaLambdaTransactionAccountProducerWriteKinesisFailure)
			logrus.WithFields(logrus.Fields{
				"app":         ctx.service.appInfo,
				"kinesis":     ctx.service.kinesisInfo,
				"evergent":    ctx.service.evergentInfo,
				"event_in":    ctx.eventIn,
				"event_out":   fullEventOut,
				"entitlement": entitlement,
				"error":       map[string]interface{}{
					"code":    err1.Code,
					"message": err1.Message,
				},
			}).Error(msg)

			return err1
		}

		// record shard_id
		shardID := aws.StringValue(output.ShardId)
		ctx.kinesisShardIDsList = append(ctx.kinesisShardIDsList, shardID)
		logrus.WithFields(logrus.Fields{
			"app":         ctx.service.appInfo,
			"kinesis":     ctx.service.kinesisInfo,
			"evergent":    ctx.service.evergentInfo,
			"event_in":    ctx.eventIn,
			"event_out":   fullEventOut,
			"entitlement": entitlement,
			"shard_id":    shardID,
		}).Info("written to kinesis stream")
	}

	return nil
}

func (ctx context) returnError(err *errors.Error) (TransactionEventReceipt, error) {
	return TransactionEventReceipt{
		Error: err,
		Meta:  ctx.tracing,
	}, err
}

func (ctx context) returnSuccess() (TransactionEventReceipt, error) {
	return TransactionEventReceipt{
		ShardIDs: ctx.kinesisShardIDsList,
		Data:     ctx.eventOut,
		Meta:     ctx.tracing,
	}, nil
}

func (ctx context) kinesisPayload(entitlement evergent.Entitlement) transaction.CreatedEvent {
	fullEvent := transaction.CreatedEvent{
		Data: transaction.Data{
			User: transaction.User{
				SpAccountID: ctx.eventIn.SpAccountID,
			},
			Product: transaction.Product{
				SKU:         entitlement.ServiceID,
				Name:        entitlement.ServiceName,
				Description: entitlement.Description,
				Type:        entitlement.TypeOfService,
				IsRenewable: entitlement.IsRenewable,
			},
			Subscription: transaction.Subscription{
				StartedAt: entitlement.StartedAt,
				ExpiredAt: entitlement.ExpiredAt,
			},
		},
		Meta: ctx.tracing,
	}

	// fill ads metadata
	if entitlement.MetadataAds != nil {
		metadata := entitlement.MetadataAds
		fullEvent.Data.Product.MetadataAds = &transaction.ProductMetadataAds{
			HasPrerollAds:      metadata.HasPrerollAds,
			HasInterstitialAds: metadata.HasInterstitialAds,
			HasBannerAds:       metadata.HasBannerAds,
		}
	}

	// fill TVOD metadata
	if entitlement.MetadataVod != nil {
		metadata := entitlement.MetadataVod
		fullEvent.Data.Product.MetadataTVOD = &transaction.ProductMetadataTVOD{
			RentalPeriodHours:  metadata.RentalPeriodHours,
			ViewingPeriodHours: metadata.ViewingPeriodHours,
		}

		fullEvent.Data.Subscription.MetadataTVOD = &transaction.SubscriptionMetadataTVOD{
			TitleID: metadata.TitleID,
		}
	}

	return fullEvent
}
