defmodule UserPool.Repo.Migrations.RemoveTransactions do
  use Ecto.Migration

  def up do
    drop(table("transactions"))
  end

  def down do
    create table("transactions", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:user_id, references(:users, type: :uuid))
      add(:purchased_at, :utc_datetime, null: false)
      add(:played_at, :utc_datetime, null: false)

      timestamps(type: :utc_datetime)
    end
  end
end
