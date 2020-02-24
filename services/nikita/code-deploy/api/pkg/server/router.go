package server

import (
	"net/http"

	"github.com/go-chi/chi"
	"github.com/hooqtv/monohooq/modules/go/middleware"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/server/handlers"
	"github.com/sirupsen/logrus"
)

// NewRouter bootstraps a new router
func NewRouter(
	logger logrus.FieldLogger,
	userHandler handlers.UserHandler,
	subscriptionHandler handlers.SubscriptionHandler,
) http.Handler {
	r := chi.NewRouter()

	r.Get("/health", handlers.GetHealth)

	r.Route("/admin", func(r chi.Router) {
		commonMiddlewares(r, logger)
		r.Post("/users", userHandler.AdminCreate)
		r.Get("/users/{userID}", userHandler.AdminGet)
		r.Post("/subscriptions", subscriptionHandler.AdminCreate)
		r.Get("/subscriptions/{subscriptionID}", subscriptionHandler.AdminGet)
	})

	r.Route("/3.0", func(r chi.Router) {
		commonMiddlewares(r, logger)
		r.Get("/subscriptions", subscriptionHandler.GetMany)
	})

	r.NotFound(handlers.NotFound)

	return r
}

func commonMiddlewares(r chi.Router, logger logrus.FieldLogger) {
	r.Use(middleware.ServerTime)
	r.Use(middleware.RequestID)
	r.Use(middleware.Logger(logger))
}
