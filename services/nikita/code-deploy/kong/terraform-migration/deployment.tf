######################################################  VARIABLES  #####################################################
data "terraform_remote_state" "nikita_infra_state" {
  backend = "remote"
  config = {
    organization = "nikita"
    token        = "SED_TF_TOKEN_NIKITA"
    workspaces = {
      name = "nikita-infra-${terraform.workspace}"
    }
  }
}

data "aws_caller_identity" "current" {}

######################################################  RESOURCES  #####################################################

module "kong_db_migration" {
  source                    = "../../../../../modules/terraform/aws/ecs/task_definition"
  name                      = local.name
  container_name            = local.container_name
  ecs_task_role             = local.ecs_task_role
  ecs_task_execution_role   = local.ecs_task_execution_role
  container_definition_file = local.container_definition_file
  awslogs_group             = local.awslogs_group_name
  region                    = var.region
  docker_image              = local.docker_image
  docker_tag                = local.docker_tag
  aws_account_id            = local.account_id
  command                   = var.db_migration_command

  // Fargate parameters
  task_requires_compatibilities = var.task_requires_compatibilities
  task_cpu                      = var.task_cpu
  task_memory                   = var.task_memory
  task_network_mode             = var.task_network_mode
}

module "run_migrate" {
  source                    = "../../../../../modules/terraform/aws/ecs/run_task"
  region                    = var.region
  cluster_name              = local.cluster_name
  task_definition_arn       = module.kong_db_migration.ecs_task_definition_arn
  launch_type               = "FARGATE"
  awsvpc_private_subnet_ids = local.private_subnet_ids
  awsvpc_security_group_ids = local.security_group_ids
}

######################################################  OUTPUTS  #####################################################
output "ecs_container_definition" {
  value = module.kong_db_migration.ecs_container_definition
}

output "ecs_task_definition_arn" {
  value = module.kong_db_migration.ecs_task_definition_arn
}
