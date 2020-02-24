package nikita

import (
	"encoding/json"
	"net/http"
	"net/http/httptest"
	"reflect"
	"testing"
	"time"

	"github.com/aws/aws-sdk-go/aws"
	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/modules/go/errors"
	"github.com/hooqtv/monohooq/modules/go/tracing"
	"github.com/stretchr/testify/require"
)

func TestNewClient(t *testing.T) {
	t.Run("PanicsWhenBaseUrlIsEmpty", func(t *testing.T) {
		require.Panics(t, func() {
			NewClient("", "api_key")
		})
	})
	t.Run("PanicsWheApiKeyIsEmpty", func(t *testing.T) {
		require.Panics(t, func() {
			NewClient("base_url", "")
		})
	})

	t.Run("ReturnsNonNilValue", func(t *testing.T) {
		require.NotNil(t, NewClient("base_url", "api_key"))
	})
}

func TestClient_CreateUser(t *testing.T) {
	request := AdminCreateUserRequest{
		Data: &CreateUserData{
			Email:             "email",
			PhoneNumber:       "phone_number",
			Country:           "country",
			RefEvSpAccountID:  "sp_account_id",
			RefEvCpCustomerID: "cp_customer_id",
		},
		Tracing: tracing.Tracing{
			RequestID: uuid.New().String(),
			Timestamp: time.Now().UTC().Truncate(time.Millisecond),
		},
	}

	response := `{
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

	t.Run("ReturnsRequestID", func(t *testing.T) {
		server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			require.Equal(t, http.MethodPost, r.Method)
			require.Equal(t, "/admin/users", r.RequestURI)
			require.Equal(t, "api_key", r.Header.Get("x-api-key"))

			var req AdminCreateUserRequest
			require.NoError(t, json.NewDecoder(r.Body).Decode(&req))
			require.Equal(t, "email", req.Data.Email)
			require.Equal(t, "phone_number", req.Data.PhoneNumber)
			require.Equal(t, "country", req.Data.Country)
			require.Equal(t, "sp_account_id", req.Data.RefEvSpAccountID)
			require.Equal(t, "cp_customer_id", req.Data.RefEvCpCustomerID)

			w.Header().Set("X-Request-ID", uuid.New().String())
			w.Header().Set("X-Server-Time", time.Now().UTC().Truncate(time.Millisecond).Format(time.RFC3339))
			w.WriteHeader(http.StatusOK)
			_, _ = w.Write([]byte(response))
		}))
		defer server.Close()

		c := NewClient(server.URL, "api_key")
		resp, err := c.AdminCreateUser(request)
		require.NoError(t, err)
		require.NotNil(t, resp)
		require.Equal(t, "user_id", resp.Data.ID)
		require.Equal(t, "email", resp.Data.Email)
		require.Equal(t, "phone_number", resp.Data.PhoneNumber)
		require.Equal(t, "country", resp.Data.Country)
		require.Equal(t, "sp_account_id", resp.Data.RefEvSpAccountID)
		require.Equal(t, "cp_customer_id", resp.Data.RefEvCpCustomerID)
		require.Equal(t, int64(1582086195), resp.Data.InsertedAt.Unix())
		require.Equal(t, int64(1582086195), resp.Data.UpdatedAt.Unix())
		require.Equal(t, int64(1582086195), resp.Data.DeletedAt.Unix())
		require.NotEmpty(t, resp.Tracing.RequestID)
		require.NotEmpty(t, resp.Tracing.Timestamp)
	})

	t.Run("HandlesError", func(t *testing.T) {
		server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			require.Equal(t, http.MethodPost, r.Method)
			require.Equal(t, "/admin/users", r.RequestURI)
			require.Equal(t, "api_key", r.Header.Get("x-api-key"))

			rsp := ErrorResponse{
				ErrorObj: errors.Error{
					Message: "blah",
					Code:    "NKT-001",
				},
			}

			w.Header().Set("X-Request-ID", uuid.New().String())
			w.Header().Set("X-Server-Time", time.Now().UTC().Truncate(time.Millisecond).Format(time.RFC3339))
			w.WriteHeader(http.StatusForbidden)
			_ = json.NewEncoder(w).Encode(rsp)
		}))
		defer server.Close()

		c := NewClient(server.URL, "api_key")
		resp, err := c.AdminCreateUser(request)
		require.Error(t, err)
		require.Nil(t, resp)
		obj, ok := err.(ErrorResponse)
		require.True(t, ok, reflect.TypeOf(err).String())
		require.Equal(t, "NKT-001", obj.ErrorObj.Code)
		require.Equal(t, "blah", obj.ErrorObj.Message)
		require.NotEmpty(t, obj.Tracing.RequestID)
		require.NotEmpty(t, obj.Tracing.Timestamp)
	})
}

func TestClient_CreateSubscription(t *testing.T) {
	now := time.Now().UTC().Truncate(time.Second)
	request := AdminCreateSubscriptionRequest{
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
			StartedAt: aws.Time(now),
			ExpiredAt: aws.Time(now),
		},
		Meta: &CreateSubscriptionMeta{
			LinkToUser: &LinkToUser{
				ID:               "user_id",
				RefEvSpAccountID: "sp_account_id",
			},
			UpsertOnConflict: true,
		},
		Tracing: tracing.Tracing{
			RequestID: uuid.New().String(),
			Timestamp: now,
		},
	}

	response := `{
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
                "started_at": "2020-02-19T06:19:19Z",
                "expired_at": "2020-02-19T06:19:19Z",
                "inserted_at": "2020-02-19T06:19:19Z",
                "updated_at": "2020-02-19T06:19:19Z",
                "deleted_at": "2020-02-19T06:19:19Z"
              }
            }`

	t.Run("ReturnsRequestID", func(t *testing.T) {
		server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			require.Equal(t, http.MethodPost, r.Method)
			require.Equal(t, "/admin/subscriptions", r.RequestURI)
			require.Equal(t, "api_key", r.Header.Get("x-api-key"))

			var req AdminCreateSubscriptionRequest
			require.NoError(t, json.NewDecoder(r.Body).Decode(&req))
			require.Equal(t, "transaction_id", req.Data.TransactionID)
			require.Equal(t, "subscription_status", req.Data.Status)
			require.Equal(t, "sku", req.Data.MetadataProduct.SKU)
			require.Equal(t, true, req.Data.MetadataProduct.IsTVOD)
			require.Equal(t, 1, req.Data.MetadataProduct.MetadataTVOD.RentalPeriodHours)
			require.Equal(t, 2, req.Data.MetadataProduct.MetadataTVOD.ViewingPeriodHours)
			require.Equal(t, true, req.Data.MetadataProduct.MetadataAds.HasPrerollAds)
			require.Equal(t, true, req.Data.MetadataProduct.MetadataAds.HasInterstitialAds)
			require.Equal(t, true, req.Data.MetadataProduct.MetadataAds.HasBannerAds)
			require.Equal(t, "title_id", req.Data.MetadataTVOD.TitleID)
			require.Equal(t, now.Unix(), req.Data.StartedAt.Unix())
			require.Equal(t, now.Unix(), req.Data.ExpiredAt.Unix())
			require.Equal(t, "user_id", req.Meta.LinkToUser.ID)
			require.Equal(t, "sp_account_id", req.Meta.LinkToUser.RefEvSpAccountID)
			require.Equal(t, true, req.Meta.UpsertOnConflict)

			w.Header().Set("X-Request-ID", uuid.New().String())
			w.Header().Set("X-Server-Time", time.Now().UTC().Truncate(time.Millisecond).Format(time.RFC3339))
			w.WriteHeader(http.StatusOK)
			_, _ = w.Write([]byte(response))
		}))
		defer server.Close()

		c := NewClient(server.URL, "api_key")
		resp, err := c.AdminCreateSubscription(request)
		require.NoError(t, err)
		require.NotNil(t, resp)

		// check metadata
		require.NotEmpty(t, resp.Tracing.RequestID)
		require.NotEmpty(t, resp.Tracing.Timestamp)

		// check response
		require.Equal(t, "subscription_id", resp.Data.ID)
		require.Equal(t, "user_id", resp.Data.UserID)
		require.Equal(t, "transaction_id", resp.Data.TransactionID)
		require.Equal(t, "subscription_status", resp.Data.Status)
		require.Equal(t, "sku", resp.Data.MetadataProduct.SKU)
		require.Equal(t, true, resp.Data.MetadataProduct.IsTVOD)
		require.Equal(t, 1, resp.Data.MetadataProduct.MetadataTVOD.RentalPeriodHours)
		require.Equal(t, 2, resp.Data.MetadataProduct.MetadataTVOD.ViewingPeriodHours)
		require.Equal(t, true, resp.Data.MetadataProduct.MetadataAds.HasPrerollAds)
		require.Equal(t, true, resp.Data.MetadataProduct.MetadataAds.HasInterstitialAds)
		require.Equal(t, true, resp.Data.MetadataProduct.MetadataAds.HasBannerAds)
		require.Equal(t, "title_id", resp.Data.MetadataTVOD.TitleID)
		require.Equal(t, int64(1582093159), resp.Data.StartedAt.Unix())
		require.Equal(t, int64(1582093159), resp.Data.ExpiredAt.Unix())
		require.Equal(t, int64(1582093159), resp.Data.InsertedAt.Unix())
		require.Equal(t, int64(1582093159), resp.Data.UpdatedAt.Unix())
		require.Equal(t, int64(1582093159), resp.Data.DeletedAt.Unix())
	})

	t.Run("HandlesError", func(t *testing.T) {
		now := time.Now().UTC().Truncate(time.Second)
		server := httptest.NewServer(http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			require.Equal(t, http.MethodPost, r.Method)
			require.Equal(t, "/admin/subscriptions", r.RequestURI)
			require.Equal(t, "api_key", r.Header.Get("x-api-key"))

			var req AdminCreateSubscriptionRequest
			require.NoError(t, json.NewDecoder(r.Body).Decode(&req))
			require.Equal(t, "transaction_id", req.Data.TransactionID)
			require.Equal(t, "subscription_status", req.Data.Status)
			require.Equal(t, "sku", req.Data.MetadataProduct.SKU)
			require.Equal(t, true, req.Data.MetadataProduct.IsTVOD)
			require.Equal(t, 1, req.Data.MetadataProduct.MetadataTVOD.RentalPeriodHours)
			require.Equal(t, 2, req.Data.MetadataProduct.MetadataTVOD.ViewingPeriodHours)
			require.Equal(t, true, req.Data.MetadataProduct.MetadataAds.HasPrerollAds)
			require.Equal(t, true, req.Data.MetadataProduct.MetadataAds.HasInterstitialAds)
			require.Equal(t, true, req.Data.MetadataProduct.MetadataAds.HasBannerAds)
			require.Equal(t, "title_id", req.Data.MetadataTVOD.TitleID)
			require.Equal(t, now.Unix(), req.Data.StartedAt.Unix())
			require.Equal(t, now.Unix(), req.Data.ExpiredAt.Unix())
			require.Equal(t, "user_id", req.Meta.LinkToUser.ID)
			require.Equal(t, "sp_account_id", req.Meta.LinkToUser.RefEvSpAccountID)
			require.Equal(t, true, req.Meta.UpsertOnConflict)

			rsp := ErrorResponse{
				ErrorObj: errors.Error{
					Message: "blah",
					Code:    "NKT-001",
				},
			}

			w.Header().Set("X-Request-ID", uuid.New().String())
			w.Header().Set("X-Server-Time", time.Now().UTC().Truncate(time.Millisecond).Format(time.RFC3339))
			w.WriteHeader(http.StatusForbidden)
			_ = json.NewEncoder(w).Encode(rsp)
		}))
		defer server.Close()

		c := NewClient(server.URL, "api_key")
		resp, err := c.AdminCreateSubscription(request)
		require.Error(t, err)
		require.Nil(t, resp)
		obj, ok := err.(ErrorResponse)
		require.True(t, ok, reflect.TypeOf(err).String())
		require.Equal(t, "NKT-001", obj.ErrorObj.Code)
		require.Equal(t, "blah", obj.ErrorObj.Message)
		require.NotEmpty(t, obj.Tracing.RequestID)
		require.NotEmpty(t, obj.Tracing.Timestamp)
	})
}
