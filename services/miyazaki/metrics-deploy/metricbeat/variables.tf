variable "aws_region" {
  default = "ap-southeast-1"
}

variable "availability_zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "metricbeat-miyazaki"
}
variable "app" {
  description = "Tag: Owner of the resources"
  default     = "miyazaki"
}

variable "tag" {
  default = "GITHUBSHA"
}

variable "docker_image" {
  default = "quay.io/hooq/metricbeat"
}

variable "docker_image_tag" {
  default = "7.5.2"
}

variable "scheduling_strategy" {
  default = "DAEMON"
}
