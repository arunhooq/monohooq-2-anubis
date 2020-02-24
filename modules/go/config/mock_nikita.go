package config

import "github.com/stretchr/testify/mock"

// NikitaMock mocks nikita-api config
type NikitaMock struct {
	mock.Mock
}

// BaseURL is a mock
func (ctx NikitaMock) BaseURL() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}

// APIKey is a mock
func (ctx NikitaMock) APIKey() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}
