#################################################################  VARIABLES  #####################################################
data "terraform_remote_state" "miyazaki_infra_state" {
  backend = "remote"
  config = {
    organization = "miyazaki"
    token        = "TF_TOKEN_MIYAZAKI"
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


# miyazaki Service
module "miyazaki-ecs-service" {
  source                             = "../../../modules/terraform/aws/ecs/service"
  name                               = local.name
  host_port                          = var.host_port
  desired_count                      = local.desired_task_count
  container_name                     = local.container_name
  container_port                     = var.container_port
  volume_name                        = local.volume_name
  instance_volume_path               = local.instance_volume_path
  min_capacity                       = local.min_capacity
  max_capacity                       = local.max_capacity
  environment                        = local.environment
  ecs_task_role                      = local.ecs_task_role
  ecs_task_execution_role            = local.ecs_task_execution_role
  ecs_service_role                   = local.ecs_service_role
  container_definition_file          = local.container_definition_file
  container_mount_path               = local.container_mount_path
  awslogs_group                      = local.awslogs_group_name
  cluster_name                       = local.cluster_name
  availability_zones                 = local.azs
  region                             = var.aws_region
  target_group_arn                   = local.alb_targetgroups_arns
  docker_image                       = local.docker_image
  docker_tag                         = local.docker_tag
  github_package_credentials_arn     = local.private_registry_token
  aws_account_id                     = "${data.aws_caller_identity.current.account_id}"
  deployment_minimum_healthy_percent = local.deployment_minimum_healthy_percent
  deployment_maximum_percent         = local.deployment_maximum_percent

}

### ECS Service ASG
module "aws_ecs_app_autoscaling_target" {
  source             = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/app_autoscaling_target"
  min_capacity       = local.min_capacity
  max_capacity       = local.max_capacity
  role_arn           = local.ecs_service_role_arn
  ecs_cluster_name   = local.cluster_name
  ecs_service_name   = module.miyazaki-ecs-service.ecs_service
  scalable_dimension = var.ecs_target_scalable_dimension
  service_namespace  = var.service_namespace
}

module "ecs_service_autoscaling_policy_scale_up" {
  name                         = "${module.miyazaki-ecs-service.ecs_service}-scale-up"
  source                       = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/scaleup"
  cooldown                     = var.autoscaling_policy_cooldown
  ecs_service_name             = module.miyazaki-ecs-service.ecs_service
  ecs_cluster_name             = local.cluster_name
  adjustment_type              = var.autoscaling_policy_adjustment_type
  metric_aggregation_type      = var.autoscaling_policy_metric_aggregation_type
  scalable_dimension           = var.ecs_target_scalable_dimension
  service_namespace            = var.service_namespace
  step_up_1_scaling_adjustment = local.step_up_1_scaling_adjustment
  step_up_1_lower_bound        = local.step_up_1_lower_bound
  step_up_1_upper_bound        = local.step_up_1_upper_bound
  step_up_2_scaling_adjustment = local.step_up_2_scaling_adjustment
  step_up_2_lower_bound        = local.step_up_2_lower_bound
  step_up_2_upper_bound        = local.step_up_2_upper_bound
  step_up_3_scaling_adjustment = local.step_up_3_scaling_adjustment
  step_up_3_lower_bound        = local.step_up_3_lower_bound
}

module "ecs_service_autoscaling_policy_scale_down" {
  name                           = "${module.miyazaki-ecs-service.ecs_service}-scale-down"
  source                         = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/scaledown"
  cooldown                       = var.autoscaling_policy_cooldown
  ecs_service_name               = module.miyazaki-ecs-service.ecs_service
  ecs_cluster_name               = local.cluster_name
  adjustment_type                = var.autoscaling_policy_adjustment_type
  metric_aggregation_type        = var.autoscaling_policy_metric_aggregation_type
  scalable_dimension             = var.ecs_target_scalable_dimension
  service_namespace              = var.service_namespace
  step_down_1_scaling_adjustment = local.step_down_1_scaling_adjustment
  step_down_1_lower_bound        = local.step_down_1_lower_bound
  step_down_1_upper_bound        = local.step_down_1_upper_bound
  step_down_2_scaling_adjustment = local.step_down_2_scaling_adjustment
  step_down_2_lower_bound        = local.step_down_2_lower_bound
  step_down_2_upper_bound        = local.step_down_2_upper_bound
  step_down_3_scaling_adjustment = local.step_down_3_scaling_adjustment
  step_down_3_upper_bound        = local.step_down_3_upper_bound
}


module "cloudwatch_alarm_ecs_service_scaleup" {
  source              = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/ecs_service_name"
  evaluation_periods  = var.ecs_service_scaleup_evaluation_periods
  period              = var.cloudwatch_alarm_ecs_service_period
  metric_namespace    = var.cloudwatch_alarm_ecs_service_metric_namespace
  statistic           = var.cloudwatch_alarm_ecs_service_metric_statistic
  metric_name         = var.metric_name
  comparison_operator = var.cloudwatch_alarm_ecs_service_scaleup_operator
  threshold           = var.ecs_service_scaleup_threshold
  alarm_description   = "This alarm monitors ${local.ecs_service_name} containers CPU utilization for scaling up"
  alarm_actions       = module.ecs_service_autoscaling_policy_scale_up.scale_up_arn
  alarm_name          = "${local.ecs_service_name}-containers-CPU-Utilization-Above-${var.ecs_service_scaleup_threshold}"
  cluster_name        = "${local.cluster_name}"
  service_name        = "${local.ecs_service_name}"
  datapoints_to_alarm = var.ecs_service_scaleup_datapoints
}

module "cloudwatch_alarm_ecs_service_scaledown" {
  source              = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/ecs_service_name"
  evaluation_periods  = var.ecs_service_scaledown_evaluation_periods
  period              = var.cloudwatch_alarm_ecs_service_period
  metric_namespace    = var.cloudwatch_alarm_ecs_service_metric_namespace
  statistic           = var.cloudwatch_alarm_ecs_service_metric_statistic
  metric_name         = var.metric_name
  comparison_operator = var.cloudwatch_alarm_ecs_service_scaledown_operator
  threshold           = var.ecs_service_scaledown_threshold
  alarm_description   = "This alarm monitors ${local.ecs_service_name} containers CPU utilization for scaling up"
  alarm_actions       = module.ecs_service_autoscaling_policy_scale_down.scale_down_arn
  alarm_name          = "${local.ecs_service_name}-containers-CPU-Utilization-Below-${var.ecs_service_scaledown_threshold}"
  cluster_name        = "${local.cluster_name}"
  service_name        = "${local.ecs_service_name}"
  datapoints_to_alarm = var.ecs_service_scaledown_datapoints
}

######################################################  OUTPUTS  #####################################################

output "ecs_container_definition" {
  value = module.miyazaki-ecs-service.ecs_container_definition
}
output "ecs_task_definition_arn" {
  value = module.miyazaki-ecs-service.ecs_task_definition_arn
}

output "ecs_service_name" {
  value = module.miyazaki-ecs-service.ecs_service
}

output "AWS_SM_ARN" {
  value = local.private_registry_token
}

output "ecs_service_autoscaling_policy_scale_up_arn" {
  value = module.ecs_service_autoscaling_policy_scale_up.scale_up_arn
}

output "ecs_service_autoscaling_policy_scale_down_arn" {
  value = module.ecs_service_autoscaling_policy_scale_down.scale_down_arn
}

output "cloudwatch_ecs_service_scaleup_cpuutilization_alarm" {
  value = "${module.cloudwatch_alarm_ecs_service_scaleup.cloudwatch_alarm_arn}"
}

output "cloudwatch_ecs_service_scaledown_cpuutilization_alarm" {
  value = "${module.cloudwatch_alarm_ecs_service_scaledown.cloudwatch_alarm_arn}"
}
