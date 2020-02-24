defmodule UserPool.Repo.Migrations.RemoveTypeColumnFromSubscriptionsTable do
  use Ecto.Migration

  def up do
    alter table(:subscriptions) do
      remove(:type)
    end
  end

  def down do
    alter table(:subscriptions) do
      add(:type, :string)
    end
  end
end
