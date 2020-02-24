package main

import (
	"os"
	"testing"
	"time"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/kinesis"
	"github.com/aws/aws-sdk-go/service/kinesis/kinesisiface"
	"github.com/hooqtv/monohooq/modules/go/clients/evergent"
	"github.com/hooqtv/monohooq/modules/go/constant"
	"github.com/sirupsen/logrus"
	"github.com/stretchr/testify/require"
)

type mockKinesis struct {
	kinesisiface.KinesisAPI

	PutRecordRes *kinesis.PutRecordOutput
	PutRecordErr error
}

func (m mockKinesis) PutRecord(*kinesis.PutRecordInput) (*kinesis.PutRecordOutput, error) {
	return m.PutRecordRes, m.PutRecordErr
}

func TestMain(m *testing.M) {
	logrus.SetFormatter(&logrus.JSONFormatter{})
	logrus.SetOutput(os.Stdout)
	logrus.SetLevel(logrus.DebugLevel)

	os.Exit(m.Run())
}

func TestService_HandleEvent(t *testing.T) {
	mock := mockKinesis{PutRecordRes: &kinesis.PutRecordOutput{ShardId: aws.String("1")}}

	now := time.Now().UTC()
	evergentMock := evergent.Mock{}
	evergentMock.On("GetEntitlements", evergent.GetEntitlementsRequest{SpAccountID: "sp_account_id"}).
		Return(&evergent.GetEntitlementsResponse{
			Entitlements: []evergent.Entitlement{{
				ServiceID:     "service_id",
				ServiceName:   "service_name",
				Name:          "name",
				Description:   "description",
				TypeOfService: "type_of_service",
				Status:        "status",
				StartedAt:     aws.Time(now),
				ExpiredAt:     aws.Time(now),
				IsRenewable:   false,
				MetadataAds: &evergent.EntitlementMetadataAds{
					HasPrerollAds:      false,
					HasInterstitialAds: false,
					HasBannerAds:       false,
				},
				MetadataVod: &evergent.EntitlementMetadataVod{
					TitleID:            "title_id",
					Title:              "title",
					ViewingPeriodHours: 1,
					RentalPeriodHours:  2,
				},
			}},
		}, nil)

	svc := service{
		kinesis:  mock,
		evergent: evergentMock,
		appInfo: &AppInfo{
			Env: "test",
			Tag: constant.AppTag(),
		},
		kinesisInfo: &KinesisInfo{
			StreamName:   "stream_name",
			PartitionKey: "partition_key",
		},
		evergentInfo: &EvergentInfo{
			BaseURL:   "base_url",
			Namespace: "namespace",
			PartnerID: "partner_id",
			User:      "user",
			Password:  "password",
		}}
	_, err := svc.HandleEvent(TransactionEvent{SpAccountID: "sp_account_id"})
	require.NoError(t, err)
	evergentMock.AssertExpectations(t)
}
