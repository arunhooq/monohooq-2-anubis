package handlers

import (
	"net/http"

	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/errorlist"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/server/renderer"
)

// NotFound returns 404 response for requests that cannot be routed
func NotFound(w http.ResponseWriter, r *http.Request) {
	resp := renderer.New(w, nil, false)
	resp.RenderError(http.StatusNotFound, errorlist.RouteNotFound())
}
