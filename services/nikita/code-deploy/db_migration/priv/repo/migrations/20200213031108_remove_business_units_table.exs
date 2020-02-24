defmodule UserPool.Repo.Migrations.RemoveBusinessUnitsTable do
  use Ecto.Migration

  def up do
    alter table(:products) do
      remove(:business_unit_id)
    end

    drop(table("business_units"))
  end

  def down do
    create table("business_units", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:name, :string, null: false)

      timestamps(type: :utc_datetime)
    end

    alter table(:products) do
      add(:business_unit_id, references(:business_units, type: :uuid))
    end
  end
end
