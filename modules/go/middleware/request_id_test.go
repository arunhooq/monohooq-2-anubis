package middleware_test

import (
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/modules/go/middleware"
	"github.com/stretchr/testify/require"
)

func TestRequestIDMiddleware(t *testing.T) {
	t.Run("WithRequestIDFromClient", func(t *testing.T) {
		requestID := uuid.New().String()
		handler := http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			require.Equal(t, requestID, middleware.GetRequestID(r))
			w.WriteHeader(http.StatusNoContent)
		})
		applyMiddleware := middleware.RequestID(handler)

		req := httptest.NewRequest("GET", "https://example.com", nil)
		req.Header.Set(middleware.RequestIDHeader, requestID)

		resp := httptest.NewRecorder()
		applyMiddleware.ServeHTTP(resp, req)

		reqIDFromServer := resp.Header().Get(middleware.RequestIDHeader)
		require.Equal(t, requestID, reqIDFromServer)
	})

	t.Run("WithoutRequestIDFromClient", func(t *testing.T) {
		handler := http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			require.NotEmpty(t, middleware.GetRequestID(r))
			w.WriteHeader(http.StatusNoContent)
		})
		applyMiddleware := middleware.RequestID(handler)

		req := httptest.NewRequest("GET", "https://example.com", nil)

		applyMiddleware.ServeHTTP(httptest.NewRecorder(), req)
	})
}
