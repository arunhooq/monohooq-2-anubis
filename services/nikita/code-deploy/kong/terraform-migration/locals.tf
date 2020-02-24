locals {
  // General
  name       = "${var.code_deploy_name}-${terraform.workspace}"
  account_id = data.aws_caller_identity.current.account_id
  tags = {
    service_name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }
}

locals {
  // Network
  private_subnet_ids = data.terraform_remote_state.nikita_infra_state.outputs.private_subnet_ids
  security_group_ids = [data.terraform_remote_state.nikita_infra_state.outputs.alb_sg_id]
}

locals {
  // ECS
  cluster_name              = data.terraform_remote_state.nikita_infra_state.outputs.script_runner_ecs_cluster_name
  container_name            = var.code_deploy_name
  container_definition_file = "hooq-${local.container_name}.json"
  docker_image              = "kong"
  docker_tag                = "2.0"
  ecs_task_role             = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role   = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_execution_role_arn
}

locals {
  // Logs
  awslogs_group_name = "nikita-kong-admin-${terraform.workspace}-logs"
}
