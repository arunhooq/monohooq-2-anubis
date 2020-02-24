package mock

import (
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/ssm"
	"github.com/aws/aws-sdk-go/service/ssm/ssmiface"
	"github.com/sirupsen/logrus"
)

// SSM mocks aws.SSM
type SSM struct {
	ssmiface.SSMAPI

	Parameters            map[string]string
	MockGetParameterError error
}

// GetParameter is a mock
func (ctx SSM) GetParameter(input *ssm.GetParameterInput) (*ssm.GetParameterOutput, error) {
	var output *ssm.GetParameterOutput
	value := ctx.Parameters[aws.StringValue(input.Name)]
	if value != "" {
		output = &ssm.GetParameterOutput{Parameter: &ssm.Parameter{Value: aws.String(value)}}
	}

	logrus.WithFields(logrus.Fields{
		"input":  input,
		"output": output,
		"error":  ctx.MockGetParameterError,
	}).Debugf("mock.SSM.GetParameter")
	return output, ctx.MockGetParameterError
}
