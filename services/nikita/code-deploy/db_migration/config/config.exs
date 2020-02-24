import Config

config :db,
       UserPool.Repo,
       hostname: System.get_env("PG_DB_HOST", "localhost"),
       port: System.get_env("PG_DB_PORT", "54320"),
       database: System.get_env("PG_DB_NAME", "nikita_db"),
       username: System.get_env("PG_DB_USER", "postgres"),
       password: System.get_env("PG_DB_PASSWORD", "")

config :db,
  ecto_repos: [UserPool.Repo]

config :db,
       UserPool.Repo,
       migration_primary_key: [
         name: :id,
         type: :binary_id
       ]
