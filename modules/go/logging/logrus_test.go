package logging

import (
	"os"
	"testing"

	"github.com/brianvoe/gofakeit"
	"github.com/sirupsen/logrus"
	"github.com/stretchr/testify/require"
)

func TestSetupLogrus(t *testing.T) {
	t.Run("ReturnsNonNilInstance", func(t *testing.T) {
		require.NotNil(t, SetupLogrus(""))
	})

	t.Run("WritesToStdout", func(t *testing.T) {
		logger := SetupLogrus("dev")
		require.Equal(t, os.Stdout, logger.Out)
	})

	t.Run("UsesJSONFormatter", func(t *testing.T) {
		logger := SetupLogrus("dev")
		require.Equal(t, &logrus.JSONFormatter{}, logger.Formatter)
	})

	t.Run("DefaultMode", func(t *testing.T) {
		t.Run("LevelSetToDebug", func(t *testing.T) {
			level := logrus.DebugLevel
			require.Equal(t, level, SetupLogrus("").Level)
			require.Equal(t, level, SetupLogrus("undefined").Level)
			require.Equal(t, level, SetupLogrus("default").Level)
			require.Equal(t, level, SetupLogrus(gofakeit.FirstName()).Level)
		})
	})

	t.Run("DevelopmentMode", func(t *testing.T) {
		t.Run("LevelSetToDebug", func(t *testing.T) {
			level := logrus.DebugLevel
			require.Equal(t, level, SetupLogrus("dev").Level)
			require.Equal(t, level, SetupLogrus("development").Level)
		})
	})

	t.Run("StagingMode", func(t *testing.T) {
		t.Run("LevelSetToInfo", func(t *testing.T) {
			level := logrus.InfoLevel
			require.Equal(t, level, SetupLogrus("stag").Level)
			require.Equal(t, level, SetupLogrus("staging").Level)
		})
	})

	t.Run("ProductionMode", func(t *testing.T) {
		t.Run("LevelSetToWarn", func(t *testing.T) {
			level := logrus.WarnLevel
			require.Equal(t, level, SetupLogrus("prod").Level)
			require.Equal(t, level, SetupLogrus("production").Level)
		})
	})
}
