package mock

import (
	"fmt"
	"testing"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/ssm"
	"github.com/brianvoe/gofakeit"
	"github.com/stretchr/testify/require"
)

func TestFakeSSM_GetParameter(t *testing.T) {
	input := &ssm.GetParameterInput{
		Name:           aws.String("name"),
		WithDecryption: aws.Bool(false),
	}

	t.Run("ReturnsSuccess", func(t *testing.T) {
		ref := gofakeit.Word()

		mock := SSM{
			Parameters: map[string]string{
				"name": ref,
			},
		}

		value, err := mock.GetParameter(input)
		require.NoError(t, err)
		require.NotNil(t, value)
		require.NotNil(t, value.Parameter, ref)
		require.NotNil(t, aws.StringValue(value.Parameter.Value), ref)
	})

	t.Run("ReturnsError", func(t *testing.T) {
		ref := fmt.Errorf(gofakeit.Word())

		mock := SSM{MockGetParameterError: ref}
		value, err := mock.GetParameter(input)
		require.Nil(t, value)
		require.Error(t, err)
		require.Equal(t, err, ref)
	})
}
