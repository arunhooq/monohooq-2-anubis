defmodule UserPool.Repo.Migrations.AddDeletedColumns do
  use Ecto.Migration

  def up do
    alter table(:users) do
      add(:deleted_at, :utc_datetime)
    end

    alter table(:products) do
      add(:deleted_at, :utc_datetime)
    end

    alter table(:subscriptions) do
      add(:deleted_at, :utc_datetime)
    end
  end

  def down do
    alter table(:subscriptions) do
      remove(:deleted_at)
    end

    alter table(:products) do
      remove(:deleted_at)
    end

    alter table(:users) do
      remove(:deleted_at)
    end
  end
end
