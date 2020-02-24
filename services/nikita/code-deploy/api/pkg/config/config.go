package config

import (
	"os"

	"github.com/spf13/viper"
)

// Config object
type Config interface {
	GetAppConfig() AppConfig
	GetPostgresConfig() PostgresConfig
	GetLoggingConfig() LoggingConfig
}

type config struct {
	v *viper.Viper
}

// New creates a new instance of Config
func New() Config {
	c := &config{}
	c.v = viper.New()

	// Setup environment variables binding and default values
	c.setupAppConfig()
	c.setupPostgresConfig()

	// Read config from config/{ENV}.toml
	env := os.Getenv("ENV")
	if env == "" {
		env = "local"
	}
	c.v.SetConfigFile("./config/" + env + ".toml")
	_ = c.v.ReadInConfig() // Ignore error if file doesn't exist

	return c
}
