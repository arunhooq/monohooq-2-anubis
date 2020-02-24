package renderer

import (
	"encoding/json"
	"net/http"

	"github.com/hooqtv/monohooq/modules/go/errors"
	"github.com/hooqtv/monohooq/modules/go/middleware"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/errorlist"
	"github.com/sirupsen/logrus"
)

// Renderer is an utility object to render JSON response
type Renderer struct {
	responseWriter  http.ResponseWriter
	logger          logrus.FieldLogger
	printStackTrace bool
}

// New creates a new instance of renderer
func New(w http.ResponseWriter, r *http.Request, printStackTrace bool) *Renderer {
	w.Header().Set("Content-Type", "application/json")
	return &Renderer{
		responseWriter:  w,
		logger:          middleware.GetLogger(r),
		printStackTrace: printStackTrace,
	}
}

// Render renders standard response
func (r *Renderer) Render(statusCode int, body interface{}) {
	r.responseWriter.WriteHeader(statusCode)
	_ = json.NewEncoder(r.responseWriter).Encode(body)
}

// RenderError renders standard error response
func (r *Renderer) RenderError(statusCode int, err error) {
	var e *errors.JSONBody
	if tryErr, ok := err.(*errors.JSONBody); ok {
		e = tryErr
	} else if tryErr, ok := err.(*errors.Error); ok {
		e = tryErr.ToJSONBody()
	} else {
		e = errorlist.UnexpectedError(err).(*errors.Error).ToJSONBody()
	}

	if e.ErrorData != nil {
		e.ErrorData.StackTrace = e.ErrorData.StackTrace[:3]

		if r.logger != nil {
			r.logger.WithFields(map[string]interface{}{
				"message":     e.Error(),
				"code":        e.ErrorData.Code,
				"stack_trace": e.ErrorData.StackTrace[:3],
			}).Error("error")
		}

		if !r.printStackTrace {
			e.ErrorData.StackTrace = nil
		}
	}

	r.Render(statusCode, e)
}
