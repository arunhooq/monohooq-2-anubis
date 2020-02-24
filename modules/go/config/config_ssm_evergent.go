package config

import (
	"fmt"
	"strings"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/ssm"
)

// evergent parameter labels
const (
	SSMEvergentBaseURL  = "ssm_evergent_baseurl"
	SSMEvergentUser     = "ssm_evergent_user"
	SSMEvergentPassword = "ssm_evergent_password"
)

// required SSM path labels
var expectedEvergentConfigLabels = []string{
	SSMEvergentBaseURL,
	SSMEvergentUser,
	SSMEvergentPassword,
}

// NewEvergentFromSSMConfig return instances of evergent configuration
func NewEvergentFromSSMConfig(parameters map[string]*ssm.GetParameterInput) Evergent {
	if len(parameters) == 0 {
		panic("parameter map is empty")
	}

	for _, label := range expectedEvergentConfigLabels {
		parameter := parameters[label]
		ssmPath := strings.TrimSpace(aws.StringValue(parameter.Name))
		if parameter == nil {
			panic(fmt.Errorf("value of label '%s' undefined", label))
		} else if ssmPath == "" {
			panic(fmt.Errorf("value of label '%s' is empty", label))
		}
	}

	return &ssmEvergentConfig{ssmConfig: ssmConfig{parameters: parameters}}
}

type ssmEvergentConfig struct {
	ssmConfig ssmConfig
}

func (ctx ssmEvergentConfig) BaseURL() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMEvergentBaseURL)
}

func (ctx ssmEvergentConfig) User() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMEvergentUser)
}

func (ctx ssmEvergentConfig) Password() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMEvergentPassword)
}
