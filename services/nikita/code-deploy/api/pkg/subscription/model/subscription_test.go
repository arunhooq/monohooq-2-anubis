package model_test

import (
	"bytes"
	"encoding/json"
	"testing"
	"time"

	"github.com/google/uuid"
	productModel "github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/product/model"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/subscription/model"
	"github.com/stretchr/testify/require"
)

func TestSubscriptionCreateRequest(t *testing.T) {
	id := uuid.New().String()
	now := time.Now().UTC()
	nowString := now.Format(time.RFC3339Nano)
	type testCase struct {
		name               string
		testedSubscription model.SubscriptionCreateRequest
		expectedResult     string
	}
	testCases := []testCase{
		{
			"JSONWithCompleteDataMetaFields",
			model.SubscriptionCreateRequest{
				Data: &model.SubscriptionCreateRequestData{
					Status: "Test-Subscription-Status",
					MetadataTVOD: &model.SubscriptionMetadataTVOD{
						TitleID: "test-title-id",
					},
					MetadataProduct: &model.SubscriptionMetadataProduct{
						SKU:    "test-product-sku",
						IsTVOD: true,
						MetadataTVOD: &productModel.ProductMetadataTVOD{
							RentalPeriodHours:  720,
							ViewingPeriodHours: 48,
						},
						MetadataAds: &productModel.ProductMetadataAds{
							HasPrerollAds:      true,
							HasInterstitialAds: true,
							HasBannerAds:       true,
						},
					},
					StartedAt: &now,
					ExpiredAt: &now,
				},
				Meta: &model.SubscriptionCreateRequestMeta{
					LinkToUser: &model.SubscriptionCreateRequestMetaLinkToUser{
						ID:               id,
						RefEvSpAccountID: "test-sp-account-id",
					},
				},
			},
			`{
	"data": {
		"status": "Test-Subscription-Status",
		"metadata_product": {
			"sku": "test-product-sku",
			"is_tvod": true,
			"metadata_tvod": {
				"rental_period_hours": 720,
				"viewing_period_hours": 48
			},
			"metadata_ads": {
				"has_preroll_ads": true,
				"has_interstitial_ads": true,
				"has_banner_ads": true
			}
		},
		"metadata_tvod": {
			"title_id": "test-title-id"
		},
		"started_at": "` + nowString + `",
		"expired_at": "` + nowString + `"
	},
	"meta": {
		"link_to_user": {
			"id": "` + id + `",
			"ref_ev_sp_account_id": "test-sp-account-id"
		}
	}
}
`,
		},
		{
			"JSONWithIncompleteDataMetaFields",
			model.SubscriptionCreateRequest{
				Data: &model.SubscriptionCreateRequestData{},
				Meta: &model.SubscriptionCreateRequestMeta{},
			},
			`{
	"data": {
		"status": "",
		"metadata_product": null,
		"metadata_tvod": null,
		"started_at": null,
		"expired_at": null
	},
	"meta": {
		"link_to_user": null
	}
}
`,
		},
		{
			"JSONWithoutDataMeta",
			model.SubscriptionCreateRequest{},
			`{
	"data": null,
	"meta": null
}
`,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			var buffer bytes.Buffer
			encoder := json.NewEncoder(&buffer)
			encoder.SetIndent("", "\t")
			encoder.Encode(tc.testedSubscription)
			require.Equal(t, tc.expectedResult, buffer.String())
		})
	}
}

func TestSubscriptionCreateRequestValidate(t *testing.T) {
	id := uuid.New().String()
	now := time.Now().UTC()
	type testCase struct {
		name               string
		testedSubscription model.SubscriptionCreateRequest
		expectedResult     bool
	}
	testCases := []testCase{
		{
			"BareMinumum",
			model.SubscriptionCreateRequest{
				Data: &model.SubscriptionCreateRequestData{
					Status: "test-status",
					MetadataProduct: &model.SubscriptionMetadataProduct{
						SKU: "test-product-sku",
					},
					StartedAt: &now,
					ExpiredAt: &now,
				},
				Meta: &model.SubscriptionCreateRequestMeta{
					LinkToUser: &model.SubscriptionCreateRequestMetaLinkToUser{
						ID: id,
					},
				},
			},
			true,
		},
		{
			"BareMinumumWithLinkToUserBySpAccountID",
			model.SubscriptionCreateRequest{
				Data: &model.SubscriptionCreateRequestData{
					Status: "test-status",
					MetadataProduct: &model.SubscriptionMetadataProduct{
						SKU: "test-sku",
					},
					StartedAt: &now,
					ExpiredAt: &now,
				},
				Meta: &model.SubscriptionCreateRequestMeta{
					LinkToUser: &model.SubscriptionCreateRequestMetaLinkToUser{
						RefEvSpAccountID: "test-sp-account-id",
					},
				},
			},
			true,
		},
		{
			"WithoutData",
			model.SubscriptionCreateRequest{},
			false,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			isValid := tc.testedSubscription.Validate()
			require.Equal(t, tc.expectedResult, isValid)
		})
	}
}

func TestSubscriptionCreateResponse(t *testing.T) {
	id := uuid.New().String()
	now := time.Now().UTC()
	nowString := now.Format(time.RFC3339Nano)
	type testCase struct {
		name               string
		testedSubscription model.SubscriptionCreateResponse
		expectedResult     string
	}
	testCases := []testCase{
		{
			"JSONWithCompleteDataFields",
			model.SubscriptionCreateResponse{
				Data: &model.Subscription{
					ID:     id,
					UserID: id,
					Status: "Test-Subscription-Status",
					MetadataTVOD: &model.SubscriptionMetadataTVOD{
						TitleID: "test-title-id",
					},
					MetadataProduct: &model.SubscriptionMetadataProduct{
						SKU:    "test-product-sku",
						IsTVOD: true,
						MetadataTVOD: &productModel.ProductMetadataTVOD{
							RentalPeriodHours:  720,
							ViewingPeriodHours: 48,
						},
						MetadataAds: &productModel.ProductMetadataAds{
							HasPrerollAds:      true,
							HasInterstitialAds: true,
							HasBannerAds:       true,
						},
					},
					StartedAt:  &now,
					ExpiredAt:  &now,
					InsertedAt: &now,
					UpdatedAt:  &now,
					DeletedAt:  &now,
				},
			},
			`{
	"data": {
		"id": "` + id + `",
		"user_id": "` + id + `",
		"status": "Test-Subscription-Status",
		"metadata_product": {
			"sku": "test-product-sku",
			"is_tvod": true,
			"metadata_tvod": {
				"rental_period_hours": 720,
				"viewing_period_hours": 48
			},
			"metadata_ads": {
				"has_preroll_ads": true,
				"has_interstitial_ads": true,
				"has_banner_ads": true
			}
		},
		"metadata_tvod": {
			"title_id": "test-title-id"
		},
		"started_at": "` + nowString + `",
		"expired_at": "` + nowString + `",
		"inserted_at": "` + nowString + `",
		"updated_at": "` + nowString + `",
		"deleted_at": "` + nowString + `"
	}
}
`,
		},
		{
			"JSONWithIncompleteDataFields",
			model.SubscriptionCreateResponse{
				Data: &model.Subscription{},
			},
			`{
	"data": {
		"id": "",
		"user_id": "",
		"status": "",
		"metadata_product": null,
		"metadata_tvod": null,
		"started_at": null,
		"expired_at": null,
		"inserted_at": null,
		"updated_at": null,
		"deleted_at": null
	}
}
`,
		},
		{
			"JSONWithoutData",
			model.SubscriptionCreateResponse{},
			`{
	"data": null
}
`,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			var buffer bytes.Buffer
			encoder := json.NewEncoder(&buffer)
			encoder.SetIndent("", "\t")
			encoder.Encode(tc.testedSubscription)
			require.Equal(t, tc.expectedResult, buffer.String())
		})
	}
}
