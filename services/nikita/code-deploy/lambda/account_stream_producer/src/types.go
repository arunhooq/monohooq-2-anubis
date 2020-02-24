package main

import (
	"github.com/hooqtv/monohooq/modules/go/errors"
	"github.com/hooqtv/monohooq/modules/go/events/account"
	"github.com/hooqtv/monohooq/modules/go/tracing"
)

// AccountEvent is the trigger payload received from kong
type AccountEvent struct {
	SpAccountID string `json:"sp_account_id"`
}

// AccountEventReceipt is account event receipt
type AccountEventReceipt struct {
	Error   *errors.Error    `json:"error,omitempty"`
	ShardID string           `json:"shard_id,omitempty"`
	Data    *account.Data    `json:"data,omitempty"`
	Meta    tracing.Metadata `json:"meta"`
}

// KinesisInfo stores kinesis information
type KinesisInfo struct {
	StreamName   string `json:"stream_name"`
	PartitionKey string `json:"partition_key"`
}

// EvergentInfo stores evergent info
type EvergentInfo struct {
	BaseURL   string `json:"base_url"`
	Namespace string `json:"namespace"`
	PartnerID string `json:"partner_id"`
	User      string `json:"user"`
	Password  string `json:"-"` // do not include in log
}

// AppInfo stores application info
type AppInfo struct {
	Env string `json:"env"`
	Tag string `json:"tag"`
}
