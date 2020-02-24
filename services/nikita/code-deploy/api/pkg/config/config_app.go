package config

// Application config
const (
	PathAppPort    = "app.port"
	EnvAppPort     = "APP_PORT"
	DefaultAppPort = 3000

	PathAppPrintStackTrace    = "app.print_stack_trace"
	EnvAppPrintStackTrace     = "APP_PRINT_STACK_TRACE"
	DefaultAppPrintStackTrace = false
)

// AppConfig groups common configuration for web application
type AppConfig interface {
	GetAppPort() int
	GetAppPrintStackTrace() bool
}

func (ctx *config) GetAppConfig() AppConfig {
	return ctx
}

func (ctx *config) setupAppConfig() {
	ctx.setupAppPort()
	ctx.setupAppPrintStackTrace()
}

func (ctx *config) GetAppPort() int {
	return ctx.v.GetInt(PathAppPort)
}

func (ctx *config) setupAppPort() {
	ctx.v.BindEnv(PathAppPort, EnvAppPort)
	ctx.v.SetDefault(PathAppPort, DefaultAppPort)
}

func (ctx *config) GetAppPrintStackTrace() bool {
	return ctx.v.GetBool(PathAppPrintStackTrace)
}

func (ctx *config) setupAppPrintStackTrace() {
	ctx.v.BindEnv(PathAppPrintStackTrace, EnvAppPrintStackTrace)
	ctx.v.SetDefault(PathAppPrintStackTrace, DefaultAppPrintStackTrace)
}
