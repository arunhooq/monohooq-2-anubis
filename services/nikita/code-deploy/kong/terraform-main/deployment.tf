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


# nikita kong main Service
module "nikita-kong-main" {
  source = "../../../../../modules/terraform/aws/ecs/service"

  name                               = local.name
  container_name                     = local.container_name
  host_port                          = var.host_port
  container_port                     = var.container_port
  desired_count                      = var.desired_task_count
  volume_name                        = local.volume_name
  instance_volume_path               = local.instance_volume_path
  container_mount_path               = local.container_mount_path
  min_capacity                       = var.min_capacity
  max_capacity                       = var.max_capacity
  environment                        = local.environment
  ecs_task_role                      = local.ecs_task_role
  ecs_task_execution_role            = local.ecs_task_execution_role
  ecs_service_role                   = local.ecs_service_role
  container_definition_file          = local.container_definition_file
  awslogs_group                      = local.awslogs_group_name
  cluster_name                       = local.cluster_name
  availability_zones                 = local.azs
  region                             = var.region
  target_group_arn                   = local.alb_targetgroups_arns
  docker_image                       = local.docker_image
  docker_tag                         = local.docker_tag
  aws_account_id                     = local.account_id
  deployment_minimum_healthy_percent = local.deployment_minimum_healthy_percent
  deployment_maximum_percent         = local.deployment_maximum_percent
}

### ECS Service ASG
module "aws_ecs_app_autoscaling_target" {
  source = "../../../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/app_autoscaling_target"

  min_capacity       = var.min_capacity
  max_capacity       = var.max_capacity
  role_arn           = local.ecs_service_role_arn
  ecs_cluster_name   = local.cluster_name
  ecs_service_name   = module.nikita-kong-main.ecs_service
  scalable_dimension = "ecs:service:DesiredCount"
  service_namespace  = "ecs"
}

module "ecs_service_autoscaling_policy_scale_up" {
  source = "../../../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/scaleup"

  name                         = "${module.nikita-kong-main.ecs_service}-scale-up"
  cooldown                     = 60
  ecs_service_name             = module.nikita-kong-main.ecs_service
  ecs_cluster_name             = local.cluster_name
  adjustment_type              = "ChangeInCapacity"
  metric_aggregation_type      = "Maximum"
  scalable_dimension           = "ecs:service:DesiredCount"
  service_namespace            = "ecs"
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
  source = "../../../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/scaledown"

  name                           = "${module.nikita-kong-main.ecs_service}-scale-down"
  cooldown                       = 60
  ecs_service_name               = module.nikita-kong-main.ecs_service
  ecs_cluster_name               = local.cluster_name
  adjustment_type                = "ChangeInCapacity"
  metric_aggregation_type        = "Maximum"
  scalable_dimension             = "ecs:service:DesiredCount"
  service_namespace              = "ecs"
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
  source = "../../../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/ecs_service_name"

  evaluation_periods  = "1"
  period              = "120"
  metric_namespace    = "AWS/ECS"
  statistic           = "Average"
  metric_name         = "CPUUtilization"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  threshold           = "30"
  alarm_description   = "This alarm monitors ${local.ecs_service_name} containers CPU utilization for scaling up"
  alarm_actions       = module.ecs_service_autoscaling_policy_scale_up.scale_up_arn
  alarm_name          = "${local.ecs_service_name}-containers-CPU-Utilization-Above-60"
  cluster_name        = local.cluster_name
  service_name        = local.ecs_service_name
  datapoints_to_alarm = "1"
}

module "cloudwatch_alarm_ecs_service_scaledown" {
  source = "../../../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/ecs_service_name"

  evaluation_periods  = "1"
  period              = "120"
  metric_namespace    = "AWS/ECS"
  statistic           = "Average"
  metric_name         = "CPUUtilization"
  comparison_operator = "LessThanThreshold"
  threshold           = "30"
  alarm_description   = "This alarm monitors ${local.ecs_service_name} containers CPU utilization for scaling up"
  alarm_actions       = module.ecs_service_autoscaling_policy_scale_up.scale_up_arn
  alarm_name          = "${local.ecs_service_name}-containers-CPU-Utilization-Below-30"
  cluster_name        = local.cluster_name
  service_name        = local.ecs_service_name
  datapoints_to_alarm = "1"
}

######################################################  OUTPUTS  #####################################################

output "ecs_container_definition" {
  value = module.nikita-kong-main.ecs_container_definition
}
output "ecs_task_definition_arn" {
  value = module.nikita-kong-main.ecs_task_definition_arn
}

output "ecs_service_name" {
  value = module.nikita-kong-main.ecs_service
}

output "ecs_service_autoscaling_policy_scale_up_arn" {
  value = module.ecs_service_autoscaling_policy_scale_up.scale_up_arn
}

output "ecs_service_autoscaling_policy_scale_down_arn" {
  value = module.ecs_service_autoscaling_policy_scale_down.scale_down_arn
}

output "cloudwatch_ecs_service_scaleup_cpuutilization_alarm" {
  value = module.cloudwatch_alarm_ecs_service_scaleup.cloudwatch_alarm_arn
}

output "cloudwatch_ecs_service_scaledown_cpuutilization_alarm" {
  value = module.cloudwatch_alarm_ecs_service_scaledown.cloudwatch_alarm_arn
}
