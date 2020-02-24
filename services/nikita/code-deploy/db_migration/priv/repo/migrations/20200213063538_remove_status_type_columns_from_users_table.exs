defmodule UserPool.Repo.Migrations.RemoveStatusTypeColumnsFromUsersTable do
  use Ecto.Migration

  def up do
    alter table(:users) do
      remove(:status)
      remove(:type)
    end
  end

  def down do
    alter table(:users) do
      add(:status, :string)
      add(:type, :string)
    end
  end
end
