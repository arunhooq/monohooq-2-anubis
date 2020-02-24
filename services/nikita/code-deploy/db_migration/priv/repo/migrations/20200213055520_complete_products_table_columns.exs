defmodule UserPool.Repo.Migrations.CompleteProductTableColumns do
  use Ecto.Migration

  def up do
    alter table(:products) do
      add(:sku, :string, null: false)
      add(:type, :string, null: false)
      add(:description, :string)
      add(:is_tvod, :boolean, null: false)
      add(:is_renewable, :boolean, null: false)
      add(:metadata_tvod, :map)
      add(:metadata_ads, :map)
    end

    create(unique_index(:products, [:sku], where: "deleted_at IS NULL"))
  end

  def down do
    drop(unique_index(:products, [:sku]))

    alter table(:products) do
      remove(:sku)
      remove(:description)
      remove(:type)
      remove(:is_tvod)
      remove(:is_renewable)
      remove(:metadata_tvod)
      remove(:metadata_ads)
    end
  end
end
