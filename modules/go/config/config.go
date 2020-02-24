package config

// Database is database configuration interface
type Database interface {
	Host() (string, error)
	Port() (int, error)
	Name() (string, error)
	User() (string, error)
	Password() (string, error)
}

// Evergent is evergent configuration interface
type Evergent interface {
	BaseURL() (string, error)
	User() (string, error)
	Password() (string, error)
}

// Nikita is nikita-api configuration interface
type Nikita interface {
	BaseURL() (string, error)
	APIKey() (string, error)
}
