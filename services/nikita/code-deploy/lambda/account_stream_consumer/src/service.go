package main

import (
	"database/sql"
	"fmt"

	"github.com/sirupsen/logrus"
)

// Event is fake event
type Event map[string]interface{}

type service struct {
	db *sql.DB
}

func (h service) HandleEvent(name Event) (string, error) {
	var count int
	if h.db == nil {
		return "", fmt.Errorf("database is nil")
	}

	if err := h.db.QueryRow("SELECT count(id) FROM users").Scan(&count); err != nil {
		logrus.WithFields(logrus.Fields{
			"error": err.Error(),
			"app":   appInfo,
		}).Errorf("failed to select users table")
	}

	msg := fmt.Sprintf("Hello from lambda_account_stream_consumer!, (%d entries)", count)
	logrus.WithField("app", appInfo).Info(msg)
	return msg, nil
}
