locals {
  name                    = var.env[terraform.workspace].name
  service                 = "${var.service-name}-db-migration"
  cluster_name            = data.terraform_remote_state.nikita_infra_state.outputs.script_runner_ecs_cluster_name
  awslogs_group_name      = "${var.service-name}-script-runner-${terraform.workspace}-logs"
  private_registry_token  = data.terraform_remote_state.cloud_sre_state.outputs.private_registry_arn
  ecs_task_role           = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_execution_role_arn
  private_subnet_ids      = data.terraform_remote_state.nikita_infra_state.outputs.private_subnet_ids
  security_group_ids      = [data.terraform_remote_state.nikita_infra_state.outputs.alb_sg_id]
  tags = {
    service-name = var.service-name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }
}
