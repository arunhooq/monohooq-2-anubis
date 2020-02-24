variable "aws_region" {
  default = "ap-southeast-1"
}

variable "availability_zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "metricbeat-helloworld-app"
}
variable "app" {
  description = "Tag: Owner of the resources"
  default     = "helloworld-app"
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
