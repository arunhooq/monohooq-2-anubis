package config

import (
	"strconv"
	"testing"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/ssm"
	"github.com/aws/aws-sdk-go/service/ssm/ssmiface"
	"github.com/brianvoe/gofakeit"
	"github.com/hooqtv/monohooq/modules/go/mock"
	"github.com/stretchr/testify/require"
)

const (
	testConfigDatabaseSSMPathHost     = "/app/nikita/PG_DB_HOST"
	testConfigDatabaseSSMPathPort     = "/app/nikita/PG_DB_PORT"
	testConfigDatabaseSSMPathName     = "/app/nikita/PG_DB_NAME"
	testConfigDatabaseSSMPathUser     = "/app/nikita/PG_DB_USER"
	testConfigDatabaseSSMPathPassword = "/app/nikita/PG_DB_PASSWORD"
)

var testConfigDatabaseSSMParameters = map[string]*ssm.GetParameterInput{
	SSMDatabaseHost:     NewSSMParameter(testConfigDatabaseSSMPathHost),
	SSMDatabasePort:     NewSSMParameter(testConfigDatabaseSSMPathPort),
	SSMDatabaseName:     NewSSMParameter(testConfigDatabaseSSMPathName),
	SSMDatabaseUser:     NewSSMParameter(testConfigDatabaseSSMPathUser),
	SSMDatabasePassword: NewSSMSecret(testConfigDatabaseSSMPathPassword),
}

func TestNewDatabaseFromSSMConfig(t *testing.T) {
	value := &ssm.GetParameterInput{Name: aws.String("/service/nikita/MY_PARAMETER"), WithDecryption: aws.Bool(false)}

	t.Run("PanicsWhenParameterMapIsEmpty", func(t *testing.T) {
		require.Panics(t, func() { NewDatabaseFromSSMConfig(nil) })
		require.Panics(t, func() { NewDatabaseFromSSMConfig(make(map[string]*ssm.GetParameterInput)) })
	})

	t.Run("PanicsWhenExpectedLabelIsMissing", func(t *testing.T) {
		t.Run("PanicsWhenSSMDatabaseHostIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewDatabaseFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMDatabasePort:     value,
					SSMDatabaseName:     value,
					SSMDatabaseUser:     value,
					SSMDatabasePassword: value,
				})
			})
		})

		t.Run("PanicsWhenSSMDatabasePortIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewDatabaseFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMDatabaseHost:     value,
					SSMDatabaseName:     value,
					SSMDatabaseUser:     value,
					SSMDatabasePassword: value,
				})
			})
		})

		t.Run("PanicsWhenSSMDatabaseNameIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewDatabaseFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMDatabaseHost:     value,
					SSMDatabasePort:     value,
					SSMDatabaseUser:     value,
					SSMDatabasePassword: value,
				})
			})
		})

		t.Run("PanicsWhenSSMDatabaseUserIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewDatabaseFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMDatabaseHost:     value,
					SSMDatabasePort:     value,
					SSMDatabaseName:     value,
					SSMDatabasePassword: value,
				})
			})
		})

		t.Run("PanicsWhenSSMDatabasePasswordIsMissing", func(t *testing.T) {
			require.Panics(t, func() {
				NewDatabaseFromSSMConfig(map[string]*ssm.GetParameterInput{
					SSMDatabaseHost: value,
					SSMDatabasePort: value,
					SSMDatabaseName: value,
					SSMDatabaseUser: value,
				})
			})
		})
	})

	t.Run("ReturnsInstanceWhenAllLabelsPresents", func(t *testing.T) {
		require.NotNil(t, NewDatabaseFromSSMConfig(testConfigDatabaseSSMParameters))
	})
}

func TestSsmDatabaseConfig_GetParameters(t *testing.T) {
	refHost := gofakeit.DomainName()
	refPort := gofakeit.Number(1024, 6500)
	refName := gofakeit.NamePrefix()
	refUser := gofakeit.NamePrefix()
	refPassword := gofakeit.Password(true, true, true, true, true, 32)

	cfg := ssmDatabaseConfig{
		ssmConfig: ssmConfig{
			parameters: testConfigDatabaseSSMParameters,
			loader: func() ssmiface.SSMAPI {
				return mock.SSM{
					Parameters: map[string]string{
						testConfigDatabaseSSMPathHost:     refHost,
						testConfigDatabaseSSMPathPort:     strconv.Itoa(refPort),
						testConfigDatabaseSSMPathName:     refName,
						testConfigDatabaseSSMPathUser:     refUser,
						testConfigDatabaseSSMPathPassword: refPassword,
					},
				}
			},
		},
	}

	t.Run("Host", func(t *testing.T) {
		value, err := cfg.Host()
		require.NoError(t, err)
		require.Equal(t, value, refHost)
	})

	t.Run("Port", func(t *testing.T) {
		value, err := cfg.Port()
		require.NoError(t, err)
		require.Equal(t, value, refPort)
	})

	t.Run("Name", func(t *testing.T) {
		value, err := cfg.Name()
		require.NoError(t, err)
		require.Equal(t, value, refName)
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
