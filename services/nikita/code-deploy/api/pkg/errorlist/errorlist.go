package errorlist

import (
	"fmt"

	"github.com/hooqtv/monohooq/modules/go/errors"
)

const (
	failedParsingReqBodyMsg  = "Failed parsing request body"
	failedParsingReqBodyCode = "NKT-40000"

	invalidParamsMsg  = "Request parameter(s) are invalid"
	invalidParamsCode = "NKT-40001"

	alreadyExistsMsg  = "%s already exists"
	alreadyExistsCode = "NKT-40300"

	routeNotFoundMsg  = "Route not found"
	routeNotFoundCode = "NKT-40400"

	resourceNotFoundMsg  = "%s is not found"
	resourceNotFoundCode = "NKT-40401"

	internalServerErrorMsg  = "Internal server error"
	internalServerErrorCode = "NKT-50000"

	unexpectedErrorMsg  = "Unexpected error"
	unexpectedErrorCode = "NKT-50099"
)

// FailedParsingReqBody error
func FailedParsingReqBody() error {
	e := errors.New(failedParsingReqBodyMsg, failedParsingReqBodyCode)
	return trimStackTrace(e)
}

// InvalidParams error
func InvalidParams() error {
	e := errors.New(invalidParamsMsg, invalidParamsCode)
	return trimStackTrace(e)
}

// AlreadyExists error
func AlreadyExists(resourceName string) error {
	message := fmt.Sprintf(alreadyExistsMsg, resourceName)
	e := errors.New(message, alreadyExistsCode)
	return trimStackTrace(e)
}

// RouteNotFound error
func RouteNotFound() error {
	e := errors.New(routeNotFoundMsg, routeNotFoundCode)
	return trimStackTrace(e)
}

// ResourceNotFound error
func ResourceNotFound(resourceName string) error {
	message := fmt.Sprintf(resourceNotFoundMsg, resourceName)
	e := errors.New(message, resourceNotFoundCode)
	return trimStackTrace(e)
}

// InternalServerError error
func InternalServerError(errorToWrap error) error {
	e := errors.Wrap(errorToWrap, unexpectedErrorMsg, unexpectedErrorCode)
	return trimStackTrace(e)
}

// UnexpectedError error
func UnexpectedError(errorToWrap error) error {
	e := errors.Wrap(errorToWrap, unexpectedErrorMsg, unexpectedErrorCode)
	return trimStackTrace(e)
}

// Remove errorlist.go from stack trace
func trimStackTrace(e *errors.Error) *errors.Error {
	e.StackTrace = e.StackTrace[1:]
	return e
}
