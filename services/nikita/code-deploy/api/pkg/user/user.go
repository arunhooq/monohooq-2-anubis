package user

import (
	"strings"
	"time"

	"github.com/go-pg/pg/v9"
	"github.com/go-pg/pg/v9/orm"
	"github.com/google/uuid"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/errorlist"
	"github.com/hooqtv/monohooq/services/nikita/code-deploy/api/pkg/user/model"
)

// service of User
type service struct {
	db orm.DB
}

// Service interface
type Service interface {
	Create(user *model.UserCreateRequestData) (*model.User, error)
	GetByID(id string) (*model.User, error)
	GetByUniqueIdentifier(email, phoneNumber, evSpAccountID string) (*model.User, error)
}

// NewService creates an instance of User Service
func NewService(db orm.DB) Service {
	return &service{db}
}

func (s *service) Create(data *model.UserCreateRequestData) (*model.User, error) {
	now := time.Now().UTC()
	item := &model.User{
		ID:                uuid.New().String(),
		Email:             strings.ToLower(data.Email),
		PhoneNumber:       strings.ToLower(data.PhoneNumber),
		Country:           strings.ToUpper(data.Country),
		RefEvSpAccountID:  data.RefEvSpAccountID,
		RefEvCpCustomerID: data.RefEvCpCustomerID,
		InsertedAt:        &now,
		UpdatedAt:         &now,
	}
	if err := s.db.Insert(item); err != nil {
		return nil, errorlist.InternalServerError(err)
	}

	return item, nil
}

func (s *service) GetByID(id string) (*model.User, error) {
	item := new(model.User)
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

func (s *service) GetByUniqueIdentifier(email, phoneNumber, evSpAccountID string) (*model.User, error) {
	item := new(model.User)

	query := s.db.Model(item)
	if email != "" {
		query = query.WhereOr("email = ?", email)
	}
	if phoneNumber != "" {
		query = query.WhereOr("phone_number = ?", phoneNumber)
	}
	if evSpAccountID != "" {
		query = query.WhereOr("ref_ev_sp_account_id = ?", evSpAccountID)
	}

	if err := query.Limit(1).Select(); err != nil {
		if err == pg.ErrNoRows {
			return nil, nil
		}
		return nil, errorlist.InternalServerError(err)
	}
	return item, nil
}
