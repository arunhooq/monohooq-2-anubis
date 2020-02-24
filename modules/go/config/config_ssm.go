package config

import (
	"fmt"
	"strconv"
	"sync"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/ssm"
	"github.com/aws/aws-sdk-go/service/ssm/ssmiface"
)

// NewSSMParameter returns new SSM parameter entry
func NewSSMParameter(name string) *ssm.GetParameterInput {
	return &ssm.GetParameterInput{
		Name:           aws.String(name),
		WithDecryption: aws.Bool(false),
	}
}

// NewSSMSecret returns new SSM secret entry
func NewSSMSecret(name string) *ssm.GetParameterInput {
	return &ssm.GetParameterInput{
		Name:           aws.String(name),
		WithDecryption: aws.Bool(true),
	}
}

// ssmConfig is the base implementation of ssm configuration loader
type ssmConfig struct {
	loader     func() ssmiface.SSMAPI
	once       sync.Once
	ssm        ssmiface.SSMAPI
	parameters map[string]*ssm.GetParameterInput
}

func (ctx *ssmConfig) getParameterString(label string) (string, error) {
	input := ctx.parameters[label]
	if input == nil {
		return "", fmt.Errorf("label '%s' is undefined", label)
	}

	// initialize SSM once
	var sdkErr error
	ctx.once.Do(func() {
		if ctx.loader == nil {
			sess, err := session.NewSession()
			if err != nil {
				sdkErr = err
			} else {
				ctx.ssm = ssm.New(sess)
			}
		} else {
			ctx.ssm = ctx.loader()
		}
	})

	// check sdk error
	if sdkErr != nil {
		return "", fmt.Errorf("failed to get aws session, %v", sdkErr)
	}

	// get parameter
	output, err := ctx.ssm.GetParameter(input)
	if err != nil {
		return "", fmt.Errorf("failed to retrieve parameter: %v", err)
	}

	return aws.StringValue(output.Parameter.Value), nil
}

func (ctx *ssmConfig) getParameterInt(label string) (int, error) {
	if value, err := ctx.getParameterString(label); err != nil {
		return 0, err
	} else if port, err := strconv.Atoi(value); err != nil {
		return 0, fmt.Errorf("failed to parse '%s' as integer: %v", value, err)
	} else {
		return port, nil
	}
}
