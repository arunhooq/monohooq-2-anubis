package config

// Postgres config
const (
	PathPostgresHost    = "postgres.host"
	EnvPostgresHost     = "PG_DB_HOST"
	DefaultPostgresHost = "localhost"

	PathPostgresPort    = "postgres.port"
	EnvPostgresPort     = "PG_DB_PORT"
	DefaultPostgresPort = 5432

	PathPostgresName    = "postgres.name"
	EnvPostgresName     = "PG_DB_NAME"
	DefaultPostgresName = ""

	PathPostgresUser    = "postgres.user"
	EnvPostgresUser     = "PG_DB_USER"
	DefaultPostgresUser = ""

	PathPostgresPassword    = "postgres.password"
	EnvPostgresPassword     = "PG_DB_PASSWORD"
	DefaultPostgresPassword = ""
)

// PostgresConfig groups common configuration for connecting to postgres
type PostgresConfig interface {
	GetPostgresHost() string
	GetPostgresPort() int
	GetPostgresName() string
	GetPostgresUser() string
	GetPostgresPassword() string
}

func (ctx *config) GetPostgresConfig() PostgresConfig {
	return ctx
}

func (ctx *config) setupPostgresConfig() {
	ctx.setupPostgresHost()
	ctx.setupPostgresPort()
	ctx.setupPostgresName()
	ctx.setupPostgresUser()
	ctx.setupPostgresPassword()
}

func (ctx *config) GetPostgresHost() string {
	return ctx.v.GetString(PathPostgresHost)
}

func (ctx *config) setupPostgresHost() {
	ctx.v.BindEnv(PathPostgresHost, EnvPostgresHost)
	ctx.v.SetDefault(PathPostgresHost, DefaultPostgresHost)
}

func (ctx *config) GetPostgresPort() int {
	return ctx.v.GetInt(PathPostgresPort)
}

func (ctx *config) setupPostgresPort() {
	ctx.v.BindEnv(PathPostgresPort, EnvPostgresPort)
	ctx.v.SetDefault(PathPostgresPort, DefaultPostgresPort)
}

func (ctx *config) GetPostgresName() string {
	return ctx.v.GetString(PathPostgresName)
}

func (ctx *config) setupPostgresName() {
	ctx.v.BindEnv(PathPostgresName, EnvPostgresName)
	ctx.v.SetDefault(PathPostgresName, DefaultPostgresName)
}

func (ctx *config) GetPostgresUser() string {
	return ctx.v.GetString(PathPostgresUser)
}

func (ctx *config) setupPostgresUser() {
	ctx.v.BindEnv(PathPostgresUser, EnvPostgresUser)
	ctx.v.SetDefault(PathPostgresUser, DefaultPostgresUser)
}

func (ctx *config) GetPostgresPassword() string {
	return ctx.v.GetString(PathPostgresPassword)
}

func (ctx *config) setupPostgresPassword() {
	ctx.v.BindEnv(PathPostgresPassword, EnvPostgresPassword)
	ctx.v.SetDefault(PathPostgresPassword, DefaultPostgresPassword)
}
