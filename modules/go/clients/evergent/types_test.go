package evergent

import (
	"encoding/json"
	"strings"
	"testing"

	"github.com/stretchr/testify/require"
)

func TestEv2GetUserDetailsResponse(t *testing.T) {
	t.Run("DecodeFailure", func(t *testing.T) {
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
		var obj ev2GetUserDetailsResponse
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, 0, obj.Message.ResponseCode)
		require.Equal(t, "eV2327", obj.Message.FailureMessages[0].ErrorCode)
		require.Equal(t, "No account found with the given details.", obj.Message.FailureMessages[0].ErrorMessage)
	})

	t.Run("DecodeSuccess", func(t *testing.T) {
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
		var obj ev2GetUserDetailsResponse
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, 1, obj.Message.ResponseCode)
		require.Equal(t, "SUCCESS", obj.Message.Status)
		require.Equal(t, "Guest", obj.Message.FirstName)
		require.Equal(t, "User", obj.Message.LastName)
		require.Equal(t, "ken+id@hooq.tv", obj.Message.Email)
		require.Equal(t, "", obj.Message.PhoneNumber)
		require.Equal(t, "ken+id@hooq.tv", obj.Message.Username)
		require.Equal(t, "171212102009056", obj.Message.CpCustomerID)
		require.Equal(t, "e9c349f9-8200-4227-b755-5d7eacc1fd22", obj.Message.SpAccountID)
		require.Equal(t, "ID", obj.Message.Country)
	})
}

func TestEv2GetEntitlementsResponse(t *testing.T) {
	t.Run("DecodeFailure", func(t *testing.T) {
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
		var obj ev2GetEntitlementsResponse
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, 0, obj.Message.ResponseCode)
		require.Equal(t, "111111111", obj.Message.FailureMessages[0].ErrorCode)
		require.Equal(t, "Authentication Failed", obj.Message.FailureMessages[0].ErrorMessage)
	})

	t.Run("DecodeSuccess", func(t *testing.T) {
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
}`
		var obj ev2GetEntitlementsResponse
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, 1, obj.Message.ResponseCode)
		require.Equal(t, "SUCCESS", obj.Message.Status)

		// first element
		require.Equal(t, false, obj.Message.AccountServiceMessages[0].HasVodMetadata)
		require.Equal(t, false, obj.Message.AccountServiceMessages[0].HasPrerollAds)
		require.Equal(t, false, obj.Message.AccountServiceMessages[0].HasInterstitialAds)
		require.Equal(t, false, obj.Message.AccountServiceMessages[0].HasBannerAds)
		require.Equal(t, false, obj.Message.AccountServiceMessages[0].IsRenewable)
		require.Equal(t, "ID-HooqStaff", obj.Message.AccountServiceMessages[0].ServiceID)
		require.Equal(t, "IDHooqStaff", obj.Message.AccountServiceMessages[0].ServiceName)
		require.Equal(t, "IDHooqStaff", obj.Message.AccountServiceMessages[0].Name)
		require.Equal(t, "IDHooqStaff", obj.Message.AccountServiceMessages[0].Description)
		require.Equal(t, "Active", obj.Message.AccountServiceMessages[0].Status)
		require.Equal(t, "PayTV", obj.Message.AccountServiceMessages[0].TypeOfService)
		require.Equal(t, int64(1491565496000), obj.Message.AccountServiceMessages[0].StartDateInMs)
		require.Equal(t, int64(0), obj.Message.AccountServiceMessages[0].ValidityTillInMs)

		// second element
		require.Equal(t, true, obj.Message.AccountServiceMessages[1].HasVodMetadata)
		require.Equal(t, false, obj.Message.AccountServiceMessages[1].HasPrerollAds)
		require.Equal(t, false, obj.Message.AccountServiceMessages[1].HasInterstitialAds)
		require.Equal(t, false, obj.Message.AccountServiceMessages[1].HasBannerAds)
		require.Equal(t, false, obj.Message.AccountServiceMessages[1].IsRenewable)
		require.Equal(t, "ID-TICKET-BONUS-TVOD", obj.Message.AccountServiceMessages[1].ServiceID)
		require.Equal(t, "Bonus TVOD On Purchase", obj.Message.AccountServiceMessages[1].ServiceName)
		require.Equal(t, "Bonus TVOD On Purchase", obj.Message.AccountServiceMessages[1].Name)
		require.Equal(t, "Bonus TVOD On Purchase", obj.Message.AccountServiceMessages[1].Description)
		require.Equal(t, "Active", obj.Message.AccountServiceMessages[1].Status)
		require.Equal(t, "", obj.Message.AccountServiceMessages[1].TypeOfService)
		require.Equal(t, int64(1581304324000), obj.Message.AccountServiceMessages[1].StartDateInMs)
		require.Equal(t, int64(1583896324000), obj.Message.AccountServiceMessages[1].ValidityTillInMs)
		require.Equal(t, "47ca7ac9-c45f-4596-badd-1bde4cc7adb5", obj.Message.AccountServiceMessages[1].VodItems[0].AssetID)
		require.Equal(t, "Joker", obj.Message.AccountServiceMessages[1].VodItems[0].Title)
		require.Equal(t, 48, obj.Message.AccountServiceMessages[1].VodItems[0].ViewingPeriodHours)
		require.Equal(t, 720, obj.Message.AccountServiceMessages[1].VodItems[0].RentalPeriodHours)
	})
}
