package config

import (
	"testing"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/ssm"
	"github.com/aws/aws-sdk-go/service/ssm/ssmiface"
	"github.com/brianvoe/gofakeit"
	"github.com/hooqtv/monohooq/modules/go/mock"
	"github.com/stretchr/testify/require"
)

const (
	testConfigEvergentSSMPathBaseURL  = "/app/nikita/EVERGENT_API_BASEURL"
	testConfigEvergentSSMPathUser     = "/app/nikita/EVERGENT_API_USERNAME"
	testConfigEvergentSSMPathPassword = "/app/nikita/EVERGENT_API_PASSWORD"
)

var testConfigEvergentSSMParameters = map[string]*ssm.GetParameterInput{
	SSMEvergentBaseURL:  NewSSMParameter(testConfigEvergentSSMPathBaseURL),
	SSMEvergentUser:     NewSSMParameter(testConfigEvergentSSMPathUser),
	SSMEvergentPassword: NewSSMSecret(testConfigEvergentSSMPathPassword),
}

func TestNewEvergentFromSSMConfig(t *testing.T) {
	value := &ssm.GetParameterInput{Name: aws.String("/service/nikita/MY_PARAMETER"), WithDecryption: aws.Bool(false)}

	t.Run("PanicsWhenParameterMapIsEmpty", func(t *testing.T) {
		require.Panics(t, func() { NewEvergentFromSSMConfig(nil) })
		require.Panics(t, func() { NewEvergentFromSSMConfig(make(map[string]*ssm.GetParameterInput)) })
	})

	t.Run("PanicsWhenExpectedLabelIsMissing", func(t *testing.T) {
		t.Run("PanicsWhenSSMEvergentBaseURLIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewEvergentFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMEvergentUser:     value,
					SSMEvergentPassword: value,
				})
			})
		})

		t.Run("PanicsWhenSSMEvergentUserIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewEvergentFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMEvergentBaseURL:  value,
					SSMEvergentPassword: value,
				})
			})
		})

		t.Run("PanicsWhenSSMEvergentPasswordIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewEvergentFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMEvergentBaseURL: value,
					SSMEvergentUser:    value,
				})
			})
		})
	})

	t.Run("ReturnsInstanceWhenAllLabelsPresents", func(t *testing.T) {
		require.NotNil(t, NewEvergentFromSSMConfig(testConfigEvergentSSMParameters))
	})
}

func TestSsmEvergentConfig_GetParameters(t *testing.T) {
	refBaseURL := gofakeit.DomainName()
	refUser := gofakeit.NamePrefix()
	refPassword := gofakeit.Password(true, true, true, true, true, 32)

	cfg := ssmEvergentConfig{
		ssmConfig: ssmConfig{
			parameters: testConfigEvergentSSMParameters,
			loader: func() ssmiface.SSMAPI {
				return mock.SSM{
					Parameters: map[string]string{
						testConfigEvergentSSMPathBaseURL:  refBaseURL,
						testConfigEvergentSSMPathUser:     refUser,
						testConfigEvergentSSMPathPassword: refPassword,
					},
				}
			},
		},
	}

	t.Run("BaseURL", func(t *testing.T) {
		value, err := cfg.BaseURL()
		require.NoError(t, err)
		require.Equal(t, value, refBaseURL)
	})

	t.Run("User", func(t *testing.T) {
		value, err := cfg.User()
		require.NoError(t, err)
		require.Equal(t, value, refUser)
	})

	t.Run("Password", func(t *testing.T) {
		value, err := cfg.Password()
		require.NoError(t, err)
		require.Equal(t, value, refPassword)
	})
}
