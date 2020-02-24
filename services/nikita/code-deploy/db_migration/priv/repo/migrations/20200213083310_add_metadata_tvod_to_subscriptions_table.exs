defmodule UserPool.Repo.Migrations.AddMetadataTvodToSubscriptionsTable do
  use Ecto.Migration

  def up do
    alter table(:subscriptions) do
      add(:metadata_tvod, :map)
    end
  end

  def down do
    alter table(:subscriptions) do
      remove(:metadata_tvod)
    end
  end
end
