package evergent

import (
	"fmt"

	"github.com/hooqtv/monohooq/modules/go/errors"
	"github.com/hooqtv/monohooq/modules/go/tracing"
)

// ErrorResponse is rest server error response
type ErrorResponse struct {
	ErrorObj errors.Error    `json:"error"`
	Tracing  tracing.Tracing `json:"-"`
	Payload  string          `json:"-"`
}

// Error returns error message
func (ctx ErrorResponse) Error() string {
	return fmt.Sprintf("[%s] %s", ctx.ErrorObj.Code, ctx.ErrorObj.Message)
}

const (
	// ErrCodeEncodeRequestBodyFailure when request body encoding failed
	ErrCodeEncodeRequestBodyFailure = "EVRC-9001"

	// ErrCodeCreateRequestFailure when http request instantiation failed
	ErrCodeCreateRequestFailure = "EVRC-9002"

	// ErrCodeHTTPCallFailure when http call failed
	ErrCodeHTTPCallFailure = "EVRC-9003"

	// ErrCodeReadResponseBodyFailure when reading http response body failed
	ErrCodeReadResponseBodyFailure = "EVRC-9004"

	// ErrCodeDecodeResponseBodyFailure when decoding response body failed
	ErrCodeDecodeResponseBodyFailure = "EVRC-9005"

	// ErrCodeInvalidResponseBody when response body is invalid
	ErrCodeInvalidResponseBody = "EVRC-9006"
)

const (
	// ErrMsgEncodeRequestBodyFailure when request body encoding failed
	ErrMsgEncodeRequestBodyFailure = "failed to encode request body"

	// ErrMsgCreateRequestFailure when http request instantiation failed
	ErrMsgCreateRequestFailure = "failed to build http request"

	// ErrMsgHTTPCallFailure when http call failed
	ErrMsgHTTPCallFailure = "failed to send http request"

	// ErrMsgReadResponseBodyFailure when reading http response body failed
	ErrMsgReadResponseBodyFailure = "failed to read response body"

	// ErrMsgDecodeResponseBodyFailure when decoding response body failed
	ErrMsgDecodeResponseBodyFailure = "failed to decode response body"

	// ErrMsgInvalidResponseBody when response body is invalid
	ErrMsgInvalidResponseBody = "invalid response body format"
)

var errorMessages = map[string]string{
	ErrCodeEncodeRequestBodyFailure:  ErrMsgEncodeRequestBodyFailure,
	ErrCodeCreateRequestFailure:      ErrMsgCreateRequestFailure,
	ErrCodeHTTPCallFailure:           ErrMsgHTTPCallFailure,
	ErrCodeReadResponseBodyFailure:   ErrMsgReadResponseBodyFailure,
	ErrCodeDecodeResponseBodyFailure: ErrMsgDecodeResponseBodyFailure,
	ErrCodeInvalidResponseBody:       ErrMsgInvalidResponseBody,
}

// NewErrorResponse instantiates based on error code
func NewErrorResponse(code string, err error) ErrorResponse {
	message, found := errorMessages[code]
	if !found {
		panic(fmt.Errorf("no such error code: %s", code))
	}

	return ErrorResponse{
		ErrorObj: *errors.Wrap(err, message, code),
	}
}
