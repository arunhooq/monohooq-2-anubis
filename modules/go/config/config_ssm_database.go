package config

import (
	"fmt"
	"strings"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/ssm"
)

// database parameter labels
const (
	SSMDatabaseHost     = "ssm_database_host"
	SSMDatabasePort     = "ssm_database_port"
	SSMDatabaseName     = "ssm_database_name"
	SSMDatabaseUser     = "ssm_database_user"
	SSMDatabasePassword = "ssm_database_password"
)

// required SSM path labels
var expectedDatabaseConfigLabels = []string{
	SSMDatabaseHost,
	SSMDatabasePort,
	SSMDatabaseName,
	SSMDatabaseUser,
	SSMDatabasePassword,
}

// NewDatabaseFromSSMConfig return instances of database configuration
func NewDatabaseFromSSMConfig(parameters map[string]*ssm.GetParameterInput) Database {
	if len(parameters) == 0 {
		panic("parameter map is empty")
	}

	for _, label := range expectedDatabaseConfigLabels {
		parameter := parameters[label]
		ssmPath := strings.TrimSpace(aws.StringValue(parameter.Name))
		if parameter == nil {
			panic(fmt.Errorf("value of label '%s' undefined", label))
		} else if ssmPath == "" {
			panic(fmt.Errorf("value of label '%s' is empty", label))
		}
	}

	return &ssmDatabaseConfig{ssmConfig: ssmConfig{parameters: parameters}}
}

type ssmDatabaseConfig struct {
	ssmConfig ssmConfig
}

func (ctx *ssmDatabaseConfig) Host() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMDatabaseHost)
}

func (ctx *ssmDatabaseConfig) Port() (int, error) {
	return ctx.ssmConfig.getParameterInt(SSMDatabasePort)
}

func (ctx *ssmDatabaseConfig) Name() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMDatabaseName)
}

func (ctx *ssmDatabaseConfig) User() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMDatabaseUser)
}

func (ctx *ssmDatabaseConfig) Password() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMDatabasePassword)
}
