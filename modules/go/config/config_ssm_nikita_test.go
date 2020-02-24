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
	testConfigNikitaSSMPathBaseURL = "/app/nikita/KONG_BASEURL"
	testConfigNikitaSSMPathAPIKey  = "/app/nikita/KONG_NIKITA_INTERNAL_APIKEY"
)

var testConfigNikitaSSMParameters = map[string]*ssm.GetParameterInput{
	SSMNikitaBaseURL: NewSSMParameter(testConfigNikitaSSMPathBaseURL),
	SSMNikitaAPIKey:  NewSSMParameter(testConfigNikitaSSMPathAPIKey),
}

func TestNewNikitaFromSSMConfig(t *testing.T) {
	value := &ssm.GetParameterInput{Name: aws.String("/service/nikita/MY_PARAMETER"), WithDecryption: aws.Bool(false)}

	t.Run("PanicsWhenParameterMapIsEmpty", func(t *testing.T) {
		require.Panics(t, func() { NewNikitaFromSSMConfig(nil) })
		require.Panics(t, func() { NewNikitaFromSSMConfig(make(map[string]*ssm.GetParameterInput)) })
	})

	t.Run("PanicsWhenExpectedLabelIsMissing", func(t *testing.T) {
		t.Run("PanicsWhenSSMNikitaBaseURLIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewNikitaFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMNikitaAPIKey: value,
				})
			})
		})

		t.Run("PanicsWhenSSMNikitaAPIKeyIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewNikitaFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMNikitaBaseURL: value,
				})
			})
		})
	})

	t.Run("ReturnsInstanceWhenAllLabelsPresents", func(t *testing.T) {
		require.NotNil(t, NewNikitaFromSSMConfig(testConfigNikitaSSMParameters))
	})
}

func TestSsmNikitaConfig_GetParameters(t *testing.T) {
	refBaseURL := gofakeit.DomainName()
	refPassword := gofakeit.Password(true, true, true, true, true, 32)

	cfg := ssmNikitaConfig{
		ssmConfig: ssmConfig{
			parameters: testConfigNikitaSSMParameters,
			loader: func() ssmiface.SSMAPI {
				return mock.SSM{
					Parameters: map[string]string{
						testConfigNikitaSSMPathBaseURL: refBaseURL,
						testConfigNikitaSSMPathAPIKey:  refPassword,
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

	t.Run("APIKey", func(t *testing.T) {
		value, err := cfg.APIKey()
		require.NoError(t, err)
		require.Equal(t, value, refPassword)
	})
}
