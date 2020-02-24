package handlers

import (
	"encoding/json"
	"net/http"

	"github.com/go-chi/chi"
	"github.com/hooqtv/monohooq/modules/go/middleware"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/config"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/errorlist"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/server/renderer"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/user"
	userModel "github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/user/model"
	"github.com/sirupsen/logrus"
)

// userHandler handler
type userHandler struct {
	service         user.Service
	printStackTrace bool
}

// UserHandler handler interface
type UserHandler interface {
	AdminCreate(w http.ResponseWriter, r *http.Request)
	AdminGet(w http.ResponseWriter, r *http.Request)
}

// NewUserHandler creates an instance of UserHandler
func NewUserHandler(appConfig config.AppConfig, userService user.Service) UserHandler {
	return &userHandler{
		service:         userService,
		printStackTrace: appConfig.GetAppPrintStackTrace(),
	}
}

// AdminCreate creates a new user and returns JSON response containing user details
// POST /admin/users
func (h *userHandler) AdminCreate(w http.ResponseWriter, r *http.Request) {
	resp := renderer.New(w, r, h.printStackTrace)

	var rBody userModel.UserCreateRequest
	if err := json.NewDecoder(r.Body).Decode(&rBody); err != nil {
		resp.RenderError(http.StatusBadRequest, errorlist.FailedParsingReqBody())
		return
	} else if !rBody.Validate() {
		resp.RenderError(http.StatusBadRequest, errorlist.InvalidParams())
		return
	}

	h.logAdminCreate(r, rBody)

	if existingUser, err := h.service.GetByUniqueIdentifier(
		rBody.Data.Email,
		rBody.Data.PhoneNumber,
		rBody.Data.RefEvSpAccountID,
	); err != nil {
		resp.RenderError(http.StatusInternalServerError, err)
		return
	} else if existingUser != nil {
		resp.RenderError(http.StatusUnauthorized, errorlist.AlreadyExists("User"))
		return
	}

	item, err := h.service.Create(rBody.Data)
	if err != nil {
		resp.RenderError(http.StatusInternalServerError, err)
		return
	}

	respBody := userModel.UserCreateResponse{Data: item}
	resp.Render(http.StatusCreated, respBody)
}

// AdminGet returns JSON response containing user details
// GET /admin/users/{userID}
func (h *userHandler) AdminGet(w http.ResponseWriter, r *http.Request) {
	resp := renderer.New(w, r, h.printStackTrace)

	userID := chi.URLParam(r, "userID")

	item, err := h.service.GetByID(userID)
	if err != nil {
		resp.RenderError(http.StatusBadRequest, err)
		return
	} else if item == nil {
		resp.RenderError(http.StatusNotFound, errorlist.ResourceNotFound("User"))
		return
	}

	respBody := userModel.UserGetResponse{Data: item}
	resp.Render(http.StatusOK, respBody)
}

func (h *userHandler) logAdminCreate(r *http.Request, rBody userModel.UserCreateRequest) {
	middleware.GetLogger(r).WithFields(logrus.Fields{
		"email":                 rBody.Data.Email,
		"phone_number":          rBody.Data.PhoneNumber,
		"country":               rBody.Data.Country,
		"ref_ev_sp_account_id":  rBody.Data.RefEvSpAccountID,
		"ref_ev_cp_customer_id": rBody.Data.RefEvCpCustomerID,
	}).Info("before_create")
}
