defmodule UserPool.Repo.Migrations.RemoveContacts do
  use Ecto.Migration

  def up do
    drop(table("contacts"))
  end

  def down do
    create table("contacts", primary_key: false) do
      add(:id, :uuid, primary_key: true)
      add(:user_id, references(:users, type: :uuid))
      add(:email, :string)
      add(:phone_number, :string)
      add(:password, :string)

      timestamps(type: :utc_datetime)
    end
  end
end
