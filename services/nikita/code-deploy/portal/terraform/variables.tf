variable "env" {
  default = {
    play = {
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

    dev = {
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
  }
}

variable "availability-zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "min_capacity" {
  default     = "1"
  description = "Minimum number of instances to run."
}

variable "max_capacity" {
  default = "3"
}

variable "service-name" {
  description = "Tag: Service Name for all resources"
  default     = "nikita"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "nikita"
}

variable "tag" {
  default = "SED_APP_TAG"
}
