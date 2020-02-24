package model

import (
	"time"
)

// Product model
type Product struct {
	ID           string               `json:"id"`
	SKU          string               `json:"sku"`
	Type         string               `json:"type"`
	Name         string               `json:"name"`
	Description  string               `json:"description"`
	IsTVOD       bool                 `json:"is_tvod"`
	IsRenewable  bool                 `json:"is_renewable"`
	MetadataTVOD *ProductMetadataTVOD `json:"metadata_tvod"`
	MetadataAds  *ProductMetadataAds  `json:"metadata_ads"`
	InsertedAt   *time.Time           `json:"inserted_at"`
	UpdatedAt    *time.Time           `json:"updated_at"`
	DeletedAt    *time.Time           `json:"deleted_at"`
}

// ProductMetadataTVOD model
type ProductMetadataTVOD struct {
	RentalPeriodHours  int `json:"rental_period_hours"`
	ViewingPeriodHours int `json:"viewing_period_hours"`
}

// ProductMetadataAds model
type ProductMetadataAds struct {
	HasPrerollAds      bool `json:"has_preroll_ads"`
	HasInterstitialAds bool `json:"has_interstitial_ads"`
	HasBannerAds       bool `json:"has_banner_ads"`
}

// ProductCreateRequest models product creation request body
type ProductCreateRequest struct {
	Data *ProductCreateRequestData `json:"data"`
}

// ProductCreateRequestData models product creation data
type ProductCreateRequestData struct {
	SKU          string               `json:"sku"`
	Type         string               `json:"type"`
	Name         string               `json:"name"`
	Description  string               `json:"description"`
	IsTVOD       bool                 `json:"is_tvod"`
	IsRenewable  bool                 `json:"is_renewable"`
	MetadataTVOD *ProductMetadataTVOD `json:"metadata_tvod"`
	MetadataAds  *ProductMetadataAds  `json:"metadata_ads"`
}

// ProductCreateResponse models product creation response body
type ProductCreateResponse struct {
	Data *Product `json:"data"`
}

// ProductGetResponse models product get response body
type ProductGetResponse ProductCreateResponse

// Validate ProductCreateRequest
func (req *ProductCreateRequest) Validate() bool {
	return req.Data != nil &&
		req.Data.SKU != "" &&
		req.Data.Type != "" &&
		req.Data.Name != ""
}
