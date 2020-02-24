defmodule UserPool.Repo.Migrations.RenameRefEvColumnsInUsersTable do
  use Ecto.Migration

  def up do
    rename(table(:users), :ref_id_ev_sp_account_id, to: :ref_ev_sp_account_id)
    rename(table(:users), :ref_id_ev_cp_customer_id, to: :ref_ev_cp_customer_id)

    alter table(:users) do
      modify(:ref_ev_sp_account_id, :string, null: true)
    end

    create(unique_index(:users, [:ref_ev_sp_account_id], where: "deleted_at IS NULL"))
  end

  def down do
    drop(unique_index(:users, [:ref_ev_sp_account_id]))

    alter table(:users) do
      modify(:ref_ev_sp_account_id, :string, null: false)
    end

    rename(table(:users), :ref_ev_cp_customer_id, to: :ref_id_ev_cp_customer_id)
    rename(table(:users), :ref_ev_sp_account_id, to: :ref_id_ev_sp_account_id)
  end
end
