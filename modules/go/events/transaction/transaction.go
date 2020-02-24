package transaction

import (
	"time"

	"github.com/hooqtv/monohooq/modules/go/tracing"
)

// CreatedEvent is the event consumed from kinesis
type CreatedEvent struct {
	Data    Data            `json:"data"`
	Tracing tracing.Tracing `json:"meta"`
}

// Data emitted when user entitlement changed
type Data struct {
	User         User         `json:"user"`
	Product      Product      `json:"product"`
	Subscription Subscription `json:"subscription"`
}

// User stores user identifiers
type User struct {
	ID          string `json:"id"`
	SpAccountID string `json:"sp_account_id"`
}

// Product stores product information
type Product struct {
	SKU          string               `json:"sku"`
	Name         string               `json:"name"`
	Description  string               `json:"description"`
	Type         string               `json:"type"`
	IsRenewable  bool                 `json:"is_renewable"`
	MetadataTVOD *ProductMetadataTVOD `json:"metadata_tvod"`
	MetadataAds  *ProductMetadataAds  `json:"metadata_ads"`
}

// Subscription stores subscription info
type Subscription struct {
	TransactionID string                    `json:"transaction_id"`
	Status        string                    `json:"status"`
	StartedAt     *time.Time                `json:"started_at"`
	ExpiredAt     *time.Time                `json:"expired_at"`
	MetadataTVOD  *SubscriptionMetadataTVOD `json:"metadata_tvod"`
}

// ProductMetadataTVOD stores TVOD metadata
type ProductMetadataTVOD struct {
	RentalPeriodHours  int `json:"rental_period_hours"`
	ViewingPeriodHours int `json:"viewing_period_hours"`
}

// ProductMetadataAds stores ads metadata
type ProductMetadataAds struct {
	HasPrerollAds      bool `json:"has_preroll_ads"`
	HasInterstitialAds bool `json:"has_interstitial_ads"`
	HasBannerAds       bool `json:"has_banner_ads"`
}

// SubscriptionMetadataTVOD stores tvod metadata
type SubscriptionMetadataTVOD struct {
	TitleID string `json:"title_id"`
}
