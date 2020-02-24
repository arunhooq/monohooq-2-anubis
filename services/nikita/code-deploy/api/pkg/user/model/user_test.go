package model_test

import (
	"bytes"
	"encoding/json"
	"testing"
	"time"

	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/user/model"
	"github.com/stretchr/testify/require"
)

func TestUserCreateRequest(t *testing.T) {
	type testCase struct {
		name           string
		testedUser     model.UserCreateRequest
		expectedResult string
	}
	testCases := []testCase{
		{
			"JSONWithCompleteDataMetaFields",
			model.UserCreateRequest{
				Data: &model.UserCreateRequestData{
					Email:             "test@example.com",
					PhoneNumber:       "0123456",
					Country:           "SG",
					RefEvSpAccountID:  "0123456sp",
					RefEvCpCustomerID: "0123456cp",
				},
			},
			`{
	"data": {
		"email": "test@example.com",
		"phone_number": "0123456",
		"country": "SG",
		"ref_ev_sp_account_id": "0123456sp",
		"ref_ev_cp_customer_id": "0123456cp"
	}
}
`,
		},
		{
			"JSONWithIncompleteDataMetaFields",
			model.UserCreateRequest{
				Data: &model.UserCreateRequestData{},
			},
			`{
	"data": {
		"email": "",
		"phone_number": "",
		"country": "",
		"ref_ev_sp_account_id": "",
		"ref_ev_cp_customer_id": ""
	}
}
`,
		},
		{
			"JSONWithoutDataMeta",
			model.UserCreateRequest{},
			`{
	"data": null
}
`,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			var buffer bytes.Buffer
			encoder := json.NewEncoder(&buffer)
			encoder.SetIndent("", "\t")
			encoder.Encode(tc.testedUser)
			require.Equal(t, tc.expectedResult, buffer.String())
		})
	}
}

func TestUserCreateRequestValidate(t *testing.T) {
	type testCase struct {
		name           string
		testedUser     model.UserCreateRequest
		expectedResult bool
	}
	testCases := []testCase{
		{
			"BareMinimum",
			model.UserCreateRequest{
				Data: &model.UserCreateRequestData{
					Email:   "test@example.com",
					Country: "SG",
				},
			},
			true,
		},
		{
			"BareMinimumWithPhoneNumber",
			model.UserCreateRequest{
				Data: &model.UserCreateRequestData{
					PhoneNumber: "0123456",
					Country:     "SG",
				},
			},
			true,
		},
		{
			"WithoutData",
			model.UserCreateRequest{},
			false,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			isValid := tc.testedUser.Validate()
			require.Equal(t, tc.expectedResult, isValid)
		})
	}
}

func TestUserCreateResponse(t *testing.T) {
	id := uuid.New().String()
	now := time.Now().UTC()
	nowString := now.Format(time.RFC3339Nano)
	type testCase struct {
		name           string
		testedUser     model.UserCreateResponse
		expectedResult string
	}
	testCases := []testCase{
		{
			"JSONWithCompleteDataFields",
			model.UserCreateResponse{
				Data: &model.User{
					ID:                id,
					Email:             "test@example.com",
					PhoneNumber:       "0123456",
					Country:           "SG",
					RefEvSpAccountID:  "0123456sp",
					RefEvCpCustomerID: "0123456cp",
					InsertedAt:        &now,
					UpdatedAt:         &now,
					DeletedAt:         &now,
				},
			},
			`{
	"data": {
		"id": "` + id + `",
		"email": "test@example.com",
		"phone_number": "0123456",
		"country": "SG",
		"ref_ev_sp_account_id": "0123456sp",
		"ref_ev_cp_customer_id": "0123456cp",
		"inserted_at": "` + nowString + `",
		"updated_at": "` + nowString + `",
		"deleted_at": "` + nowString + `"
	}
}
`,
		},
		{
			"JSONWithIncompleteDataFields",
			model.UserCreateResponse{
				Data: &model.User{},
			},
			`{
	"data": {
		"id": "",
		"email": "",
		"phone_number": "",
		"country": "",
		"ref_ev_sp_account_id": "",
		"ref_ev_cp_customer_id": "",
		"inserted_at": null,
		"updated_at": null,
		"deleted_at": null
	}
}
`,
		},
		{
			"JSONWithoutData",
			model.UserCreateResponse{},
			`{
	"data": null
}
`,
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			var buffer bytes.Buffer
			encoder := json.NewEncoder(&buffer)
			encoder.SetIndent("", "\t")
			encoder.Encode(tc.testedUser)
			require.Equal(t, tc.expectedResult, buffer.String())
		})
	}
}
