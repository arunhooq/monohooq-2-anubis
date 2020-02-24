package middleware

import "net/http"

// Middleware type
type Middleware func(http.Handler) http.Handler

// ContextKey is the type to set request context key
type ContextKey string
