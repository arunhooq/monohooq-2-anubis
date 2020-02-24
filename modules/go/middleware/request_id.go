package middleware

import (
	"context"
	"net/http"

	"github.com/google/uuid"
)

const (
	// RequestIDKey is the key that holds the unique request ID in a request context.
	RequestIDKey ContextKey = "request_id"

	// RequestIDHeader is the header key to get/set request id
	RequestIDHeader = "X-Request-ID"
)

// RequestID middleware
func RequestID(next http.Handler) http.Handler {
	fn := func(w http.ResponseWriter, r *http.Request) {
		ctx := r.Context()

		// Get request ID from header and sets it to request context
		// Or generate a new one if client doesn't provide one
		requestID := r.Header.Get(RequestIDHeader)
		if requestID == "" {
			requestID = uuid.New().String()
		}
		ctx = context.WithValue(ctx, RequestIDKey, requestID)

		// Set request ID to response header as well
		w.Header().Set(RequestIDHeader, requestID)

		next.ServeHTTP(w, r.WithContext(ctx))
	}
	return http.HandlerFunc(fn)
}

// GetRequestID fetches request id value from http request context
func GetRequestID(r *http.Request) string {
	return r.Context().Value(RequestIDKey).(string)
}
