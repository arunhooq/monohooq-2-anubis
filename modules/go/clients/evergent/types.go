package evergent

import (
	"strings"
	"time"
)

// GetUserDetailsRequest stores get user-detail request
type GetUserDetailsRequest struct {
	SpAccountID string `json:"sp_account_id"`
}

// GetUserDetailsResponse stores get user-detail response
type GetUserDetailsResponse struct {
	IsAnonymized bool   `json:"is_anonymized"`
	CpCustomerID string `json:"cp_customer_id"`
	SpAccountID  string `json:"sp_account_id"`
	Username     string `json:"username"`
	FirstName    string `json:"first_name"`
	LastName     string `json:"last_name"`
	Email        string `json:"email"`
	PhoneNumber  string `json:"phone_number"`
	Country      string `json:"country"`
}

// GetEntitlementsRequest stores get entitlement request
type GetEntitlementsRequest struct {
	SpAccountID string `json:"sp_account_id"`
}

// GetEntitlementsResponse stores get entitlement response
type GetEntitlementsResponse struct {
	Entitlements []Entitlement `json:"entitlements"`
}

// Entitlement stores entitlement data
type Entitlement struct {
	TransactionID string                   `json:"transaction_id"`
	ServiceID     string                   `json:"service_id"`
	ServiceName   string                   `json:"service_name"`
	Name          string                   `json:"name"`
	Description   string                   `json:"description"`
	TypeOfService string                   `json:"type_of_service"`
	Status        string                   `json:"status"`
	StartedAt     *time.Time               `json:"started_at"`
	ExpiredAt     *time.Time               `json:"expired_at"`
	IsRenewable   bool                     `json:"is_renewable"`
	MetadataTvod  *EntitlementMetadataTvod `json:"metadata_tvod"`
	MetadataAds   *EntitlementMetadataAds  `json:"metadata_ads"`
}

// EntitlementMetadataTvod stores TVOD metadata
type EntitlementMetadataTvod struct {
	TitleID            string `json:"title_id"`
	Title              string `json:"title"`
	ViewingPeriodHours int    `json:"viewing_period_hours"`
	RentalPeriodHours  int    `json:"rental_period_hours"`
}

// EntitlementMetadataAds stores ads metadata
type EntitlementMetadataAds struct {
	HasPrerollAds      bool `json:"has_preroll_ads"`
	HasInterstitialAds bool `json:"has_interstitial_ads"`
	HasBannerAds       bool `json:"has_banner_ads"`
}

// ---------------------------------------------------------------------------------------------------------------------
// Private structures
// ---------------------------------------------------------------------------------------------------------------------

type ev2GetUserDetailsRequest struct {
	Message ev2GetUserDetailsRequestMessage `json:"GetUserDetailsRequestMessage"`
}

type ev2GetUserDetailsResponse struct {
	Message ev2GetUserDetailsResponseMessage `json:"GetUserDetailsResponseMessage"`
}

type ev2GetEntitlementsRequest struct {
	Message ev2GetEntitlementsRequestMessage `json:"GetEntitlementsRequestMessage"`
}

type ev2GetEntitlementsResponse struct {
	Message ev2GetEntitlementsResponseMessage `json:"GetEntitlementsResponseMessage"`
}

type ev2GetUserDetailsRequestMessage struct {
	ChannelPartnerID  string `json:"channelPartnerID"`
	APIUser           string `json:"apiUser"`
	APIPassword       string `json:"apiPassword"`
	SpAccountID       string `json:"spAccountID"`
	ReturnCountryCode bool   `json:"returnCountry,string"`
}

type ev2GetUserDetailsResponseMessage struct {
	ResponseCode int `json:"responseCode,string"`

	// When ResponseCode is 0
	FailureMessages []ev2FailureMessage `json:"failureMessage"`

	// when ResponseCode is 1
	Status       string `json:"status"` // call status
	IsAnonymized bool   `json:"anonymized"`
	CpCustomerID string `json:"cpCustomerID"`
	SpAccountID  string `json:"spAccountID"`
	Username     string `json:"customerUserName"`
	FirstName    string `json:"firstName"`
	LastName     string `json:"lastName"`
	Email        string `json:"email"`
	PhoneNumber  string `json:"mobilePhone"`
	Country      string `json:"country"` // 2 letter caps country code
}

type ev2GetEntitlementsRequestMessage struct {
	ChannelPartnerID   string `json:"channelPartnerID"`
	APIUser            string `json:"apiUser"`
	APIPassword        string `json:"apiPassword"`
	SpAccountID        string `json:"spAccountID"`
	ReturnAdsInfo      bool   `json:"returnAdsInfo"`
	ReturnOrderDetails bool   `json:"returnOrderDetails"`
	ReturnTVOD         string `json:"returnTVOD"`
}

type ev2GetEntitlementsResponseMessage struct {
	ResponseCode int `json:"responseCode,string"`

	// when responseCode is 0
	FailureMessages []ev2FailureMessage `json:"failureMessage"`

	// when responseCode is 1
	Status                 string                     `json:"message"` // call status
	AccountServiceMessages []ev2AccountServiceMessage `json:"AccountServiceMessage"`
}

type ev2AccountServiceMessage struct {
	ServiceID          string                     `json:"serviceID"`
	ServiceName        string                     `json:"serviceName"`
	Name               string                     `json:"name"`
	Description        string                     `json:"description"`
	TypeOfService      string                     `json:"typeOfService"`
	Status             string                     `json:"status"`
	OrderID            string                     `json:"orderID"`
	ExternalOrderID    string                     `json:"externalOrderID"`
	StartDateInMs      int64                      `json:"startDate"`
	ValidityTillInMs   int64                      `json:"validityTill"`
	IsRenewable        bool                       `json:"isRenewal"`
	HasVodMetadata     bool                       `json:"isContent"`
	HasPrerollAds      bool                       `json:"preRollAdsEnabled"`
	HasInterstitialAds bool                       `json:"interstitialAdsEnabled"`
	HasBannerAds       bool                       `json:"bannerAdsEnabled"`
	VodItems           []ev2AccountServiceVodItem `json:"vodItems"`
}

func (ctx ev2AccountServiceMessage) transactionID() string {
	if strings.TrimSpace(ctx.ExternalOrderID) != "" {
		return strings.TrimSpace(ctx.ExternalOrderID)
	}

	return strings.TrimSpace(ctx.OrderID)
}

type ev2AccountServiceVodItem struct {
	AssetID            string `json:"assetId"`
	Title              string `json:"title"`
	ViewingPeriodHours int    `json:"viewingPeriod,string"`
	RentalPeriodHours  int    `json:"rentalPeriod,string"`
}

type ev2FailureMessage struct {
	ErrorCode    string `json:"errorCode"`
	ErrorMessage string `json:"errorMessage"`
}
