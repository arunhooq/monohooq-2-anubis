package evergent

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"strconv"
	"strings"
	"time"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/hashicorp/go-cleanhttp"
	"github.com/hooqtv/monohooq/modules/go/constant"
	"github.com/hooqtv/monohooq/modules/go/errors"
)

type client struct {
	namespace  string // ev2, hooq
	baseURL    string
	partnerID  string // business-unit code (HAWK, HOOQINDO, ...)
	username   string
	password   string
	httpClient *http.Client
}

// NewEV2Client instantiates evergent ev2 client
func NewEV2Client(baseURL string, partnerID string, username string, password string) Client {
	return NewClient("ev2", baseURL, partnerID, username, password)
}

// NewClient instantiates evergent client
func NewClient(namespace string, baseURL string, partnerID string, username string, password string) Client {
	if strings.TrimSpace(namespace) == "" {
		panic("namespace is empty")
	}

	if strings.TrimSpace(baseURL) == "" {
		panic("base_url is empty")
	}

	if strings.TrimSpace(partnerID) == "" {
		panic("partner_id is empty")
	}

	if strings.TrimSpace(username) == "" {
		panic("username is empty")
	}

	if strings.TrimSpace(password) == "" {
		panic("password is empty")
	}

	return client{
		namespace:  namespace,
		baseURL:    strings.TrimSpace(baseURL),
		partnerID:  strings.TrimSpace(partnerID),
		username:   strings.TrimSpace(username),
		password:   strings.TrimSpace(password),
		httpClient: cleanhttp.DefaultClient(),
	}
}

func (ctx client) GetUserDetails(req GetUserDetailsRequest) (*GetUserDetailsResponse, error) {
	url := fmt.Sprintf("%s/%s/getUserDetails", ctx.baseURL, ctx.namespace)
	request := ev2GetUserDetailsRequest{
		Message: ev2GetUserDetailsRequestMessage{
			ChannelPartnerID:  ctx.partnerID,
			APIUser:           ctx.username,
			APIPassword:       ctx.password,
			SpAccountID:       req.SpAccountID,
			ReturnCountryCode: true,
		},
	}

	var response ev2GetUserDetailsResponse
	if err := ctx.send(http.MethodPost, url, request, &response); err != nil {
		return nil, err
	}

	if response.Message.ResponseCode != 1 {
		if len(response.Message.FailureMessages) == 0 {
			err := fmt.Errorf("unexpected length of GetUserDetailsResponseMessage.failureMessage")
			return nil, NewErrorResponse(ErrCodeInvalidResponseBody, err)
		}

		errMsg := response.Message.FailureMessages[0]
		return nil, ErrorResponse{
			ErrorObj: *errors.New(errMsg.ErrorMessage, errMsg.ErrorCode),
		}
	}

	message := response.Message
	return &GetUserDetailsResponse{
		IsAnonymized: message.IsAnonymized,
		CpCustomerID: message.CpCustomerID,
		SpAccountID:  message.SpAccountID,
		Username:     message.Username,
		FirstName:    message.FirstName,
		LastName:     message.LastName,
		Email:        message.Email,
		PhoneNumber:  message.PhoneNumber,
		Country:      message.Country,
	}, nil
}

func (ctx client) GetEntitlements(req GetEntitlementsRequest) (*GetEntitlementsResponse, error) {
	url := fmt.Sprintf("%s/%s/getEntitlements", ctx.baseURL, ctx.namespace)
	request := ev2GetEntitlementsRequest{
		Message: ev2GetEntitlementsRequestMessage{
			ChannelPartnerID:   ctx.partnerID,
			APIUser:            ctx.username,
			APIPassword:        ctx.password,
			SpAccountID:        req.SpAccountID,
			ReturnAdsInfo:      true,
			ReturnOrderDetails: true,
			ReturnTVOD:         "T",
		},
	}

	var response ev2GetEntitlementsResponse
	if err := ctx.send(http.MethodPost, url, request, &response); err != nil {
		return nil, err
	}

	if response.Message.ResponseCode != 1 {
		if len(response.Message.FailureMessages) == 0 {
			err := fmt.Errorf("unexpected length of GetEntitlementsResponseMessage.failureMessage")
			return nil, NewErrorResponse(ErrCodeInvalidResponseBody, err)
		}

		errMsg := response.Message.FailureMessages[0]
		return nil, ErrorResponse{
			ErrorObj: *errors.New(errMsg.ErrorMessage, errMsg.ErrorCode),
		}
	}

	message := response.Message

	entitlements := make([]Entitlement, 0)
	for _, e := range message.AccountServiceMessages {
		obj := Entitlement{
			TransactionID: e.transactionID(),
			ServiceID:     e.ServiceID,
			ServiceName:   e.ServiceName,
			Name:          e.Name,
			Description:   e.Description,
			TypeOfService: e.TypeOfService,
			Status:        e.Status,
			IsRenewable:   e.IsRenewable,
			MetadataAds: &EntitlementMetadataAds{
				HasPrerollAds:      e.HasPrerollAds,
				HasInterstitialAds: e.HasInterstitialAds,
				HasBannerAds:       e.HasBannerAds,
			},
		}

		if e.StartDateInMs > 0 {
			obj.StartedAt = aws.Time(time.Unix(0, e.StartDateInMs*int64(time.Millisecond)))
		}

		if e.ValidityTillInMs > 0 {
			obj.ExpiredAt = aws.Time(time.Unix(0, e.ValidityTillInMs*int64(time.Millisecond)))
		}

		if e.HasVodMetadata && len(e.VodItems) > 0 {
			item := e.VodItems[0]
			obj.MetadataTvod = &EntitlementMetadataTvod{
				TitleID:            item.AssetID,
				Title:              item.Title,
				ViewingPeriodHours: item.ViewingPeriodHours,
				RentalPeriodHours:  item.RentalPeriodHours,
			}
		}

		entitlements = append(entitlements, obj)
	}
	return &GetEntitlementsResponse{
		Entitlements: entitlements,
	}, nil
}

func (ctx client) send(method string, url string, request interface{}, response interface{}) error {
	// build body
	buff := bytes.NewBuffer(nil)
	if request != nil {
		if err := json.NewEncoder(buff).Encode(request); err != nil {
			return NewErrorResponse(ErrCodeEncodeRequestBodyFailure, err)
		}
	}

	// build request
	req, err := http.NewRequest(method, url, buff)
	if err != nil {
		return NewErrorResponse(ErrCodeCreateRequestFailure, err)
	}

	// add headers
	req.Header.Add("Content-Type", "application/json")
	req.Header.Add("User-Agent", fmt.Sprintf("monohooq/evergent %s", constant.AppTag()))
	req.Header.Add("Content-Length", strconv.Itoa(buff.Len()))

	// send request
	resp, err := ctx.httpClient.Do(req)
	if err != nil {
		return NewErrorResponse(ErrCodeHTTPCallFailure, err)
	}

	// read response
	defer func() { _ = resp.Body.Close() }()

	// read response body
	payload, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return NewErrorResponse(ErrCodeReadResponseBodyFailure, err)
	}

	// parse response
	switch resp.StatusCode {
	case http.StatusOK, http.StatusCreated:
		if response == nil {
			return nil
		}

		if err := json.Unmarshal(payload, response); err != nil {
			return NewErrorResponse(ErrCodeDecodeResponseBodyFailure, err)
		}
	default:
		var errResp ErrorResponse
		if err := json.Unmarshal(payload, &errResp); err != nil {
			return NewErrorResponse(ErrCodeDecodeResponseBodyFailure, err)
		}

		return errResp
	}

	return nil
}
