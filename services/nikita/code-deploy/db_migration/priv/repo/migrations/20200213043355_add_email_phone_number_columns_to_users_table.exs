defmodule UserPool.Repo.Migrations.AddEmailPhoneNumberColumnsToUsersTable do
  use Ecto.Migration

  def up do
    alter table(:users) do
      add(:email, :string)
      add(:phone_number, :string)
    end

    create(unique_index(:users, [:email], where: "deleted_at IS NULL"))
    create(unique_index(:users, [:phone_number], where: "deleted_at IS NULL"))
  end

  def down do
    drop(unique_index(:users, [:phone_number]))
    drop(unique_index(:users, [:email]))

    alter table(:users) do
      remove(:phone_number)
      remove(:email)
    end
  end
end
