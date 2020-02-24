package main

import (
	"fmt"

	"github.com/go-pg/pg/v9"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/config"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/logger"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/server"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/server/handlers"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/subscription"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/user"
)

func main() {
	// Boostrap config
	c := config.New()
	appConfig := c.GetAppConfig()
	postgresConfig := c.GetPostgresConfig()
	loggingConfig := c.GetLoggingConfig()

	// Bootstrap logger
	log := logger.New(loggingConfig.GetLoggingLevel(), loggingConfig.GetLoggingOutput())
	log.WithFields(map[string]interface{}{
		"level":  loggingConfig.GetLoggingLevel(),
		"output": loggingConfig.GetLoggingOutput(),
	}).Info("Bootstrap logger completed")

	// Bootstrap db
	dbAddress := fmt.Sprintf("%s:%d", postgresConfig.GetPostgresHost(), postgresConfig.GetPostgresPort())
	db := pg.Connect(&pg.Options{
		Addr:     dbAddress,
		User:     postgresConfig.GetPostgresUser(),
		Password: postgresConfig.GetPostgresPassword(),
		Database: postgresConfig.GetPostgresName(),
	})
	log.WithField("address", dbAddress).Info("Bootstrap database completed")

	// Bootstrap services and handlers
	userService := user.NewService(db)
	userHandler := handlers.NewUserHandler(appConfig, userService)
	log.Info("Bootstrap user domain completed")

	subscriptionService := subscription.NewService(db)
	subscriptionHandler := handlers.NewSubscriptionHandler(appConfig, subscriptionService, userService)
	log.Info("Bootstrap subscription domain completed")

	// Bootstrap server
	router := server.NewRouter(log, userHandler, subscriptionHandler)
	port := appConfig.GetAppPort()
	server.ListenAndServe(log, port, router)
}
