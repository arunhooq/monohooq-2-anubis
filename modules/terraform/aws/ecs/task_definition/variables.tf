variable "name" {
  default = ""
}
variable "host_port" {
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
  type    = list(string)
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
variable "command" {
  default = ""
}
variable "task_requires_compatibilities" {
  description = "Set to FARGATE if the task definition is intenteded to be executed with Fargate"
  default     = []
  type        = list(string)
}
variable "task_cpu" {
  description = "Must be set if task_requires_compatibilities is set to FARGATE"
  default     = ""
}
variable "task_memory" {
  description = "Must be set if task_requires_compatibilities is set to FARGATE"
  default     = ""
}
variable "task_network_mode" {
  description = "Docker networking mode"
  default     = "bridge"
}
variable "tags" {
  description = "Common service and env tags"
  type        = map(string)
  default     = {}
}
