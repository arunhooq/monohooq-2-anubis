package nikita

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
	ErrCodeEncodeRequestBodyFailure = "NKTC-9001"

	// ErrCodeCreateRequestFailure when http request instantiation failed
	ErrCodeCreateRequestFailure = "NKTC-9002"

	// ErrCodeHTTPCallFailure when http call failed
	ErrCodeHTTPCallFailure = "NKTC-9003"

	// ErrCodeReadResponseBodyFailure when reading http response body failed
	ErrCodeReadResponseBodyFailure = "NKTC-9004"

	// ErrCodeDecodeResponseBodyFailure when decoding response body failed
	ErrCodeDecodeResponseBodyFailure = "NKTC-9005"
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
)

var errorMessages = map[string]string{
	ErrCodeEncodeRequestBodyFailure:  ErrMsgEncodeRequestBodyFailure,
	ErrCodeCreateRequestFailure:      ErrMsgCreateRequestFailure,
	ErrCodeHTTPCallFailure:           ErrMsgHTTPCallFailure,
	ErrCodeReadResponseBodyFailure:   ErrMsgReadResponseBodyFailure,
	ErrCodeDecodeResponseBodyFailure: ErrMsgDecodeResponseBodyFailure,
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
