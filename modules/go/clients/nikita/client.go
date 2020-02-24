package nikita

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"strconv"
	"strings"
	"time"

	"github.com/hashicorp/go-cleanhttp"
	"github.com/hooqtv/monohooq/modules/go/constant"
)

// NewClient instantiates nikita api client
func NewClient(baseURL string, apiKey string) Client {
	if strings.TrimSpace(baseURL) == "" {
		panic("nikita_api base_url is empty")
	}

	if strings.TrimSpace(apiKey) == "" {
		panic("nikita_api api_key is empty")
	}

	return client{
		baseURL:    strings.TrimSpace(baseURL),
		apiKey:     strings.TrimSpace(apiKey),
		httpClient: cleanhttp.DefaultClient(),
	}
}

type client struct {
	baseURL    string
	apiKey     string
	httpClient *http.Client
}

type clientContext struct {
	inRequestID  string
	inTimestamp  time.Time
	outRequestID string
	outTimestamp time.Time
	client       client
}

func (c client) context(requestID string) clientContext {
	return clientContext{
		inRequestID: requestID,
		inTimestamp: time.Now().UTC(),
		client:      c,
	}
}

func (c client) AdminCreateUser(req AdminCreateUserRequest) (*AdminCreateUserResponse, error) {
	url := fmt.Sprintf("%s/admin/users", c.baseURL)

	var resp AdminCreateUserResponse
	ctx := c.context(req.Tracing.RequestID)
	if err := ctx.send(http.MethodPost, url, &req, &resp); err != nil {
		return nil, err
	}

	resp.Tracing.RequestID = ctx.outRequestID
	resp.Tracing.Timestamp = ctx.outTimestamp
	return &resp, nil
}

func (c client) AdminCreateSubscription(req AdminCreateSubscriptionRequest) (*AdminCreateSubscriptionResponse, error) {
	url := fmt.Sprintf("%s/admin/subscriptions", c.baseURL)

	var resp AdminCreateSubscriptionResponse
	ctx := c.context(req.Tracing.RequestID)
	if err := ctx.send(http.MethodPost, url, &req, &resp); err != nil {
		return nil, err
	}

	resp.Tracing.RequestID = ctx.outRequestID
	resp.Tracing.Timestamp = ctx.outTimestamp
	return &resp, nil
}

func (ctx *clientContext) send(method string, url string, request interface{}, response interface{}) error {
	// build body
	buff := bytes.NewBuffer(nil)
	if request != nil {
		if err := json.NewEncoder(buff).Encode(request); err != nil {
			return ctx.newErrorResponse(ErrCodeEncodeRequestBodyFailure, err)
		}
	}

	// build request
	req, err := http.NewRequest(method, url, buff)
	if err != nil {
		return ctx.newErrorResponse(ErrCodeCreateRequestFailure, err)
	}

	// add headers
	req.Header.Add("Content-Type", "application/json")
	req.Header.Add("User-Agent", fmt.Sprintf("monohooq/nikita %s", constant.AppTag()))
	req.Header.Add("Content-Length", strconv.Itoa(buff.Len()))
	req.Header.Add("X-Api-Key", ctx.client.apiKey)
	req.Header.Add("apikey", ctx.client.apiKey)
	req.Header.Add("X-Request-ID", ctx.inRequestID)

	// send request
	resp, err := ctx.client.httpClient.Do(req)
	if err != nil {
		return ctx.newErrorResponse(ErrCodeHTTPCallFailure, err)
	}

	// read response
	defer func() { _ = resp.Body.Close() }()

	// read response body
	payload, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return ctx.newErrorResponse(ErrCodeReadResponseBodyFailure, err)
	}

	// read response metadata
	serverTime, _ := time.Parse(time.RFC3339Nano, resp.Header.Get("X-Server-Time"))
	ctx.outRequestID = resp.Header.Get("X-Request-ID")
	ctx.outTimestamp = serverTime

	// parse response
	switch resp.StatusCode {
	case http.StatusOK, http.StatusCreated:
		if response == nil {
			return nil
		}

		if err := json.Unmarshal(payload, response); err != nil {
			return ctx.newDecodeErrorResponse(err, payload)
		}
	default:
		var errResp ErrorResponse
		if err := json.Unmarshal(payload, &errResp); err != nil {
			return ctx.newDecodeErrorResponse(err, payload)
		}
		ctx.traceErrorResponse(&errResp)

		return errResp
	}

	return nil
}

func (ctx *clientContext) traceErrorResponse(err *ErrorResponse) {
	if ctx.outRequestID != "" {
		err.Tracing.RequestID = ctx.outRequestID
	} else {
		err.Tracing.RequestID = ctx.inRequestID
	}
	if !ctx.outTimestamp.IsZero() {
		err.Tracing.Timestamp = ctx.outTimestamp
	} else {
		err.Tracing.Timestamp = ctx.inTimestamp
	}
}

// newErrorResponse wraps NewErrorResponse with tracing data that has been set in context
func (ctx *clientContext) newErrorResponse(code string, err error) ErrorResponse {
	e := NewErrorResponse(code, err)
	ctx.traceErrorResponse(&e)
	return e
}

// newDecodeErrorResponse also sets payload for debugging payload issue
func (ctx *clientContext) newDecodeErrorResponse(err error, payload []byte) ErrorResponse {
	e := ctx.newErrorResponse(ErrCodeDecodeResponseBodyFailure, err)
	e.Payload = string(payload)
	return e
}
