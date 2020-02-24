package model_test

import (
	"bytes"
	"encoding/json"
	"testing"
	"time"

	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/product/model"
	"github.com/stretchr/testify/require"
)

func TestProductCreateRequest(t *testing.T) {
	type testCase struct {
		name           string
		testedProduct  model.ProductCreateRequest
		expectedResult string
	}
	testCases := []testCase{
		{
			"JSONWithCompleteDataFields",
			model.ProductCreateRequest{
				Data: &model.ProductCreateRequestData{
					SKU:         "Test-Product-SKU",
					Type:        "Test-Product-Type",
					Name:        "Test Product",
					Description: "This is a test product",
					IsTVOD:      true,
					IsRenewable: true,
					MetadataTVOD: &model.ProductMetadataTVOD{
						RentalPeriodHours:  720,
						ViewingPeriodHours: 48,
					},
					MetadataAds: &model.ProductMetadataAds{
						HasPrerollAds:      true,
						HasInterstitialAds: true,
						HasBannerAds:       true,
					},
				},
			},
			`{
	"data": {
		"sku": "Test-Product-SKU",
		"type": "Test-Product-Type",
		"name": "Test Product",
		"description": "This is a test product",
		"is_tvod": true,
		"is_renewable": true,
		"metadata_tvod": {
			"rental_period_hours": 720,
			"viewing_period_hours": 48
		},
		"metadata_ads": {
			"has_preroll_ads": true,
			"has_interstitial_ads": true,
			"has_banner_ads": true
		}
	}
}
`,
		},
		{
			"JSONWithIncompleteDataFields",
			model.ProductCreateRequest{
				Data: &model.ProductCreateRequestData{},
			},
			`{
	"data": {
		"sku": "",
		"type": "",
		"name": "",
		"description": "",
		"is_tvod": false,
		"is_renewable": false,
		"metadata_tvod": null,
		"metadata_ads": null
	}
}
`,
		},
		{
			"JSONWithoutData",
			model.ProductCreateRequest{},
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
			encoder.Encode(tc.testedProduct)
			require.Equal(t, tc.expectedResult, buffer.String())
		})
	}
}

func TestProductCreateRequestValidate(t *testing.T) {
	type testCase struct {
		name           string
		testedProduct  model.ProductCreateRequest
		expectedResult bool
	}
	testCases := []testCase{
		{
			"BareMinumum",
			model.ProductCreateRequest{
				Data: &model.ProductCreateRequestData{
					SKU:  "Test-Product-SKU",
					Type: "Test-Product-Type",
					Name: "Test Product",
				},
			},
			true,
		},
		{
			"WithoutSKU",
			model.ProductCreateRequest{
				Data: &model.ProductCreateRequestData{
					Type: "Test-Product-Type",
					Name: "Test Product",
				},
			},
			false,
		},
		{
			"WithoutType",
			model.ProductCreateRequest{
				Data: &model.ProductCreateRequestData{
					SKU:  "Test-Product-SKU",
					Name: "Test Product",
				},
			},
			false,
		},
		{
			"WithoutName",
			model.ProductCreateRequest{
				Data: &model.ProductCreateRequestData{
					SKU:  "Test-Product-SKU",
					Type: "Test-Product-Type",
				},
			},
			false,
		},
		{
			"WithoutData",
			model.ProductCreateRequest{},
			false,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			isValid := tc.testedProduct.Validate()
			require.Equal(t, tc.expectedResult, isValid)
		})
	}
}

func TestProductCreateResponse(t *testing.T) {
	id := uuid.New().String()
	now := time.Now().UTC()
	nowString := now.Format(time.RFC3339Nano)
	type testCase struct {
		name           string
		testedProduct  model.ProductCreateResponse
		expectedResult string
	}
	testCases := []testCase{
		{
			"JSONWithCompleteDataFields",
			model.ProductCreateResponse{
				Data: &model.Product{
					ID:          id,
					SKU:         "Test-Product-SKU",
					Type:        "Test-Product-Type",
					Name:        "Test Product",
					Description: "This is a test product",
					IsTVOD:      true,
					IsRenewable: true,
					MetadataTVOD: &model.ProductMetadataTVOD{
						RentalPeriodHours:  720,
						ViewingPeriodHours: 48,
					},
					MetadataAds: &model.ProductMetadataAds{
						HasPrerollAds:      true,
						HasInterstitialAds: true,
						HasBannerAds:       true,
					},
					InsertedAt: &now,
					UpdatedAt:  &now,
					DeletedAt:  &now,
				},
			},
			`{
	"data": {
		"id": "` + id + `",
		"sku": "Test-Product-SKU",
		"type": "Test-Product-Type",
		"name": "Test Product",
		"description": "This is a test product",
		"is_tvod": true,
		"is_renewable": true,
		"metadata_tvod": {
			"rental_period_hours": 720,
			"viewing_period_hours": 48
		},
		"metadata_ads": {
			"has_preroll_ads": true,
			"has_interstitial_ads": true,
			"has_banner_ads": true
		},
		"inserted_at": "` + nowString + `",
		"updated_at": "` + nowString + `",
		"deleted_at": "` + nowString + `"
	}
}
`,
		},
		{
			"JSONWithIncompleteDataFields",
			model.ProductCreateResponse{
				Data: &model.Product{},
			},
			`{
	"data": {
		"id": "",
		"sku": "",
		"type": "",
		"name": "",
		"description": "",
		"is_tvod": false,
		"is_renewable": false,
		"metadata_tvod": null,
		"metadata_ads": null,
		"inserted_at": null,
		"updated_at": null,
		"deleted_at": null
	}
}
`,
		},
		{
			"JSONWithoutData",
			model.ProductCreateResponse{},
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
			encoder.Encode(tc.testedProduct)
			require.Equal(t, tc.expectedResult, buffer.String())
		})
	}
}
