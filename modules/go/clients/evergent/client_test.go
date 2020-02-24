package evergent

import (
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/stretchr/testify/require"
)

func TestNewClient(t *testing.T) {
	t.Run("PanicsWhenNamespaceIsEmpty", func(t *testing.T) {
		require.Panics(t, func() {
			NewClient("", "base_url", "partner_id", "username", "password")
		})
	})

	t.Run("PanicsWhenBaseURLIsEmpty", func(t *testing.T) {
		require.Panics(t, func() {
			NewClient("namespace", "", "partner_id", "username", "password")
		})
	})

	t.Run("PanicsWhenPartnerIDIsEmpty", func(t *testing.T) {
		require.Panics(t, func() {
			NewClient("namespace", "base_url", "", "username", "password")
		})
	})

	t.Run("PanicsWhenUsernameIsEmpty", func(t *testing.T) {
		require.Panics(t, func() {
			NewClient("namespace", "base_url", "partner_id", "", "password")
		})
	})

	t.Run("PanicsWhenPasswordIsEmpty", func(t *testing.T) {
		require.Panics(t, func() {
			NewClient("namespace", "base_url", "partner_id", "username", "")
		})
	})

	t.Run("ReturnNonNilInstance", func(t *testing.T) {
		require.NotNil(t, NewClient("namespace", "base_url", "partner_id", "username", "password"))
	})
}

func TestClient_GetUserDetails(t *testing.T) {
	t.Run("EV2", func(t *testing.T) {
		t.Run("Success", func(t *testing.T) {
			server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
				require.Equal(t, http.MethodPost, r.Method)
				require.Equal(t, "/ev2/getUserDetails", r.RequestURI)

				req := ev2GetUserDetailsRequest{}
				require.NoError(t, json.NewDecoder(r.Body).Decode(&req))
				require.Equal(t, "partner_id", req.Message.ChannelPartnerID)
				require.Equal(t, "username", req.Message.APIUser)
				require.Equal(t, "password", req.Message.APIPassword)
				require.Equal(t, "sp_account_id", req.Message.SpAccountID)

				payload := `{
  "GetUserDetailsResponseMessage": {
    "anonymized": false,
    "responseCode": "1",
    "status": "SUCCESS",
    "firstName": "Guest",
    "lastName": "User",
    "email": "ken+id@hooq.tv",
    "customerUserName": "ken+id@hooq.tv",
    "cpCustomerID": "171212102009056",
    "spAccountID": "e9c349f9-8200-4227-b755-5d7eacc1fd22",
    "country": "ID"
  }
}`
				w.WriteHeader(http.StatusOK)
				_, _ = w.Write([]byte(payload))
			}))
			defer server.Close()

			c := NewEV2Client(server.URL, "partner_id", "username", "password")
			resp, err := c.GetUserDetails(GetUserDetailsRequest{
				SpAccountID: "sp_account_id",
			})
			require.NoError(t, err)
			require.NotNil(t, resp)
			require.Equal(t, "e9c349f9-8200-4227-b755-5d7eacc1fd22", resp.SpAccountID)
			require.Equal(t, "171212102009056", resp.CpCustomerID)
			require.Equal(t, "ken+id@hooq.tv", resp.Username)
			require.Equal(t, "Guest", resp.FirstName)
			require.Equal(t, "User", resp.LastName)
			require.Equal(t, "ken+id@hooq.tv", resp.Email)
			require.Equal(t, "", resp.PhoneNumber)
			require.Equal(t, "ID", resp.Country)
		})

		t.Run("Failure", func(t *testing.T) {
			server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
				require.Equal(t, http.MethodPost, r.Method)
				require.Equal(t, "/ev2/getUserDetails", r.RequestURI)

				req := ev2GetUserDetailsRequest{}
				require.NoError(t, json.NewDecoder(r.Body).Decode(&req))
				require.Equal(t, "partner_id", req.Message.ChannelPartnerID)
				require.Equal(t, "username", req.Message.APIUser)
				require.Equal(t, "password", req.Message.APIPassword)
				require.Equal(t, "sp_account_id", req.Message.SpAccountID)

				payload := `{
  "GetUserDetailsResponseMessage": {
    "responseCode": "0",
    "failureMessage": [
      {
        "errorCode": "eV2327",
        "errorMessage": "No account found with the given details."
      }
    ]
  }
}`
				w.WriteHeader(http.StatusOK)
				_, _ = w.Write([]byte(payload))
			}))
			defer server.Close()

			c := NewEV2Client(server.URL, "partner_id", "username", "password")
			resp, err := c.GetUserDetails(GetUserDetailsRequest{
				SpAccountID: "sp_account_id",
			})
			require.Error(t, err)
			require.Nil(t, resp)
			obj, ok := err.(ErrorResponse)
			require.True(t, ok)
			require.Equal(t, "eV2327", obj.ErrorObj.Code)
			require.Equal(t, "No account found with the given details.", obj.ErrorObj.Message)
		})
	})
}

func TestClient_GetEntitlements(t *testing.T) {
	t.Run("EV2", func(t *testing.T) {
		t.Run("Success", func(t *testing.T) {
			server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
				require.Equal(t, http.MethodPost, r.Method)
				require.Equal(t, "/ev2/getEntitlements", r.RequestURI)

				req := ev2GetEntitlementsRequest{}
				require.NoError(t, json.NewDecoder(r.Body).Decode(&req))
				require.Equal(t, "partner_id", req.Message.ChannelPartnerID)
				require.Equal(t, "username", req.Message.APIUser)
				require.Equal(t, "password", req.Message.APIPassword)
				require.Equal(t, "sp_account_id", req.Message.SpAccountID)
				require.Equal(t, "T", req.Message.ReturnTVOD)
				require.Equal(t, true, req.Message.ReturnAdsInfo)
				require.Equal(t, true, req.Message.ReturnOrderDetails)

				payload := `{
  "GetEntitlementsResponseMessage": {
    "responseCode": "1",
    "message": "SUCCESS",
    "AccountServiceMessage": [
      {
        "isContent": false,
        "preRollAdsEnabled": false,
        "interstitialAdsEnabled": false,
        "bannerAdsEnabled": false,
        "serviceName": "IDHooqStaff",
        "serviceID": "ID-HooqStaff",
        "startDate": 1491565496000,
        "description": "IDHooqStaff",
        "status": "Active",
        "orderID": "20344141",
        "externalOrderID": "1234356561",
        "typeOfService": "PayTV",
        "name": "IDHooqStaff",
        "isRenewal": false
      },
      {
        "isContent": true,
        "preRollAdsEnabled": false,
        "interstitialAdsEnabled": false,
        "bannerAdsEnabled": false,
        "serviceName": "Bonus TVOD On Purchase",
        "serviceID": "ID-TICKET-BONUS-TVOD",
        "startDate": 1581304324000,
        "validityTill": 1583896324000,
        "description": "Bonus TVOD On Purchase",
        "validityEndDate": "03/11/2020 03:12:04",
        "status": "Active",
        "orderID": "20344142",
        "externalOrderID": "1234356562",
        "vodItems": [
          {
            "assetId": "47ca7ac9-c45f-4596-badd-1bde4cc7adb5",
            "viewingPeriod": "48",
            "title": "Joker",
            "rentalPeriod": "720"
          }
        ],
        "name": "Bonus TVOD On Purchase",
        "isRenewal": false
      }
    ]
  }
}
`
				w.WriteHeader(http.StatusOK)
				_, _ = w.Write([]byte(payload))
			}))
			defer server.Close()

			c := NewEV2Client(server.URL, "partner_id", "username", "password")
			resp, err := c.GetEntitlements(GetEntitlementsRequest{
				SpAccountID: "sp_account_id",
			})
			require.NoError(t, err)
			require.NotNil(t, resp)

			// first element
			require.Equal(t, false, resp.Entitlements[0].MetadataAds.HasPrerollAds)
			require.Equal(t, false, resp.Entitlements[0].MetadataAds.HasInterstitialAds)
			require.Equal(t, false, resp.Entitlements[0].MetadataAds.HasBannerAds)
			require.Equal(t, false, resp.Entitlements[0].IsRenewable)
			require.Equal(t, "ID-HooqStaff", resp.Entitlements[0].ServiceID)
			require.Equal(t, "IDHooqStaff", resp.Entitlements[0].ServiceName)
			require.Equal(t, "IDHooqStaff", resp.Entitlements[0].Name)
			require.Equal(t, "IDHooqStaff", resp.Entitlements[0].Description)
			require.Equal(t, "Active", resp.Entitlements[0].Status)
			require.Equal(t, "1234356561", resp.Entitlements[0].TransactionID)
			require.Equal(t, "PayTV", resp.Entitlements[0].TypeOfService)
			require.Equal(t, int64(1491565496), resp.Entitlements[0].StartedAt.Unix())
			require.Nil(t, resp.Entitlements[0].ExpiredAt)

			// second element
			require.Equal(t, false, resp.Entitlements[1].MetadataAds.HasPrerollAds)
			require.Equal(t, false, resp.Entitlements[1].MetadataAds.HasInterstitialAds)
			require.Equal(t, false, resp.Entitlements[1].MetadataAds.HasBannerAds)
			require.Equal(t, false, resp.Entitlements[1].IsRenewable)
			require.Equal(t, "ID-TICKET-BONUS-TVOD", resp.Entitlements[1].ServiceID)
			require.Equal(t, "Bonus TVOD On Purchase", resp.Entitlements[1].ServiceName)
			require.Equal(t, "Bonus TVOD On Purchase", resp.Entitlements[1].Name)
			require.Equal(t, "Bonus TVOD On Purchase", resp.Entitlements[1].Description)
			require.Equal(t, "Active", resp.Entitlements[1].Status)
			require.Equal(t, "1234356562", resp.Entitlements[1].TransactionID)
			require.Equal(t, "", resp.Entitlements[1].TypeOfService)
			require.Equal(t, int64(1581304324), resp.Entitlements[1].StartedAt.Unix())
			require.Equal(t, int64(1583896324), resp.Entitlements[1].ExpiredAt.Unix())
			require.Equal(t, "47ca7ac9-c45f-4596-badd-1bde4cc7adb5", resp.Entitlements[1].MetadataTvod.TitleID)
			require.Equal(t, "Joker", resp.Entitlements[1].MetadataTvod.Title)
			require.Equal(t, 48, resp.Entitlements[1].MetadataTvod.ViewingPeriodHours)
			require.Equal(t, 720, resp.Entitlements[1].MetadataTvod.RentalPeriodHours)
		})

		t.Run("Failure", func(t *testing.T) {
			server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
				require.Equal(t, http.MethodPost, r.Method)
				require.Equal(t, "/ev2/getEntitlements", r.RequestURI)

				req := ev2GetEntitlementsRequest{}
				require.NoError(t, json.NewDecoder(r.Body).Decode(&req))
				require.Equal(t, "partner_id", req.Message.ChannelPartnerID)
				require.Equal(t, "username", req.Message.APIUser)
				require.Equal(t, "password", req.Message.APIPassword)
				require.Equal(t, "sp_account_id", req.Message.SpAccountID)

				payload := `{
  "GetEntitlementsResponseMessage": {
    "responseCode": "0",
    "failureMessage": [
      {
        "errorCode": "111111111",
        "errorMessage": "Authentication Failed"
      }
    ]
  }
}`
				w.WriteHeader(http.StatusOK)
				_, _ = w.Write([]byte(payload))
			}))
			defer server.Close()

			c := NewEV2Client(server.URL, "partner_id", "username", "password")
			resp, err := c.GetEntitlements(GetEntitlementsRequest{
				SpAccountID: "sp_account_id",
			})
			require.Error(t, err)
			require.Nil(t, resp)
			obj, ok := err.(ErrorResponse)
			require.True(t, ok)
			require.Equal(t, "111111111", obj.ErrorObj.Code)
			require.Equal(t, "Authentication Failed", obj.ErrorObj.Message)
		})
	})
}
