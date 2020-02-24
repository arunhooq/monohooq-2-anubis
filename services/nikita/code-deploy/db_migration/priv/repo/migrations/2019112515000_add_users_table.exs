defmodule UserPool.Repo.Migrations.AddUsersTable do
  use Ecto.Migration

  def up do
    create table("users", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:country, :string, null: false)
      add(:ref_id_ev_sp_account_id, :string, null: false)
      add(:ref_id_ev_cp_customer_id, :string)
      add(:status, :string, null: false)
      add(:type, :string, null: false)

      timestamps(type: :utc_datetime)
    end
  end

  def down do
    drop(table("users"))
  end
end
