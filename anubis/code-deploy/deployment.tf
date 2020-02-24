######################################################  VARIABLES  #####################################################
data "terraform_remote_state" "anubis_infra_state" {
  backend = "remote"
  config = {
    organization = "Anubis"
    token        = "TF_TOKEN_ANUBIS"
    workspaces = {
      name = "anubis_infra_${terraform.workspace}"
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

######################################################  RESOURCES  #####################################################

# anubis api service
module "api-ecs-task-definition" {
  source                         = "../../modules/terraform/aws/ecs/task_definition"
  name                           = "${local.name}-api"
  host_port                      = var.host_port
  container_name                 = local.api_service
  container_port                 = var.container_port
  volume_name                    = var.volume_name
  instance_volume_path           = var.instance_volume_path
  min_capacity                   = var.min_capacity
  max_capacity                   = var.max_capacity
  environment                    = local.Environment
  ecs_task_role                  = local.ecs_task_role
  ecs_task_execution_role        = local.ecs_task_execution_role
  ecs_service_role               = local.ecs_service_role
  container_definition_file      = "hooq-${local.api_service}.json"
  container_mount_path           = var.container_mount_path
  awslogs_group                  = local.awslogs_group_name
  cluster_name                   = local.cluster_name
  availability_zones             = local.azs
  region                         = var.region
  docker_image                   = var.docker_image
  docker_tag                     = var.docker_tag
  github_package_credentials_arn = local.private_registry_token
  aws_account_id                 = data.aws_caller_identity.current.account_id
}

# anubis partnerapi service
module "partnerapi-ecs-task-definition" {
  source                         = "../../modules/terraform/aws/ecs/task_definition"
  name                           = "${local.name}-partnerapi"
  host_port                      = var.host_port
  container_name                 = local.partnerapi_service
  container_port                 = var.container_port
  volume_name                    = var.volume_name
  instance_volume_path           = var.instance_volume_path
  min_capacity                   = var.min_capacity
  max_capacity                   = var.max_capacity
  environment                    = local.Environment
  ecs_task_role                  = local.ecs_task_role
  ecs_task_execution_role        = local.ecs_task_execution_role
  ecs_service_role               = local.ecs_service_role
  container_definition_file      = "hooq-${local.partnerapi_service}.json"
  container_mount_path           = var.container_mount_path
  awslogs_group                  = local.awslogs_group_name
  cluster_name                   = local.cluster_name
  availability_zones             = local.azs
  region                         = var.region
  docker_image                   = var.docker_image
  docker_tag                     = var.docker_tag
  github_package_credentials_arn = local.private_registry_token
  aws_account_id                 = data.aws_caller_identity.current.account_id
}

# anubis android service
module "android-ecs-task-definition" {
  source                         = "../../modules/terraform/aws/ecs/task_definition"
  name                           = "${local.name}-android"
  host_port                      = var.host_port
  container_name                 = local.android_service
  container_port                 = var.container_port
  volume_name                    = var.volume_name
  instance_volume_path           = var.instance_volume_path
  min_capacity                   = var.min_capacity
  max_capacity                   = var.max_capacity
  environment                    = local.Environment
  ecs_task_role                  = local.ecs_task_role
  ecs_task_execution_role        = local.ecs_task_execution_role
  ecs_service_role               = local.ecs_service_role
  container_definition_file      = "hooq-${local.android_service}.json"
  container_mount_path           = var.container_mount_path
  awslogs_group                  = local.awslogs_group_name
  cluster_name                   = local.cluster_name
  availability_zones             = local.azs
  region                         = var.region
  docker_image                   = var.docker_image
  docker_tag                     = var.docker_tag
  github_package_credentials_arn = local.private_registry_token
  aws_account_id                 = data.aws_caller_identity.current.account_id
}


# anubis web service
module "web-ecs-task-definition" {
  source                         = "../../modules/terraform/aws/ecs/task_definition"
  name                           = "${local.name}-web"
  host_port                      = var.host_port
  container_name                 = local.web_service
  container_port                 = var.container_port
  volume_name                    = var.volume_name
  instance_volume_path           = var.instance_volume_path
  min_capacity                   = var.min_capacity
  max_capacity                   = var.max_capacity
  environment                    = local.Environment
  ecs_task_role                  = local.ecs_task_role
  ecs_task_execution_role        = local.ecs_task_execution_role
  ecs_service_role               = local.ecs_service_role
  container_definition_file      = "hooq-${local.web_service}.json"
  container_mount_path           = var.container_mount_path
  awslogs_group                  = local.awslogs_group_name
  cluster_name                   = local.cluster_name
  availability_zones             = local.azs
  region                         = var.region
  docker_image                   = var.docker_image
  docker_tag                     = var.docker_tag
  github_package_credentials_arn = local.private_registry_token
  aws_account_id                 = data.aws_caller_identity.current.account_id
}

# anubis ios service
module "ios-ecs-task-definition" {
  source                         = "../../modules/terraform/aws/ecs/task_definition"
  name                           = "${local.name}-ios"
  host_port                      = var.host_port
  container_name                 = local.ios_service
  container_port                 = var.container_port
  volume_name                    = var.volume_name
  instance_volume_path           = var.instance_volume_path
  min_capacity                   = var.min_capacity
  max_capacity                   = var.max_capacity
  environment                    = local.Environment
  ecs_task_role                  = local.ecs_task_role
  ecs_task_execution_role        = local.ecs_task_execution_role
  ecs_service_role               = local.ecs_service_role
  container_definition_file      = "hooq-${local.ios_service}.json"
  container_mount_path           = var.container_mount_path
  awslogs_group                  = local.awslogs_group_name
  cluster_name                   = local.cluster_name
  availability_zones             = local.azs
  region                         = var.region
  docker_image                   = var.docker_image
  docker_tag                     = var.docker_tag
  github_package_credentials_arn = local.private_registry_token
  aws_account_id                 = data.aws_caller_identity.current.account_id
}

######################################################  OUTPUTS  #####################################################

output "api_ecs_container_definition" {
  value = module.api-ecs-task-definition.ecs_container_definition
}
output "api_ecs_task_definition_arn" {
  value = module.api-ecs-task-definition.ecs_task_definition_arn
}

output "AWS_SM_ARN" {
  value = local.private_registry_token
}


output "partnerapi_ecs_container_definition" {
  value = module.partnerapi-ecs-task-definition.ecs_container_definition
}
output "partnerapi_ecs_task_definition_arn" {
  value = module.partnerapi-ecs-task-definition.ecs_task_definition_arn
}

output "android_ecs_container_definition" {
  value = module.android-ecs-task-definition.ecs_container_definition
}
output "android_ecs_task_definition_arn" {
  value = module.android-ecs-task-definition.ecs_task_definition_arn
}

output "ios_ecs_container_definition" {
  value = module.ios-ecs-task-definition.ecs_container_definition
}
output "ios_ecs_task_definition_arn" {
  value = module.ios-ecs-task-definition.ecs_task_definition_arn
}

output "web_ecs_container_definition" {
  value = module.web-ecs-task-definition.ecs_container_definition
}
output "web_ecs_task_definition_arn" {
  value = module.web-ecs-task-definition.ecs_task_definition_arn
}




