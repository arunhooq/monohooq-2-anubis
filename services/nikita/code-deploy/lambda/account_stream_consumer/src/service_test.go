package main

import (
	"os"
	"testing"

	"github.com/DATA-DOG/go-sqlmock"
	"github.com/sirupsen/logrus"
	"github.com/stretchr/testify/require"
)

func TestMain(m *testing.M) {
	logrus.SetFormatter(&logrus.JSONFormatter{})
	logrus.SetOutput(os.Stdout)
	logrus.SetLevel(logrus.DebugLevel)

	os.Exit(m.Run())
}

func TestService_HandleEvent(t *testing.T) {
	var event Event

	t.Run("ErrorsWhenDatabaseIsNil", func(t *testing.T) {
		svc := service{db: nil}

		_, err := svc.HandleEvent(event)
		require.Error(t, err)
	})

	t.Run("NormalCase", func(t *testing.T) {
		db, mock, err := sqlmock.New()
		require.NoError(t, err)
		require.NotNil(t, db)

		defer db.Close()

		svc := service{db: db}
		mock.ExpectQuery("^SELECT (.+) FROM users$").WillReturnRows(sqlmock.NewRows([]string{"count"}).AddRow(1))
		res, err := svc.HandleEvent(event)
		require.NoError(t, err)
		require.NotEmpty(t, res)
		require.NoError(t, mock.ExpectationsWereMet())
	})
}
