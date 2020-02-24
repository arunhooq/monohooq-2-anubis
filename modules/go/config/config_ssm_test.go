package config

import (
	"testing"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/brianvoe/gofakeit"
	"github.com/stretchr/testify/require"
)

func TestNewSSMParameter(t *testing.T) {
	name := gofakeit.FirstName()
	require.Equal(t, name, aws.StringValue(NewSSMParameter(name).Name))
	require.False(t, aws.BoolValue(NewSSMParameter("name").WithDecryption))
}

func TestNewSSMSecret(t *testing.T) {
	name := gofakeit.FirstName()
	require.Equal(t, name, aws.StringValue(NewSSMSecret(name).Name))
	require.True(t, aws.BoolValue(NewSSMSecret("name").WithDecryption))
}
