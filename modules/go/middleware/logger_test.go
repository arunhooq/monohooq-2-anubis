package middleware_test

import (
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/sirupsen/logrus"
	"github.com/stretchr/testify/require"

	"github.com/hooqtv/monohooq/modules/go/middleware"
)

func TestLoggerMiddleware(t *testing.T) {
	standardLogger := logrus.StandardLogger()
	handler := http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		logger := middleware.GetLogger(r)
		require.NotEmpty(t, logger)
		require.NotEqual(t, standardLogger, logger)
		logger.Info("Test Log")

		w.WriteHeader(http.StatusNoContent)
	})
	applyMiddleware1 := middleware.Logger(standardLogger)(handler)
	applyMiddleware2 := middleware.RequestID(applyMiddleware1)

	req := httptest.NewRequest("GET", "https://example.com", nil)

	resp := httptest.NewRecorder()
	applyMiddleware2.ServeHTTP(resp, req)
}

func TestGetLoggerFallback(t *testing.T) {
	req := httptest.NewRequest("GET", "https://example.com", nil)
	logger := middleware.GetLogger(req)
	require.Equal(t, logrus.StandardLogger(), logger)
}
