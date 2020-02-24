package config

// Application config
const (
	PathLoggingLevel    = "logging.level"
	EnvLoggingLevel     = "LOGGING_LEVEL"
	DefaultLoggingLevel = "info"

	PathLoggingOutput    = "logging.output"
	EnvLoggingOutput     = "LOGGING_OUTPUT"
	DefaultLoggingOutput = "json"
)

// LoggingConfig groups common configuration for logging
type LoggingConfig interface {
	GetLoggingLevel() string
	GetLoggingOutput() string
}

func (ctx *config) GetLoggingConfig() LoggingConfig {
	return ctx
}

func (ctx *config) setupLoggingConfig() {
	ctx.setupLoggingLevel()
	ctx.setupLoggingOutput()
}

func (ctx *config) GetLoggingLevel() string {
	return ctx.v.GetString(PathLoggingLevel)
}

func (ctx *config) setupLoggingLevel() {
	ctx.v.BindEnv(PathLoggingLevel, EnvLoggingLevel)
	ctx.v.SetDefault(PathLoggingLevel, DefaultLoggingLevel)
}

func (ctx *config) GetLoggingOutput() string {
	return ctx.v.GetString(PathLoggingOutput)
}

func (ctx *config) setupLoggingOutput() {
	ctx.v.BindEnv(PathLoggingOutput, EnvLoggingOutput)
	ctx.v.SetDefault(PathLoggingOutput, DefaultLoggingOutput)
}
