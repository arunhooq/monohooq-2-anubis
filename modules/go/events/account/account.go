package account

import "github.com/hooqtv/monohooq/modules/go/tracing"

// CreatedEvent is the event consumed from kinesis
type CreatedEvent struct {
	Data    Data            `json:"data"`
	Tracing tracing.Tracing `json:"tracing"`
}

// Data emitted when a user created
type Data struct {
	RefEvSpAccountID  string `json:"ref_ev_sp_account_id"`
	RevEvCpCustomerID string `json:"rev_ev_cp_customer_id"`
	Email             string `json:"email"`
	PhoneNumber       string `json:"phone_number"`
	Country           string `json:"country"`
}
