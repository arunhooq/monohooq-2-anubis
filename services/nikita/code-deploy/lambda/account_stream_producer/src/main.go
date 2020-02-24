package main

import (
	"os"

	"github.com/aws/aws-lambda-go/lambda"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/kinesis"
	"github.com/aws/aws-sdk-go/service/ssm"
	"github.com/hooqtv/monohooq/modules/go/clients/evergent"
	"github.com/hooqtv/monohooq/modules/go/config"
	"github.com/hooqtv/monohooq/modules/go/constant"
	"github.com/hooqtv/monohooq/modules/go/logging"
)

var env = os.Getenv("ENVIRONMENT")

var parameters = map[string]*ssm.GetParameterInput{
	config.SSMEvergentBaseURL:  config.NewSSMParameter("/app/nikita/EVERGENT_API_BASEURL"),
	config.SSMEvergentUser:     config.NewSSMParameter("/app/nikita/EVERGENT_API_USERNAME"),
	config.SSMEvergentPassword: config.NewSSMSecret("/app/nikita/EVERGENT_API_PASSWORD"),
}

const (
	evNamespace         = "ev2"
	evPartnerID         = "HAWK"
	kinesisPartitionKey = "sp_account_id"
)

func main() {
	logging.SetupLogrus(env)

	kinesisInfo := &KinesisInfo{
		StreamName:   os.Getenv("KINESIS_STREAM_NAME"),
		PartitionKey: kinesisPartitionKey,
	}

	appInfo := &AppInfo{
		Env: env,
		Tag: constant.AppTag(),
	}

	cfg := config.NewEvergentFromSSMConfig(parameters)
	ev2, ev2Info, err := newEvergent(cfg)
	if err != nil {
		panic(err)
	}

	svc := service{
		kinesis:  kinesis.New(session.Must(session.NewSession())),
		evergent: ev2,

		appInfo:      appInfo,
		evergentInfo: ev2Info,
		kinesisInfo:  kinesisInfo,
	}

	lambda.Start(svc.HandleEvent)
}

func newEvergent(cfg config.Evergent) (evergent.Client, *EvergentInfo, error) {
	baseURL, err := cfg.BaseURL()
	if err != nil {
		return nil, nil, err
	}

	user, err := cfg.User()
	if err != nil {
		return nil, nil, err
	}

	password, err := cfg.Password()
	if err != nil {
		return nil, nil, err
	}

	info := &EvergentInfo{
		Namespace: evNamespace,
		PartnerID: evPartnerID,
		BaseURL:   baseURL,
		User:      user,
		Password:  password,
	}

	return evergent.NewClient(evNamespace, baseURL, evPartnerID, user, password), info, nil
}
