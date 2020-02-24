defmodule UserPool.Repo.Migrations.AddTransactionsTable do
  use Ecto.Migration

  def up do
    create table("transactions", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:user_id, references(:users, type: :uuid))
      add(:purchased_at, :utc_datetime, null: false)
      add(:played_at, :utc_datetime, null: false)

      timestamps(type: :utc_datetime)
    end
  end

  def down do
    drop(table("transactions"))
  end
end
