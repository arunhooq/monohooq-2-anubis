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

data "terraform_remote_state" "cloud_sre_state" {
  backend = "remote"
  config = {
    organization = "Cloud_SRE"
    token        = "SED_TF_TOKEN_CLOUD_SRE"
    workspaces = {
      name = "cloud-sre-aws-${terraform.workspace}"
    }
  }
}

data "aws_caller_identity" "current" {}


######################################################  RESOURCES  #####################################################


# nikita portal Service
module "nikita-portal" {
  source = "../../../../../modules/terraform/aws/ecs/service"

  name                               = local.name
  host_port                          = "0"
  desired_count                      = "2"
  container_name                     = local.service
  container_port                     = "3000"
  volume_name                        = "nikita-portal-log"
  instance_volume_path               = "/var/log/nikita-portal"
  min_capacity                       = var.min_capacity
  max_capacity                       = var.max_capacity
  environment                        = local.Environment
  ecs_task_role                      = local.ecs_task_role
  ecs_task_execution_role            = local.ecs_task_execution_role
  ecs_service_role                   = local.ecs_service_role
  container_definition_file          = "hooq-${local.service}.json"
  container_mount_path               = "/usr/local/nikita-portal/logs"
  awslogs_group                      = local.awslogs_group_name
  cluster_name                       = local.cluster_name
  availability_zones                 = local.azs
  region                             = "ap-southeast-1"
  target_group_arn                   = local.alb_targetgroups_arns
  docker_image                       = "quay.io/hooq/nikita-portal"
  docker_tag                         = var.tag
  github_package_credentials_arn     = local.private_registry_token
  aws_account_id                     = data.aws_caller_identity.current.account_id
  deployment_minimum_healthy_percent = local.deployment_minimum_healthy_percent
  deployment_maximum_percent         = local.deployment_maximum_percent
}

### ECS Service ASG
module "aws_ecs_app_autoscaling_target" {
  source             = "../../../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/app_autoscaling_target"
  min_capacity       = var.min_capacity
  max_capacity       = var.max_capacity
  role_arn           = local.ecs_service_role_arn
  ecs_cluster_name   = local.cluster_name
  ecs_service_name   = module.nikita-portal.ecs_service
  scalable_dimension = "ecs:service:DesiredCount"
  service_namespace  = "ecs"
}

module "ecs_service_autoscaling_policy_scale_up" {
  source = "../../../../../modules/terraform/aws/autoscaling/autoscaling_policy/ecs_service_scaling/scaleup"

  name                    = "${module.nikita-portal.ecs_service}-scale-up"
  cooldown                = 60
  ecs_service_name        = module.nikita-portal.ecs_service
  ecs_cluster_name        = local.cluster_name
  adjustment_type         = "ChangeInCapacity"
  metric_aggregation_type = "Maximum"
  scalable_dimension      = "ecs:service:DesiredCount"
  service_namespace       = "ecs"

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

  name                    = "${module.nikita-portal.ecs_service}-scale-down"
  cooldown                = 60
  ecs_service_name        = module.nikita-portal.ecs_service
  ecs_cluster_name        = local.cluster_name
  adjustment_type         = "ChangeInCapacity"
  metric_aggregation_type = "Maximum"
  scalable_dimension      = "ecs:service:DesiredCount"
  service_namespace       = "ecs"

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
  value = module.nikita-portal.ecs_container_definition
}
output "ecs_task_definition_arn" {
  value = module.nikita-portal.ecs_task_definition_arn
}

output "ecs_service_name" {
  value = module.nikita-portal.ecs_service
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
  value = module.cloudwatch_alarm_ecs_service_scaleup.cloudwatch_alarm_arn
}

output "cloudwatch_ecs_service_scaledown_cpuutilization_alarm" {
  value = module.cloudwatch_alarm_ecs_service_scaledown.cloudwatch_alarm_arn
}
