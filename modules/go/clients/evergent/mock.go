package evergent

import "github.com/stretchr/testify/mock"

// Mock mocks evergent client
type Mock struct {
	mock.Mock
}

// GetUserDetails is a mock
func (ctx Mock) GetUserDetails(req GetUserDetailsRequest) (*GetUserDetailsResponse, error) {
	var res *GetUserDetailsResponse

	args := ctx.Called(req)
	obj, ok := args.Get(0).(*GetUserDetailsResponse)
	if ok {
		res = obj
	}

	return res, args.Error(1)
}

// GetEntitlements is a mock
func (ctx Mock) GetEntitlements(req GetEntitlementsRequest) (*GetEntitlementsResponse, error) {
	var res *GetEntitlementsResponse

	args := ctx.Called(req)
	obj, ok := args.Get(0).(*GetEntitlementsResponse)
	if ok {
		res = obj
	}

	return res, args.Error(1)
}
