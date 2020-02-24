package subscription

import (
	"time"

	"github.com/go-pg/pg/v9"
	"github.com/go-pg/pg/v9/orm"
	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/errorlist"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/subscription/model"
)

// service of Subscription
type service struct {
	db orm.DB
}

// Service interface
type Service interface {
	Create(data *model.SubscriptionCreateRequestData, meta *model.SubscriptionCreateRequestMeta) (*model.Subscription, error)
	GetByID(id string) (*model.Subscription, error)
	GetManyByUserID(userID, state string) ([]model.Subscription, error)
}

// NewService creates an instance of Subscription Service
func NewService(db orm.DB) Service {
	return &service{db}
}

func (s *service) Create(data *model.SubscriptionCreateRequestData, meta *model.SubscriptionCreateRequestMeta) (*model.Subscription, error) {
	now := time.Now().UTC()
	item := &model.Subscription{
		ID:              uuid.New().String(),
		UserID:          meta.LinkToUser.ID,
		Status:          data.Status,
		MetadataProduct: data.MetadataProduct,
		MetadataTVOD:    data.MetadataTVOD,
		StartedAt:       data.StartedAt,
		ExpiredAt:       data.ExpiredAt,
		InsertedAt:      &now,
		UpdatedAt:       &now,
	}
	if err := s.db.Insert(item); err != nil {
		return nil, errorlist.InternalServerError(err)
	}

	return item, nil
}

func (s *service) GetByID(id string) (*model.Subscription, error) {
	item := new(model.Subscription)
	if err := s.db.Model(item).
		Where("id = ?", id).
		Limit(1).
		Select(); err != nil {
		if err == pg.ErrNoRows {
			return nil, nil
		}
		return nil, errorlist.InternalServerError(err)
	}
	return item, nil
}

func (s *service) GetManyByUserID(userID, state string) ([]model.Subscription, error) {
	items := []model.Subscription{}

	query := s.db.Model(&items).Where("user_id = ?", userID)
	switch state {
	case "active":
		query.Where("started_at < now()")
		query.Where("expired_at > now()")
	case "expired":
		query.Where("started_at < now()")
		query.Where("expired_at < now()")
	case "future":
		query.Where("started_at > now()")
		query.Where("expired_at > now()")
	}

	if err := query.Select(); err != nil {
		if err == pg.ErrNoRows {
			return items, nil
		}
		return nil, errorlist.InternalServerError(err)
	}
	return items, nil
}
