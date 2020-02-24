package handlers

import (
	"net/http"

	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/server/renderer"
)

var health = map[string]string{
	"status": "ok",
}

// GetHealth returns a simple response for healthcheck purpose
func GetHealth(w http.ResponseWriter, r *http.Request) {
	resp := renderer.New(w, r, false)
	resp.Render(http.StatusOK, health)
}
