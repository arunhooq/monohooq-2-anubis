package nikita

import (
	"bytes"
	"encoding/json"
	"strings"
	"testing"
	"time"

	"github.com/stretchr/testify/require"
)

func TestAdminCreateSubscriptionRequest(t *testing.T) {
	now := time.Now().UTC().Truncate(time.Second)

	t.Run("Encode", func(t *testing.T) {
		obj := AdminCreateSubscriptionRequest{
			Data: &CreateSubscriptionData{
				TransactionID: "transaction_id",
				Status:        "subscription_status",
				MetadataProduct: &SubscriptionMetadataProduct{
					SKU:    "sku",
					IsTVOD: true,
					MetadataTVOD: &ProductMetadataTVOD{
						RentalPeriodHours:  1,
						ViewingPeriodHours: 2,
					},
					MetadataAds: &ProductMetadataAds{
						HasPrerollAds:      true,
						HasInterstitialAds: true,
						HasBannerAds:       true,
					},
				},
				MetadataTVOD: &SubscriptionMetadataTVOD{
					TitleID: "title_id",
				},
				StartedAt: &now,
				ExpiredAt: &now,
			},
			Meta: &CreateSubscriptionMeta{
				LinkToUser: &LinkToUser{
					ID:               "user_id",
					RefEvSpAccountID: "sp_account_id",
				},
				UpsertOnConflict: true,
			},
		}

		buff := bytes.NewBuffer(nil)
		encoder := json.NewEncoder(buff)
		encoder.SetIndent("", "  ")
		require.NoError(t, encoder.Encode(obj))

		encoded := buff.String()
		require.NotEmpty(t, encoded)
		t.Logf("encoded AdminCreateSubscriptionRequest: \n%s", encoded)
	})

	t.Run("Decode", func(t *testing.T) {
		payload := `{
              "data": {
                "transaction_id": "transaction_id",
                "status": "subscription_status",
                "metadata_product": {
                  "sku": "sku",
                  "is_tvod": true,
                  "metadata_tvod": {
                    "rental_period_hours": 1,
                    "viewing_period_hours": 2
                  },
                  "metadata_ads": {
                    "has_preroll_ads": true,
                    "has_interstitial_ads": true,
                    "has_banner_ads": true
                  }
                },
                "metadata_tvod": {
                  "title_id": "title_id"
                },
                "started_at": "2020-02-19T03:57:46Z",
                "expired_at": "2020-02-19T03:57:46Z"
              },
              "meta": {
                "link_to_user": {
                  "id": "user_id",
                  "ref_ev_sp_account_id": "sp_account_id"
                },
                "upsert_on_conflict": true
              }
            }`

		var obj AdminCreateSubscriptionRequest
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, "transaction_id", obj.Data.TransactionID)
		require.Equal(t, "subscription_status", obj.Data.Status)
		require.Equal(t, "sku", obj.Data.MetadataProduct.SKU)
		require.Equal(t, true, obj.Data.MetadataProduct.IsTVOD)
		require.Equal(t, 1, obj.Data.MetadataProduct.MetadataTVOD.RentalPeriodHours)
		require.Equal(t, 2, obj.Data.MetadataProduct.MetadataTVOD.ViewingPeriodHours)
		require.Equal(t, true, obj.Data.MetadataProduct.MetadataAds.HasPrerollAds)
		require.Equal(t, true, obj.Data.MetadataProduct.MetadataAds.HasInterstitialAds)
		require.Equal(t, true, obj.Data.MetadataProduct.MetadataAds.HasBannerAds)
		require.Equal(t, "title_id", obj.Data.MetadataTVOD.TitleID)
		require.Equal(t, int64(1582084666), obj.Data.StartedAt.Unix())
		require.Equal(t, int64(1582084666), obj.Data.ExpiredAt.Unix())
		require.Equal(t, "user_id", obj.Meta.LinkToUser.ID)
		require.Equal(t, "sp_account_id", obj.Meta.LinkToUser.RefEvSpAccountID)
		require.Equal(t, true, obj.Meta.UpsertOnConflict)
	})
}

func TestAdminCreateSubscriptionResponse(t *testing.T) {

	now := time.Now().UTC().Truncate(time.Second)
	t.Run("Encode", func(t *testing.T) {
		obj := AdminCreateSubscriptionResponse{
			Data: &Subscription{
				ID:            "subscription_id",
				UserID:        "user_id",
				TransactionID: "transaction_id",
				Status:        "subscription_status",
				MetadataProduct: &SubscriptionMetadataProduct{
					SKU:    "sku",
					IsTVOD: true,
					MetadataTVOD: &ProductMetadataTVOD{
						RentalPeriodHours:  1,
						ViewingPeriodHours: 2,
					},
					MetadataAds: &ProductMetadataAds{
						HasPrerollAds:      true,
						HasInterstitialAds: true,
						HasBannerAds:       true,
					},
				},
				MetadataTVOD: &SubscriptionMetadataTVOD{
					TitleID: "title_id",
				},
				StartedAt:  &now,
				ExpiredAt:  &now,
				InsertedAt: &now,
				UpdatedAt:  &now,
				DeletedAt:  &now,
			},
		}

		buff := bytes.NewBuffer(nil)
		encoder := json.NewEncoder(buff)
		encoder.SetIndent("", "  ")
		require.NoError(t, encoder.Encode(obj))

		encoded := buff.String()
		require.NotEmpty(t, encoded)
		t.Logf("encoded AdminCreateSubscriptionResponse:\n%s", encoded)
	})
	t.Run("Decode", func(t *testing.T) {
		payload := `{
  "data": {
	"id": "subscription_id",
	"user_id": "user_id",
	"transaction_id": "transaction_id",
	"status": "subscription_status",
	"metadata_product": {
	  "sku": "sku",
	  "is_tvod": true,
	  "metadata_tvod": {
		"rental_period_hours": 1,
		"viewing_period_hours": 2
	  },
	  "metadata_ads": {
		"has_preroll_ads": true,
		"has_interstitial_ads": true,
		"has_banner_ads": true
	  }
	},
	"metadata_tvod": {
	  "title_id": "title_id"
	},
	"started_at": "2020-02-19T04:11:21Z",
	"expired_at": "2020-02-19T04:11:21Z",
	"inserted_at": "2020-02-19T04:11:21Z",
	"updated_at": "2020-02-19T04:11:21Z",
	"deleted_at": "2020-02-19T04:11:21Z"
  }
}`
		var obj AdminCreateSubscriptionResponse
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, "subscription_id", obj.Data.ID)
		require.Equal(t, "user_id", obj.Data.UserID)
		require.Equal(t, "transaction_id", obj.Data.TransactionID)
		require.Equal(t, "subscription_status", obj.Data.Status)
		require.Equal(t, "sku", obj.Data.MetadataProduct.SKU)
		require.Equal(t, true, obj.Data.MetadataProduct.IsTVOD)
		require.Equal(t, 1, obj.Data.MetadataProduct.MetadataTVOD.RentalPeriodHours)
		require.Equal(t, 2, obj.Data.MetadataProduct.MetadataTVOD.ViewingPeriodHours)
		require.Equal(t, true, obj.Data.MetadataProduct.MetadataAds.HasPrerollAds)
		require.Equal(t, true, obj.Data.MetadataProduct.MetadataAds.HasInterstitialAds)
		require.Equal(t, true, obj.Data.MetadataProduct.MetadataAds.HasBannerAds)
		require.Equal(t, "title_id", obj.Data.MetadataTVOD.TitleID)
		require.Equal(t, int64(1582085481), obj.Data.StartedAt.Unix())
		require.Equal(t, int64(1582085481), obj.Data.ExpiredAt.Unix())
		require.Equal(t, int64(1582085481), obj.Data.InsertedAt.Unix())
		require.Equal(t, int64(1582085481), obj.Data.UpdatedAt.Unix())
		require.Equal(t, int64(1582085481), obj.Data.DeletedAt.Unix())
	})
}

func TestAdminCreateUserRequest(t *testing.T) {
	t.Run("Encode", func(t *testing.T) {
		obj := AdminCreateUserRequest{
			Data: &CreateUserData{
				Email:             "email",
				PhoneNumber:       "phone_number",
				Country:           "country",
				RefEvSpAccountID:  "sp_account_id",
				RefEvCpCustomerID: "cp_customer_id",
			},
		}

		buff := bytes.NewBuffer(nil)
		encoder := json.NewEncoder(buff)
		encoder.SetIndent("", "  ")
		require.NoError(t, encoder.Encode(obj))

		encoded := buff.String()
		require.NotEmpty(t, encoded)
		t.Logf("encoded AdminCreateUserRequest:\n%s", encoded)
	})

	t.Run("Decode", func(t *testing.T) {
		payload := `{
  "data": {
	"email": "email",
	"phone_number": "phone_number",
	"country": "country",
	"ref_ev_sp_account_id": "sp_account_id",
	"ref_ev_cp_customer_id": "cp_customer_id"
  }
}`
		var obj AdminCreateUserRequest
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, "email", obj.Data.Email)
		require.Equal(t, "phone_number", obj.Data.PhoneNumber)
		require.Equal(t, "country", obj.Data.Country)
		require.Equal(t, "sp_account_id", obj.Data.RefEvSpAccountID)
		require.Equal(t, "cp_customer_id", obj.Data.RefEvCpCustomerID)
	})
}

func TestAdminCreateUserResponse(t *testing.T) {
	t.Run("Encode", func(t *testing.T) {
		now := time.Now().UTC().Truncate(time.Second)
		obj := AdminCreateUserResponse{
			Data: &User{
				ID:                "user_id",
				Email:             "email",
				PhoneNumber:       "phone_number",
				Country:           "country",
				RefEvSpAccountID:  "sp_account_id",
				RefEvCpCustomerID: "cp_customer_id",
				InsertedAt:        &now,
				UpdatedAt:         &now,
				DeletedAt:         &now,
			},
		}

		buff := bytes.NewBuffer(nil)
		encoder := json.NewEncoder(buff)
		encoder.SetIndent("", "  ")
		require.NoError(t, encoder.Encode(obj))

		encoded := buff.String()
		require.NotEmpty(t, encoded)
		t.Logf("encoded AdminCreateUserResponse:\n%s", encoded)
	})

	t.Run("Decode", func(t *testing.T) {
		payload := `{
  "data": {
	"id": "user_id",
	"email": "email",
	"phone_number": "phone_number",
	"country": "country",
	"ref_ev_sp_account_id": "sp_account_id",
	"ref_ev_cp_customer_id": "cp_customer_id",
	"inserted_at": "2020-02-19T04:23:15Z",
	"updated_at": "2020-02-19T04:23:15Z",
	"deleted_at": "2020-02-19T04:23:15Z"
  }
}`
		var obj AdminCreateUserResponse
		require.NoError(t, json.NewDecoder(strings.NewReader(payload)).Decode(&obj))
		require.Equal(t, "user_id", obj.Data.ID)
		require.Equal(t, "email", obj.Data.Email)
		require.Equal(t, "phone_number", obj.Data.PhoneNumber)
		require.Equal(t, "country", obj.Data.Country)
		require.Equal(t, "sp_account_id", obj.Data.RefEvSpAccountID)
		require.Equal(t, "cp_customer_id", obj.Data.RefEvCpCustomerID)
		require.Equal(t, int64(1582086195), obj.Data.InsertedAt.Unix())
		require.Equal(t, int64(1582086195), obj.Data.UpdatedAt.Unix())
		require.Equal(t, int64(1582086195), obj.Data.DeletedAt.Unix())
	})
}
