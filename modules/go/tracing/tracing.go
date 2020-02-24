package tracing

import (
	"time"

	"github.com/google/uuid"
)

// Tracing stores common event metadata
type Tracing struct {
	RequestID string    `json:"request_id"`
	Timestamp time.Time `json:"timestamp"`
	Source    string    `json:"source"`
}

// TimestampOrDefault optionally generates timestamp
func (ctx *Tracing) TimestampOrDefault() time.Time {
	if ctx.Timestamp.IsZero() {
		ctx.Timestamp = time.Now().UTC().Truncate(time.Millisecond)
	}

	return ctx.Timestamp
}

// RequestIDOrDefault optionally generates request identifier
func (ctx *Tracing) RequestIDOrDefault() string {
	if ctx.RequestID == "" {
		ctx.RequestID = uuid.New().String()
	}

	return ctx.RequestID
}
