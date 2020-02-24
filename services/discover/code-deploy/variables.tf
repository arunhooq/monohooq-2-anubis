variable "azs" {
  description = "Availability Zones in AWS to be use"
  default     = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env" {
  default = {
    dev = {
      name = "discover_dev"
      desired_task_count = "2"
      max_capacity = "10"
      min_capacity = "2"
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
      name = "discover_stag"
      desired_task_count = "2"
      max_capacity = "10"
      min_capacity = "2"
    }
    prod = {
      name = "discover_prod"
      desired_task_count = "2"
      max_capacity = "10"
      min_capacity = "2"
    }
    play = {
      name = "discover_play"
      desired_task_count = "1"
      max_capacity = "10"
      min_capacity = "2"   
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
  }
}

variable "TF_TOKEN_DISCOVER" {
  type = string
}

variable "TF_TOKEN_CLOUD_SRE" {
  type = string
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "discover"
}

variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "discover"
}

variable "tag" {
  default = "APP_TAG"
}

variable "autoscaling_policy_scaleup_scaling_adjust" {
  default = "1"
}

variable "autoscaling_policy_cooldown" {
  default = "60"
}

variable "autoscaling_policy_scaleup_metric_interval_lower_bound" {
  default = "0"
}

variable "autoscaling_policy_adjustment_type" {
  default = "ChangeInCapacity"
}

variable "autoscaling_policy_metric_aggregation_type" {
  default = "Maximum"
}

variable "ecs_target_scalable_dimension" {
  default = "ecs:service:DesiredCount"
}

variable "service_namespace" {
  default = "ecs"
}

variable "autoscaling_policy_scaledown_metric_interval_upper_bound" {
  default = "0"
}

variable "autoscaling_policy_scaledown_scaling_adjust" {
  default = "1"
}

variable "ecs_service_scaleup_evaluation_periods" {
  default = "1"
}

variable "cloudwatch_alarm_ecs_service_period" {
  default = "120"
}

variable "cloudwatch_alarm_ecs_service_metric_namespace" {
  default = "AWS/ECS"
}

variable "cloudwatch_alarm_ecs_service_metric_statistic" {
  default = "Average"
}

variable "metric_name" {
  default = "CPUUtilization"
}

variable "cloudwatch_alarm_ecs_service_scaleup_operator" {
  default = "GreaterThanOrEqualToThreshold"
}

variable "ecs_service_scaleup_threshold" {
  default = "60"
}

variable "ecs_service_scaleup_datapoints" {
  default = "1"
}

variable "ecs_service_scaledown_datapoints" {
  default = "1"
}

variable "ecs_service_scaledown_evaluation_periods" {
  default = "1"
}

variable "cloudwatch_alarm_ecs_service_scaledown_operator" {
  default = "LessThanThreshold"
}

variable "ecs_service_scaledown_threshold" {
  default = "30"
}

variable "host_port" {
  default     = "0"
  description = "The host port that will be mapped to container port"
}

variable "container_port" {
  default     = "6201"
  description = "The port that the container listens to"
}

variable "aws_region" {
  default = "ap-southeast-1"
}

variable "docker_registry_path" {
  default = "quay.io/hooq"
}
