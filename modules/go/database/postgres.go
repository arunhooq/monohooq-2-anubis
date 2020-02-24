package database

import (
	"database/sql"
	"fmt"

	"github.com/hooqtv/monohooq/modules/go/config"
	_ "github.com/lib/pq" // PQ postgres driver
	"github.com/sirupsen/logrus"
)

// NewPostgresConnectionFromConfig instantiates database connection to postgres from SSM connection
func NewPostgresConnectionFromConfig(cfg config.Database) (*sql.DB, error) {
	if host, err := cfg.Host(); err != nil {
		return nil, err
	} else if port, err := cfg.Port(); err != nil {
		return nil, err
	} else if name, err := cfg.Name(); err != nil {
		return nil, err
	} else if user, err := cfg.User(); err != nil {
		return nil, err
	} else if password, err := cfg.Password(); err != nil {
		return nil, err
	} else {
		psqlInfo := fmt.Sprintf("host=%s port=%d user=%s password=%s dbname=%s",
			host, port, user, password, name)

		logrus.WithField("db_param", map[string]interface{}{
			"host": host,
			"port": port,
			"name": name,
			"user": user,
		}).Debug("establishing postgres database connection")
		return sql.Open("postgres", psqlInfo)
	}
}
