package model

import (
	"time"
)

// User model
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

// UserCreateRequest models user creation request body
type UserCreateRequest struct {
	Data *UserCreateRequestData `json:"data"`
}

// UserCreateRequestData models user creation data
type UserCreateRequestData struct {
	Email             string `json:"email"`
	PhoneNumber       string `json:"phone_number"`
	Country           string `json:"country"`
	RefEvSpAccountID  string `json:"ref_ev_sp_account_id"`
	RefEvCpCustomerID string `json:"ref_ev_cp_customer_id"`
}

// UserCreateResponse models user creation response body
type UserCreateResponse struct {
	Data *User `json:"data"`
}

// UserGetResponse models user get response body
type UserGetResponse struct {
	Data *User `json:"data"`
}

// Validate UserCreateRequest
func (req *UserCreateRequest) Validate() bool {
	return req.Data != nil &&
		req.Data.Country != "" &&
		(req.Data.Email != "" || req.Data.PhoneNumber != "")
}
