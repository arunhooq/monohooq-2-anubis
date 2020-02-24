package middleware

import (
	"context"
	"net/http"
	"strings"

	"github.com/sirupsen/logrus"
)

const (
	// LoggerKey is the request context key that holds logger instance
	LoggerKey ContextKey = "logger"
)

// Logger creates a logger middleware that will attach a logger instance to request context
// The logger instance will have "action" and "request_id" fields by default.
// Requires RequestID middleware to be attached before this
func Logger(logger logrus.FieldLogger) Middleware {
	return func(next http.Handler) http.Handler {
		return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			ctx := r.Context()

			decoratedLogger := logger.WithFields(logrus.Fields{
				"action":     generateActionValue(r),
				"request_id": r.Context().Value(RequestIDKey),
			})

			ctx = context.WithValue(ctx, LoggerKey, decoratedLogger)
			next.ServeHTTP(w, r.WithContext(ctx))
		})
	}
}

// GetLogger fetches logger instance from http request context
// If no logger is found in request contect, return standard logger
func GetLogger(r *http.Request) logrus.FieldLogger {
	logger, _ := r.Context().Value(LoggerKey).(logrus.FieldLogger)
	if logger != nil {
		return logger
	}
	return logrus.StandardLogger()
}

// Generate a friendly value to log "action" attribute from request path
func generateActionValue(r *http.Request) string {
	method := strings.ToLower(r.Method)
	path := strings.ToLower(
		strings.ReplaceAll(r.URL.Path, "/", "_"),
	)
	return method + path
}
