package nikita

// Client is nikita-api client interface
type Client interface {
	AdminCreateUser(data AdminCreateUserRequest) (*AdminCreateUserResponse, error)
	AdminCreateSubscription(data AdminCreateSubscriptionRequest) (*AdminCreateSubscriptionResponse, error)
}
