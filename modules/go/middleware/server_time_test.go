package middleware_test

import (
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/hooqtv/monohooq/modules/go/middleware"
	"github.com/stretchr/testify/require"
)

func TestServerTimeMiddleware(t *testing.T) {
	handler := http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(http.StatusNoContent)
	})
	applyMiddleware := middleware.ServerTime(handler)

	req := httptest.NewRequest("GET", "https://example.com", nil)

	resp := httptest.NewRecorder()
	applyMiddleware.ServeHTTP(resp, req)

	serverTime := resp.Header().Get(middleware.ServerTimeHeader)
	require.NotEmpty(t, serverTime)
}
