package nikita

import "github.com/stretchr/testify/mock"

// Mock is nikita client mock
type Mock struct {
	mock.Mock
}

// AdminCreateUser is a mock
func (ctx Mock) AdminCreateUser(request AdminCreateUserRequest) (*AdminCreateUserResponse, error) {
	args := ctx.Called(request)
	return args.Get(0).(*AdminCreateUserResponse), args.Error(1)
}

// AdminCreateSubscription is a mock
func (ctx Mock) AdminCreateSubscription(request AdminCreateSubscriptionRequest) (*AdminCreateSubscriptionResponse, error) {
	args := ctx.Called(request)
	return args.Get(0).(*AdminCreateSubscriptionResponse), args.Error(1)
}
