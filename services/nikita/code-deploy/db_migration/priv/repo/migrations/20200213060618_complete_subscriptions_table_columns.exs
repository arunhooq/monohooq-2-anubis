defmodule UserPool.Repo.Migrations.CompleteSubscriptionsTableColumns do
  use Ecto.Migration

  def up do
    execute("ALTER TABLE subscriptions DROP CONSTRAINT subscriptions_user_id_fkey")
    execute("ALTER TABLE subscriptions DROP CONSTRAINT subscriptions_product_id_fkey")

    alter table(:subscriptions) do
      modify(:user_id, :uuid, null: false)
      modify(:product_id, :uuid, null: false)
    end

    create(index(:subscriptions, [:user_id]))
  end

  def down do
    drop(index(:subscriptions, [:user_id]))

    alter table(:subscriptions) do
      modify(:user_id, references(:users, type: :uuid), null: true)
      modify(:product_id, references(:products, type: :uuid), null: true)
    end
  end
end
