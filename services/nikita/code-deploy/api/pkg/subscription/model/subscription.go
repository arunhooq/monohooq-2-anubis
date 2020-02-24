package model

import (
	"time"

	productModel "github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/product/model"
)

// Subscription model
type Subscription struct {
	ID              string                       `json:"id"`
	UserID          string                       `json:"user_id"`
	Status          string                       `json:"status"`
	MetadataProduct *SubscriptionMetadataProduct `json:"metadata_product"`
	MetadataTVOD    *SubscriptionMetadataTVOD    `json:"metadata_tvod"`
	StartedAt       *time.Time                   `json:"started_at"`
	ExpiredAt       *time.Time                   `json:"expired_at"`
	InsertedAt      *time.Time                   `json:"inserted_at"`
	UpdatedAt       *time.Time                   `json:"updated_at"`
	DeletedAt       *time.Time                   `json:"deleted_at"`
}

// SubscriptionMetadataProduct model
type SubscriptionMetadataProduct struct {
	SKU          string                            `json:"sku"`
	IsTVOD       bool                              `json:"is_tvod"`
	MetadataTVOD *productModel.ProductMetadataTVOD `json:"metadata_tvod"`
	MetadataAds  *productModel.ProductMetadataAds  `json:"metadata_ads"`
}

// SubscriptionMetadataTVOD model
type SubscriptionMetadataTVOD struct {
	TitleID string `json:"title_id"`
}

// SubscriptionCreateRequest models subscription creation request body
type SubscriptionCreateRequest struct {
	Data *SubscriptionCreateRequestData `json:"data"`
	Meta *SubscriptionCreateRequestMeta `json:"meta"`
}

// SubscriptionCreateRequestData models subscription creation data
type SubscriptionCreateRequestData struct {
	Status          string                       `json:"status"`
	MetadataProduct *SubscriptionMetadataProduct `json:"metadata_product"`
	MetadataTVOD    *SubscriptionMetadataTVOD    `json:"metadata_tvod"`
	StartedAt       *time.Time                   `json:"started_at"`
	ExpiredAt       *time.Time                   `json:"expired_at"`
}

// SubscriptionCreateRequestMeta models subscription creation meta
type SubscriptionCreateRequestMeta struct {
	LinkToUser *SubscriptionCreateRequestMetaLinkToUser `json:"link_to_user"`
}

// SubscriptionCreateRequestMetaLinkToUser models subscription creation meta link to user
type SubscriptionCreateRequestMetaLinkToUser struct {
	ID               string `json:"id"`
	RefEvSpAccountID string `json:"ref_ev_sp_account_id"`
}

// SubscriptionCreateResponse models subscription creation response body
type SubscriptionCreateResponse struct {
	Data *Subscription `json:"data"`
}

// SubscriptionGetResponse models subscription get response body
type SubscriptionGetResponse struct {
	Data *Subscription `json:"data"`
}

// SubscriptionGetManyResponse models subscription get many response body
type SubscriptionGetManyResponse struct {
	Data []Subscription `json:"data"`
}

// Validate SubscriptionCreateRequest
func (req *SubscriptionCreateRequest) Validate() bool {
	return req.Data != nil &&
		req.Data.Status != "" &&
		req.Data.MetadataProduct != nil &&
		req.Data.MetadataProduct.SKU != "" &&
		req.Data.StartedAt != nil &&
		req.Data.ExpiredAt != nil &&
		req.Meta.LinkToUser != nil &&
		(req.Meta.LinkToUser.ID != "" || req.Meta.LinkToUser.RefEvSpAccountID != "")
}
