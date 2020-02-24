defmodule UserPool.Repo.Migrations.NestProductInSubscriptionsTable do
  use Ecto.Migration

  def up do
    drop(table("products"))

    alter table(:subscriptions) do
      add(:metadata_product, :map)
      remove(:product_id)
    end
  end

  def down do
    alter table(:subscriptions) do
      add(:product_id, :uuid, null: false)
      remove(:metadata_product)
    end

    create table("products", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:name, :string, null: false)
      add(:sku, :string, null: false)
      add(:type, :string, null: false)
      add(:description, :string)
      add(:is_tvod, :boolean, null: false)
      add(:is_renewable, :boolean, null: false)
      add(:metadata_tvod, :map)
      add(:metadata_ads, :map)

      timestamps(type: :utc_datetime)
      add(:deleted_at, :utc_datetime)
    end

    create(unique_index(:products, [:sku], where: "deleted_at IS NULL"))
  end
end
