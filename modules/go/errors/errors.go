package errors

import "github.com/pkg/errors"

// Error common object
type Error struct {
	Message      string   `json:"message"`
	Code         string   `json:"code"`
	StackTrace   []string `json:"stack_trace,omitempty"`
	WrappedError error    `json:"-"`
}

// JSONBody is a common error response in JSON
type JSONBody struct {
	ErrorData *Error `json:"error"`
}

type stackTracer interface {
	StackTrace() errors.StackTrace
}

// New creates a new instance of Error
func New(message string, code string) *Error {
	e := new(Error)
	e.fill(errors.New(message), code)
	return e
}

// Wrap wraps an error within an instance of Error
func Wrap(existingError error, message string, code string) *Error {
	e := new(Error)
	e.fill(errors.Wrap(existingError, message), code)
	return e
}

// Error object behaves like native golang error
func (e *Error) Error() string {
	return e.Message
}

// ToJSONBody wraps Error within JSONBody
func (e *Error) ToJSONBody() *JSONBody {
	j := new(JSONBody)
	j.ErrorData = e
	return j
}

// JSONBody also behaves like native golang error
func (e *JSONBody) Error() string {
	if e.ErrorData == nil {
		return ""
	}
	return e.ErrorData.Error()
}

// Utility function to fill out Error fields
func (e *Error) fill(wrappedError error, code string) {
	e.WrappedError = wrappedError
	e.Message = wrappedError.Error()
	e.Code = code

	if err, ok := wrappedError.(stackTracer); ok {
		// Pop the first frame which is always errors.go
		// We want the real code that calls .New
		frames := []errors.Frame(err.StackTrace())[1:]

		// Convert to slice of string
		e.StackTrace = make([]string, len(frames))
		for idx, frame := range frames {
			frameInBytes, _ := frame.MarshalText()
			e.StackTrace[idx] = string(frameInBytes)
		}
	}
}
