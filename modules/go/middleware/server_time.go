package middleware

import (
	"net/http"
	"time"
)

// ServerTimeHeader is the header key to set server time
const ServerTimeHeader = "X-Server-Time"

// ServerTime middleware
func ServerTime(next http.Handler) http.Handler {
	fn := func(w http.ResponseWriter, r *http.Request) {
		ctx := r.Context()
		w.Header().Set(ServerTimeHeader, time.Now().UTC().Format(time.RFC3339Nano))
		next.ServeHTTP(w, r.WithContext(ctx))
	}
	return http.HandlerFunc(fn)
}
