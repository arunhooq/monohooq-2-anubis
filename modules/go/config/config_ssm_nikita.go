package config

import (
	"fmt"
	"strings"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/ssm"
)

// evergent parameter labels
const (
	SSMNikitaBaseURL = "ssm_nikita_baseurl"
	SSMNikitaAPIKey  = "ssm_nikita_apikey"
)

// required SSM path labels
var expectedNikitaConfigLabels = []string{
	SSMNikitaBaseURL,
	SSMNikitaAPIKey,
}

// NewNikitaFromSSMConfig return instances of nikita configuration
func NewNikitaFromSSMConfig(parameters map[string]*ssm.GetParameterInput) Nikita {
	if len(parameters) == 0 {
		panic("parameter map is empty")
	}

	for _, label := range expectedNikitaConfigLabels {
		parameter := parameters[label]
		ssmPath := strings.TrimSpace(aws.StringValue(parameter.Name))
		if parameter == nil {
			panic(fmt.Errorf("value of label '%s' undefined", label))
		} else if ssmPath == "" {
			panic(fmt.Errorf("value of label '%s' is empty", label))
		}
	}

	return &ssmNikitaConfig{ssmConfig: ssmConfig{parameters: parameters}}
}

type ssmNikitaConfig struct {
	ssmConfig ssmConfig
}

func (ctx ssmNikitaConfig) BaseURL() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMNikitaBaseURL)
}

func (ctx ssmNikitaConfig) APIKey() (string, error) {
	return ctx.ssmConfig.getParameterString(SSMNikitaAPIKey)
}
