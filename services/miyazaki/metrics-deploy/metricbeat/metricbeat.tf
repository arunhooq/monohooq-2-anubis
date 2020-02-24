######################################################  VARIABLES  #####################################################
data "terraform_remote_state" "miyazaki_infra_state" {
  backend = "remote"
  config = {
    organization = "miyazaki"
    token = "TF_TOKEN_MIYAZAKI"
    workspaces = {
      name = "miyazaki-infra-${terraform.workspace}"
    }
  }
}

data "terraform_remote_state" "cloud_sre_state" {
  backend = "remote"
  config = {
    organization = "Cloud_SRE"
    token        = "TF_TOKEN_CLOUD_SRE"
    workspaces = {
      name = "cloud-sre-aws-${terraform.workspace}"
    }
  }
}

data "aws_caller_identity" "current" {}

######################################################## RESOURCES #####################################################

# miyazaki Service
module "miyazaki-metricbeat-ecs-service" {
  source                         = "../../../../modules/terraform/aws/ecs/service/daemon"
  name                           = var.service_name
  ecs_task_role                  = local.ecs_task_role
  ecs_task_execution_role        = local.ecs_task_execution_role
  ecs_service_role               = local.ecs_service_role
  awslogs_group                  = local.awslogs_group_name
  container_definition_file      = "metricbeat.json"
  cluster_name                   = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_cluster_name
  availability_zones             = var.availability_zones
  region                         = var.aws_region
  docker_image                   = var.docker_image
  docker_tag                     = var.docker_image_tag
  scheduling_strategy            = var.scheduling_strategy
  github_package_credentials_arn = local.private_registry_token
  environment                    = terraform.workspace
  aws_account_id                 = "${data.aws_caller_identity.current.account_id}"

}

######################################################  OUTPUTS  #####################################################

output "ecs_container_definition" {
  value = module.miyazaki-metricbeat-ecs-service.ecs_container_definition
}
output "ecs_task_definition_arn" {
  value = module.miyazaki-metricbeat-ecs-service.ecs_task_definition_arn
}

output "ecs_service_name" {
  value = module.miyazaki-metricbeat-ecs-service.ecs_service
}
