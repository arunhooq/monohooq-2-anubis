package errors

import (
	"encoding/json"
	"fmt"
	"testing"

	"github.com/stretchr/testify/require"
)

func TestNewError(t *testing.T) {
	e := New("This is an error", "CURRY-123")

	require.Equal(t, "This is an error", e.Message)
	require.Equal(t, "CURRY-123", e.Code)
	require.NotNil(t, e.WrappedError)
	require.NotNil(t, e.StackTrace)

	// Is still considered golang error
	require.EqualError(t, e, "This is an error")

	// WrappedError is also considered golang error
	require.EqualError(t, e.WrappedError, "This is an error")

	// Make sure stack frames begin with this test
	require.Contains(t, e.StackTrace[0], "errors.TestNewError")
	require.Contains(t, e.StackTrace[0], "errors_test.go")
}

func TestWrapError(t *testing.T) {
	existingError := fmt.Errorf("Something happened")
	e := Wrap(existingError, "This is an error", "CURRY-123")

	require.Equal(t, "This is an error: Something happened", e.Message)
	require.Equal(t, "CURRY-123", e.Code)
	require.NotNil(t, e.WrappedError)
	require.NotNil(t, e.StackTrace)

	// Is still considered golang error
	require.EqualError(t, e, "This is an error: Something happened")

	// WrappedError is also considered golang error
	require.EqualError(t, e.WrappedError, "This is an error: Something happened")

	// Make sure stack frames begin with this test
	require.Contains(t, e.StackTrace[0], "errors.TestWrapError")
	require.Contains(t, e.StackTrace[0], "errors_test.go")
}

func TestJSONBody(t *testing.T) {
	existingError := fmt.Errorf("Something happened")
	e := Wrap(existingError, "This is an error", "CURRY-123").ToJSONBody()

	require.EqualError(t, e, "This is an error: Something happened")

	marshalled, err := json.Marshal(e)
	require.NoError(t, err)

	var unmarshalled map[string]map[string]interface{}
	err = json.Unmarshal(marshalled, &unmarshalled)
	require.NoError(t, err)
	require.NotEmpty(t, unmarshalled["error"])
	require.Equal(t, "This is an error: Something happened", unmarshalled["error"]["message"])
	require.Equal(t, "CURRY-123", unmarshalled["error"]["code"])
	require.NotEmpty(t, unmarshalled["error"]["stack_trace"])

	// Can be unmarshalled back to JSONBody
	var unmarshalled2 JSONBody
	err = json.Unmarshal(marshalled, &unmarshalled2)
	require.NoError(t, err)
	require.Equal(t, e.ErrorData.Code, unmarshalled2.ErrorData.Code)
	require.Equal(t, e.ErrorData.Message, unmarshalled2.ErrorData.Message)
	require.Equal(t, e.ErrorData.StackTrace, unmarshalled2.ErrorData.StackTrace)
}
