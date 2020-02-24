variable "availability_zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env" {
  default = {
    dev = {
      name                               = "miyazaki_dev"
      desired_task_count                 = "2"
      max_capacity                       = "10"
      min_capacity                       = "2"
      deployment_minimum_healthy_percent = "50"
      deployment_maximum_percent         = "200"
      #ScaleDown
      step_down_1_scaling_adjustment = -1
      step_down_1_lower_bound        = -5
      step_down_1_upper_bound        = 0
      step_down_2_scaling_adjustment = -2
      step_down_2_lower_bound        = -10
      step_down_2_upper_bound        = -5
      step_down_3_scaling_adjustment = -3
      step_down_3_upper_bound        = -10
      #Scaleup
      step_up_1_scaling_adjustment = 1
      step_up_1_lower_bound        = 0
      step_up_1_upper_bound        = 15
      step_up_2_scaling_adjustment = 2
      step_up_2_lower_bound        = 15
      step_up_2_upper_bound        = 25
      step_up_3_scaling_adjustment = 3
      step_up_3_lower_bound        = 25
    }
    stag = {
      name                               = "miyazaki_stag"
      desired_task_count                 = "2"
      max_capacity                       = "10"
      min_capacity                       = "2"
      deployment_minimum_healthy_percent = "50"
      deployment_maximum_percent         = "200"
      #ScaleDown
      step_down_1_scaling_adjustment = -1
      step_down_1_lower_bound        = -5
      step_down_1_upper_bound        = 0
      step_down_2_scaling_adjustment = -2
      step_down_2_lower_bound        = -10
      step_down_2_upper_bound        = -5
      step_down_3_scaling_adjustment = -3
      step_down_3_upper_bound        = -10
      #Scaleup
      step_up_1_scaling_adjustment = 1
      step_up_1_lower_bound        = 0
      step_up_1_upper_bound        = 15
      step_up_2_scaling_adjustment = 2
      step_up_2_lower_bound        = 15
      step_up_2_upper_bound        = 25
      step_up_3_scaling_adjustment = 3
      step_up_3_lower_bound        = 25
    }
    prod = {
      name                               = "miyazaki_prod"
      desired_task_count                 = "2"
      max_capacity                       = "100"
      min_capacity                       = "2"
      deployment_minimum_healthy_percent = "50"
      deployment_maximum_percent         = "200"
      #ScaleDown
      step_down_1_scaling_adjustment = -2
      step_down_1_lower_bound        = -5
      step_down_1_upper_bound        = 0
      step_down_2_scaling_adjustment = -4
      step_down_2_lower_bound        = -10
      step_down_2_upper_bound        = -5
      step_down_3_scaling_adjustment = -6
      step_down_3_upper_bound        = -10
      #Scaleup
      step_up_1_scaling_adjustment = 2
      step_up_1_lower_bound        = 0
      step_up_1_upper_bound        = 15
      step_up_2_scaling_adjustment = 6
      step_up_2_lower_bound        = 15
      step_up_2_upper_bound        = 25
      step_up_3_scaling_adjustment = 20
      step_up_3_lower_bound        = 25
    }
    play = {
      name               = "miyazaki_play"
      desired_task_count = "2"
      max_capacity       = "10"
      min_capacity       = "2"
      #ScaleDown
      step_down_1_scaling_adjustment = -1
      step_down_1_lower_bound        = -5
      step_down_1_upper_bound        = 0
      step_down_2_scaling_adjustment = -2
      step_down_2_lower_bound        = -10
      step_down_2_upper_bound        = -5
      step_down_3_scaling_adjustment = -3
      step_down_3_upper_bound        = -10
      #Scaleup
      step_up_1_scaling_adjustment = 1
      step_up_1_lower_bound        = 0
      step_up_1_upper_bound        = 15
      step_up_2_scaling_adjustment = 2
      step_up_2_lower_bound        = 15
      step_up_2_upper_bound        = 25
      step_up_3_scaling_adjustment = 3
      step_up_3_lower_bound        = 25
    }
  }
}

variable "code_deploy_name" {
  default = "miyazaki"
}

variable "host_port" {
  default     = "0"
  description = "The host port that will be mapped to container port"
}

variable "container_port" {
  default     = "4352"
  description = "The port that the container listens to"
}

variable "docker_registry_path" {
  default = "quay.io/hooq"
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "miyazaki"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "miyazaki"
}

variable "aws_region" {
  default = "ap-southeast-1"
}

variable "tag" {
  default = "APP_TAG"
}

variable "ecs_target_scalable_dimension" {
  default = "ecs:service:DesiredCount"
}

variable "service_namespace" {
  default = "ecs"
}

variable "autoscaling_policy_cooldown" {
  default = "60"
}

variable "autoscaling_policy_adjustment_type" {
  default = "ChangeInCapacity"
}

variable "autoscaling_policy_metric_aggregation_type" {
  default = "Maximum"
}

variable "autoscaling_policy_scaleup_scalable_dimension" {
  default = "ecs:service:DesiredCount"
}


variable "metric_name" {
  default = "CPUUtilization"
}

variable "cloudwatch_alarm_ecs_service_metric_statistic" {
  default = "Average"
}

variable "cloudwatch_alarm_ecs_service_metric_namespace" {
  default = "AWS/ECS"
}

variable "cloudwatch_alarm_ecs_service_scaleup_operator" {
  default = "GreaterThanOrEqualToThreshold"
}

variable "cloudwatch_alarm_ecs_service_scaledown_operator" {
  default = "LessThanThreshold"
}

variable "ecs_service_scaledown_threshold" {
  default = "30"
}

variable "ecs_service_scaleup_threshold" {
  default = "60"
}

variable "cloudwatch_alarm_ecs_service_period" {
  default = "120"
}

variable "ecs_service_scaleup_evaluation_periods" {
  default = "1"
}

variable "ecs_service_scaledown_evaluation_periods" {
  default = "1"
}

variable "ecs_service_scaleup_datapoints" {
  default = "1"
}

variable "ecs_service_scaledown_datapoints" {
  default = "1"
}
