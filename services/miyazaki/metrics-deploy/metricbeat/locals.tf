locals {
  ecs_task_role            = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role  = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_task_execution_role_arn
  ecs_service_role         = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_service_role_arn
  private_registry_token   = data.terraform_remote_state.cloud_sre_state.outputs.private_registry_arn

  tags = {
    service_name = var.service_name
    owner = var.app
    environment = terraform.workspace
    version = var.tag
  }
  awslogs_group_name    = "${var.app}-${terraform.workspace}-metricbeat-logs"
}
