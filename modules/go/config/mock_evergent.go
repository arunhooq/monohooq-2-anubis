package config

import "github.com/stretchr/testify/mock"

// EvergentMock mocks evergent
type EvergentMock struct {
	mock.Mock
}

// BaseURL is a mock
func (ctx EvergentMock) BaseURL() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}

// User is a mock
func (ctx EvergentMock) User() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}

// Password is a mock
func (ctx EvergentMock) Password() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}
