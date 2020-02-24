variable "name" {
  default = ""
}
variable "host_port" {
  default = ""
}
variable "desired_count" {
  default = ""
}
variable "container_name" {
  default = ""
}
variable "container_port" {
  default = ""
}
variable "volume_name" {
  default = ""
}
variable "instance_volume_path" {
  default = ""
}
variable "min_capacity" {
  default = ""
}
variable "max_capacity" {
  default = ""
}
variable "environment" {
  default = ""
}
variable "ecs_task_role" {
  default = ""
}
variable "ecs_task_execution_role" {
  default = ""
}
variable "container_definition_file" {
  default = ""
}
variable "container_mount_path" {
  default = ""
}
variable "awslogs_group" {
  default = ""
}
variable "cluster_name" {
  default = ""
}
variable "availability_zones" {
  default = ""
}
variable "target_group_arn" {
  default = []
  type = list(string)
}
variable "docker_image" {
  default = ""
}
variable "docker_tag" {
  default = ""
}
variable "region" {
  default = ""
}
variable "ecs_service_role" {
  default = ""
}
variable "github_package_credentials_arn" {
  default = ""
}
variable "aws_account_id" {
  default = ""
}
variable "deployment_minimum_healthy_percent" {
  default = ""
}
variable "deployment_maximum_percent" {
  default = ""
}