defmodule UserPool.Repo.Migrations.AddSubscriptionsTable do
  use Ecto.Migration

  def up do
    create table("subscriptions", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:user_id, references(:users, type: :uuid))
      add(:product_id, references(:products, type: :uuid))
      add(:status, :string, null: false)
      add(:type, :string, null: false)
      add(:started_at, :utc_datetime, null: false)
      add(:expired_at, :utc_datetime, null: false)

      timestamps(type: :utc_datetime)
    end
  end

  def down do
    drop(table("subscriptions"))
  end
end
