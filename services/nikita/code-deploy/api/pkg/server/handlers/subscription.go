package handlers

import (
	"encoding/json"
	"net/http"

	"github.com/go-chi/chi"
	"github.com/hooqtv/monohooq/modules/go/middleware"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/config"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/errorlist"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/server/renderer"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/subscription"
	subscriptionModel "github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/subscription/model"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/user"
)

// subscriptionHandler handler
type subscriptionHandler struct {
	service         subscription.Service
	userService     user.Service
	printStackTrace bool
}

// SubscriptionHandler handler interface
type SubscriptionHandler interface {
	AdminCreate(w http.ResponseWriter, r *http.Request)
	AdminGet(w http.ResponseWriter, r *http.Request)
	GetMany(w http.ResponseWriter, r *http.Request)
}

// NewSubscriptionHandler creates an instance of SubscriptionHandler
func NewSubscriptionHandler(appConfig config.AppConfig, subscriptionService subscription.Service, userService user.Service) SubscriptionHandler {
	return &subscriptionHandler{
		service:         subscriptionService,
		userService:     userService,
		printStackTrace: appConfig.GetAppPrintStackTrace(),
	}
}

// AdminCreate creates a new subscription and returns JSON response containing subscription details
// POST /admin/subscriptions
func (h *subscriptionHandler) AdminCreate(w http.ResponseWriter, r *http.Request) {
	resp := renderer.New(w, r, h.printStackTrace)

	var rBody subscriptionModel.SubscriptionCreateRequest
	if err := json.NewDecoder(r.Body).Decode(&rBody); err != nil {
		resp.RenderError(http.StatusBadRequest, errorlist.FailedParsingReqBody())
		return
	} else if !rBody.Validate() {
		resp.RenderError(http.StatusBadRequest, errorlist.InvalidParams())
		return
	}

	h.logAdminCreate(r, rBody)

	if rBody.Meta.LinkToUser.ID != "" {
		if existingUser, err := h.userService.GetByID(rBody.Meta.LinkToUser.ID); err != nil {
			resp.RenderError(http.StatusInternalServerError, err)
			return
		} else if existingUser == nil {
			resp.RenderError(http.StatusUnauthorized, errorlist.ResourceNotFound("User"))
			return
		}
	} else if rBody.Meta.LinkToUser.RefEvSpAccountID != "" {
		if existingUser, err := h.userService.GetByUniqueIdentifier(
			"",
			"",
			rBody.Meta.LinkToUser.RefEvSpAccountID,
		); err != nil {
			resp.RenderError(http.StatusInternalServerError, err)
			return
		} else if existingUser == nil {
			resp.RenderError(http.StatusUnauthorized, errorlist.ResourceNotFound("User"))
			return
		} else {
			rBody.Meta.LinkToUser.ID = existingUser.ID
		}
	}

	item, err := h.service.Create(rBody.Data, rBody.Meta)
	if err != nil {
		resp.RenderError(http.StatusInternalServerError, err)
		return
	}

	respBody := subscriptionModel.SubscriptionCreateResponse{Data: item}
	resp.Render(http.StatusCreated, respBody)
}

// AdminGet returns JSON response containing subscription details
// GET /admin/subscriptions/{subscriptionID}
func (h *subscriptionHandler) AdminGet(w http.ResponseWriter, r *http.Request) {
	resp := renderer.New(w, r, h.printStackTrace)

	subscriptionID := chi.URLParam(r, "subscriptionID")

	item, err := h.service.GetByID(subscriptionID)
	if err != nil {
		resp.RenderError(http.StatusBadRequest, err)
		return
	} else if item == nil {
		resp.RenderError(http.StatusNotFound, errorlist.ResourceNotFound("Subscription"))
		return
	}

	respBody := subscriptionModel.SubscriptionGetResponse{Data: item}
	resp.Render(http.StatusOK, respBody)
}

// AdminGet returns JSON response containing subscription details
// GET /{version}/subscriptions
func (h *subscriptionHandler) GetMany(w http.ResponseWriter, r *http.Request) {
	resp := renderer.New(w, r, h.printStackTrace)

	// TODO: UPDATE HOW TO GET USER ID AFTER JWT IS IMPLEMENTED!!!
	userID := r.URL.Query().Get("user_id")
	state := r.URL.Query().Get("state")

	items, err := h.service.GetManyByUserID(userID, state)
	if err != nil {
		resp.RenderError(http.StatusBadRequest, err)
		return
	}

	respBody := subscriptionModel.SubscriptionGetManyResponse{Data: items}
	resp.Render(http.StatusOK, respBody)
}

func (h *subscriptionHandler) logAdminCreate(r *http.Request, rBody subscriptionModel.SubscriptionCreateRequest) {
	logData := map[string]interface{}{
		"status":                    rBody.Data.Status,
		"metadata_product_sku":      rBody.Data.MetadataProduct.SKU,
		"metadata_tvod_title_id":    "",
		"started_at":                rBody.Data.StartedAt,
		"expired_at":                rBody.Data.ExpiredAt,
		"user_id":                   rBody.Meta.LinkToUser.ID,
		"user_ref_ev_sp_account_id": rBody.Meta.LinkToUser.RefEvSpAccountID,
	}

	if rBody.Data.MetadataTVOD != nil {
		logData["metadata_tvod_title_id"] = rBody.Data.MetadataTVOD.TitleID
	}

	middleware.GetLogger(r).WithFields(logData).Info("before_create")
}
