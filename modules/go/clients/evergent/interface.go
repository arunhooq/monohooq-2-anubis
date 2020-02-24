package evergent

// Client is an evergent client interface
type Client interface {
	GetUserDetails(req GetUserDetailsRequest) (*GetUserDetailsResponse, error)
	GetEntitlements(req GetEntitlementsRequest) (*GetEntitlementsResponse, error)
}
