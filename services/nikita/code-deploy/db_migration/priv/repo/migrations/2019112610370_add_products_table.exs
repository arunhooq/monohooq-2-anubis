defmodule UserPool.Repo.Migrations.AddProductsTable do
  use Ecto.Migration

  def up do
    create table("products", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:name, :string, null: false)
      add(:business_unit_id, references(:business_units, type: :uuid))

      timestamps(type: :utc_datetime)
    end
  end

  def down do
    drop(table("products"))
  end
end
