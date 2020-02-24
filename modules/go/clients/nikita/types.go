package nikita

import (
	"time"

	"github.com/hooqtv/monohooq/modules/go/tracing"
)

// ---------------------------------------------------------------------------------------------------------------------
// HTTP Body Request and response
// ---------------------------------------------------------------------------------------------------------------------

// AdminCreateSubscriptionRequest is sent to create subscription
type AdminCreateSubscriptionRequest struct {
	Data    *CreateSubscriptionData `json:"data"`
	Meta    *CreateSubscriptionMeta `json:"meta"`
	Tracing tracing.Tracing         `json:"-"`
}

// AdminCreateSubscriptionResponse received when subscription created
type AdminCreateSubscriptionResponse struct {
	Data    *Subscription   `json:"data"`
	Tracing tracing.Tracing `json:"-"`
}

// AdminCreateUserRequest is sent to create user
type AdminCreateUserRequest struct {
	Data    *CreateUserData `json:"data"`
	Tracing tracing.Tracing `json:"-"`
}

// AdminCreateUserResponse received when user created
type AdminCreateUserResponse struct {
	Data    *User           `json:"data"`
	Tracing tracing.Tracing `json:"-"`
}

// ---------------------------------------------------------------------------------------------------------------------
// Basic Types
// ---------------------------------------------------------------------------------------------------------------------

// CreateSubscriptionData model
type CreateSubscriptionData struct {
	TransactionID   string                       `json:"transaction_id"`
	Status          string                       `json:"status"`
	MetadataProduct *SubscriptionMetadataProduct `json:"metadata_product"`
	MetadataTVOD    *SubscriptionMetadataTVOD    `json:"metadata_tvod"`
	StartedAt       *time.Time                   `json:"started_at"`
	ExpiredAt       *time.Time                   `json:"expired_at"`
}

// CreateSubscriptionMeta model
type CreateSubscriptionMeta struct {
	LinkToUser       *LinkToUser `json:"link_to_user"`
	UpsertOnConflict bool        `json:"upsert_on_conflict"`
}

// CreateUserData model
type CreateUserData struct {
	Email             string `json:"email"`
	PhoneNumber       string `json:"phone_number"`
	Country           string `json:"country"`
	RefEvSpAccountID  string `json:"ref_ev_sp_account_id"`
	RefEvCpCustomerID string `json:"ref_ev_cp_customer_id"`
}

// LinkToUser provides link to user
type LinkToUser struct {
	ID               string `json:"id"`
	RefEvSpAccountID string `json:"ref_ev_sp_account_id"`
}

// SubscriptionMetadataProduct model
type SubscriptionMetadataProduct struct {
	SKU          string               `json:"sku"`
	IsTVOD       bool                 `json:"is_tvod"`
	MetadataTVOD *ProductMetadataTVOD `json:"metadata_tvod"`
	MetadataAds  *ProductMetadataAds  `json:"metadata_ads"`
}

// SubscriptionMetadataTVOD model
type SubscriptionMetadataTVOD struct {
	TitleID string `json:"title_id"`
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

// User is user object
type User struct {
	ID                string     `json:"id"`
	Email             string     `json:"email"`
	PhoneNumber       string     `json:"phone_number"`
	Country           string     `json:"country"`
	RefEvSpAccountID  string     `json:"ref_ev_sp_account_id"`
	RefEvCpCustomerID string     `json:"ref_ev_cp_customer_id"`
	InsertedAt        *time.Time `json:"inserted_at"`
	UpdatedAt         *time.Time `json:"updated_at"`
	DeletedAt         *time.Time `json:"deleted_at"`
}

// Subscription stores subscription
type Subscription struct {
	ID              string                       `json:"id"`
	UserID          string                       `json:"user_id"`
	TransactionID   string                       `json:"transaction_id"`
	Status          string                       `json:"status"`
	MetadataProduct *SubscriptionMetadataProduct `json:"metadata_product"`
	MetadataTVOD    *SubscriptionMetadataTVOD    `json:"metadata_tvod"`
	StartedAt       *time.Time                   `json:"started_at"`
	ExpiredAt       *time.Time                   `json:"expired_at"`
	InsertedAt      *time.Time                   `json:"inserted_at"`
	UpdatedAt       *time.Time                   `json:"updated_at"`
	DeletedAt       *time.Time                   `json:"deleted_at"`
}
