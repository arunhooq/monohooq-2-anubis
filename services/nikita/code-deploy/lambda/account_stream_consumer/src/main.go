package main

import (
	"os"

	"github.com/aws/aws-lambda-go/lambda"
	"github.com/aws/aws-sdk-go/service/ssm"
	"github.com/hooqtv/monohooq/modules/go/config"
	"github.com/hooqtv/monohooq/modules/go/constant"
	"github.com/hooqtv/monohooq/modules/go/database"
	"github.com/hooqtv/monohooq/modules/go/logging"
	"github.com/sirupsen/logrus"
)

var parameters = map[string]*ssm.GetParameterInput{
	config.SSMDatabaseHost:     config.NewSSMParameter("/app/nikita/PG_DB_HOST"),
	config.SSMDatabasePort:     config.NewSSMParameter("/app/nikita/PG_DB_PORT"),
	config.SSMDatabaseName:     config.NewSSMParameter("/app/nikita/PG_DB_NAME"),
	config.SSMDatabaseUser:     config.NewSSMParameter("/app/nikita/PG_DB_USER"),
	config.SSMDatabasePassword: config.NewSSMSecret("/app/nikita/PG_DB_PASSWORD"),
}

var env = os.Getenv("ENVIRONMENT")
var appInfo = map[string]interface{}{
	"tag": constant.AppTag(),
	"env": env,
}

func main() {
	logging.SetupLogrus(env)

	cfg := config.NewDatabaseFromSSMConfig(parameters)
	db, err := database.NewPostgresConnectionFromConfig(cfg)
	if err != nil {
		logrus.WithFields(logrus.Fields{
			"app":   appInfo,
			"error": err.Error(),
		}).Panicf("failed to establish database connection")
	}

	defer db.Close()

	svc := service{db: db}
	lambda.Start(svc.HandleEvent)
}
