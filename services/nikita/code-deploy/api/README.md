# Api

## Quick Start

1. Setup nikita db.
    * Follow the README in [db component](../db/README.md).
2. Start local server with `go run main.go`.
3. Open [`localhost:3000/api/health`](http://localhost:3000/api/health).

## How to: Run locally with docker

This requires nikita DB to be up locally with docker as well. If you haven't, please follow [db_migration README](../db_migration/README.md) first.

1. Run docker-compose to create local API container.
```bash
docker-compose up -d
```

2. Open [`localhost:3000/api/health`](http://localhost:3000/api/health).
