# DB Component

## How to setup in local environment

1. Run docker-compose to create local DB container. This will also place the container under a docker network `nikita_net`.
```bash
docker-compose up -d
```

2. Create a database on the docker container.
```bash
docker exec -it nikita_db psql -U postgres -c "create database nikita_db"
```

3. Fetch dependencies.

```sh
mix deps.get
```

4. Migrate database.

```sh
mix ecto.migrate
```

## How to: Create and Run DB migrations

```sh
# Create a new migration, specify the name in the last argument
# It will be created in priv/repo/migrations
mix ecto.gen.migration my_new_table_awdawd

# Migrate latest
mix ecto.create

# Rollback the last migration file
mix ecto.rollback

# run `mix ecto` to list all other ecto commands
```

## How to: Run ecto commands with docker

1. Build the docker image.

```sh
docker build -t nikita_db_migration .
```

2. Run the ecto command like the following example:

```sh
docker run -it \
  --net=nikita_net \
  -e PG_DB_HOST='nikita_db' \
  -e PG_DB_PORT='5432' \
  -e PG_DB_NAME='nikita_db' \
  -e PG_DB_USER='postgres' \
  -e PG_DB_PASSWORD='' \
  nikita_db_migration \
  mix ecto.migrate
```
