package tracing_test

import (
	"testing"
	"time"

	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/modules/go/tracing"
	"github.com/stretchr/testify/require"
)

func TestTimestampOrDefault(t *testing.T) {
	t.Run("HasTimestamp", func(t *testing.T) {
		now := time.Now().UTC()
		trace := &tracing.Tracing{
			Timestamp: now,
		}
		require.Equal(t, now, trace.TimestampOrDefault())
	})
	t.Run("NoTimestamp", func(t *testing.T) {
		trace := &tracing.Tracing{}
		require.NotEmpty(t, trace.TimestampOrDefault())
	})
}

func TestRequestIDOrDefault(t *testing.T) {
	t.Run("HasRequestID", func(t *testing.T) {
		requestID := uuid.New().String()
		trace := &tracing.Tracing{
			RequestID: requestID,
		}
		require.Equal(t, requestID, trace.RequestIDOrDefault())
	})
	t.Run("NoRequestID", func(t *testing.T) {
		trace := &tracing.Tracing{}
		require.NotEmpty(t, trace.RequestIDOrDefault())
	})
}
