package logging

import (
	"os"
	"strings"

	"github.com/sirupsen/logrus"
)

// SetupLogrus instantiates logrus logger instance
func SetupLogrus(env string) *logrus.Logger {
	logger := logrus.StandardLogger()
	logger.SetFormatter(&logrus.JSONFormatter{})
	logger.SetOutput(os.Stdout)

	switch strings.ToLower(env) {
	case "stag", "staging":
		logger.SetLevel(logrus.InfoLevel)
	case "prod", "production":
		logger.SetLevel(logrus.WarnLevel)
	default:
		logger.SetLevel(logrus.DebugLevel)
	}

	return logger
}
