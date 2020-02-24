defmodule UserPool.Repo.Migrations.AddBusinessUnitsTable do
  use Ecto.Migration

  def up do
    create table("business_units", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:name, :string, null: false)

      timestamps(type: :utc_datetime)
    end
  end

  def down do
    drop(table("business_units"))
  end
end
