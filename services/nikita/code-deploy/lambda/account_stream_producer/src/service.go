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
	"github.com/hooqtv/monohooq/modules/go/events/account"
	"github.com/hooqtv/monohooq/modules/go/tracing"
	"github.com/sirupsen/logrus"
)

type service struct {
	kinesis  kinesisiface.KinesisAPI
	evergent evergent.Client

	appInfo      *AppInfo
	evergentInfo *EvergentInfo
	kinesisInfo  *KinesisInfo
}

func (h service) context(event AccountEvent) *context {
	return &context{
		service: h,
		eventIn: event,
		tracing: tracing.Metadata{
			RequestID: uuid.New().String(),
			Timestamp: time.Now().UTC().Truncate(time.Millisecond),
		},
	}
}

func (h service) HandleEvent(event AccountEvent) (AccountEventReceipt, error) {
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
	eventIn AccountEvent

	// outputs
	eventOut       *account.Data
	kinesisShardID string
	userDetail     *evergent.GetUserDetailsResponse
	tracing        tracing.Metadata
}

func (ctx *context) readFromEvergent() *errors.Error {
	// get user detail
	resp, err := ctx.service.evergent.GetUserDetails(evergent.GetUserDetailsRequest{SpAccountID: ctx.eventIn.SpAccountID})
	if err != nil {
		var err1 *errors.Error
		if casted, ok := err.(evergent.ErrorResponse); ok {
			err1 = errors.New(casted.Error(), ErrCodeNikitaLambdaAccountProducerReadEvergentFailure)
		} else {
			err1 = errors.New("failed to parse error message", ErrCodeNikitaLambdaAccountProducerReadEvergentFailure)
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
		}).Error("failed to fetch user detail")

		return err1
	}

	ctx.userDetail = resp
	logrus.WithFields(logrus.Fields{
		"app":         ctx.service.appInfo,
		"kinesis":     ctx.service.kinesisInfo,
		"evergent":    ctx.service.evergentInfo,
		"event_in":    ctx.eventIn,
		"user_detail": ctx.userDetail,
	}).Info("fetched entitlement successfully")
	return nil
}

func (ctx *context) writeToKinesis() *errors.Error {
	// compose full event payload
	fullEventOut := ctx.kinesisPayload()
	ctx.eventOut = &fullEventOut.Data

	// decode payload
	buff := bytes.NewBuffer(nil)
	if err := json.NewEncoder(buff).Encode(fullEventOut); err != nil {
		msg := "failed to encode kinesis payload"
		err1 := errors.Wrap(err, msg, ErrCodeNikitaLambdaAccountProducerWriteKinesisFailure)
		logrus.WithFields(logrus.Fields{
			"app":         ctx.service.appInfo,
			"kinesis":     ctx.service.kinesisInfo,
			"evergent":    ctx.service.evergentInfo,
			"event_in":    ctx.eventIn,
			"event_out":   fullEventOut,
			"user_detail": ctx.userDetail,
			"error":       map[string]interface{}{
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
		err1 := errors.Wrap(err, msg, ErrCodeNikitaLambdaAccountProducerWriteKinesisFailure)
		logrus.WithFields(logrus.Fields{
			"app":         ctx.service.appInfo,
			"kinesis":     ctx.service.kinesisInfo,
			"evergent":    ctx.service.evergentInfo,
			"event_in":    ctx.eventIn,
			"event_out":   fullEventOut,
			"user_detail": ctx.userDetail,
			"error":       map[string]interface{}{
				"code":    err1.Code,
				"message": err1.Message,
			},
		}).Error(msg)
		return err1
	}

	// record shard_id
	ctx.kinesisShardID = aws.StringValue(output.ShardId)

	logrus.WithFields(logrus.Fields{
		"app":         ctx.service.appInfo,
		"kinesis":     ctx.service.kinesisInfo,
		"evergent":    ctx.service.evergentInfo,
		"event_in":    ctx.eventIn,
		"event_out":   fullEventOut,
		"user_detail": ctx.userDetail,
		"shard_id":    ctx.kinesisShardID,
	}).Info("written to kinesis stream")
	return nil
}

func (ctx context) kinesisPayload() account.CreatedEvent {
	return account.CreatedEvent{
		Data: account.Data{
			RefEvSpAccountID: ctx.userDetail.SpAccountID,
			RevEvCpAccountID: ctx.userDetail.CpCustomerID,
			Email:            ctx.userDetail.Email,
			PhoneNumber:      ctx.userDetail.PhoneNumber,
			Country:          ctx.userDetail.Country,
			Status:           ctx.userDetail.Status,
		},
		Metadata: ctx.tracing,
	}
}

func (ctx context) returnError(err *errors.Error) (AccountEventReceipt, error) {
	return AccountEventReceipt{
		Error: err,
		Meta:  ctx.tracing,
	}, err
}

func (ctx context) returnSuccess() (AccountEventReceipt, error) {
	return AccountEventReceipt{
		Data: ctx.eventOut,
		Meta: ctx.tracing,
	}, nil
}
