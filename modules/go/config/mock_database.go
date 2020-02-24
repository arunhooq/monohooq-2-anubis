package config

import "github.com/stretchr/testify/mock"

// DatabaseMock mocks database
type DatabaseMock struct {
	mock.Mock
}

// Host is a mock
func (ctx DatabaseMock) Host() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}

// Port is a mock
func (ctx DatabaseMock) Port() (int, error) {
	args := ctx.Called()
	return args.Int(0), args.Error(1)
}

// Name is a mock
func (ctx DatabaseMock) Name() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}

// User is a mock
func (ctx DatabaseMock) User() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}

// Password is a mock
func (ctx DatabaseMock) Password() (string, error) {
	args := ctx.Called()
	return args.String(0), args.Error(1)
}
