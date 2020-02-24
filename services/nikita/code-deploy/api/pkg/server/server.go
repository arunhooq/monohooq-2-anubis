package server

import (
	"fmt"
	"net/http"

	"github.com/sirupsen/logrus"
)

// ListenAndServe listens requests coming to a TCP port and then passes them to a http handler
func ListenAndServe(logger logrus.FieldLogger, port int, h http.Handler) {
	logger.WithField("port", port).Info("Ready to serve requests")
	http.ListenAndServe(fmt.Sprintf(":%d", port), h)
}
