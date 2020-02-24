variable "region" {
  description = "AWS region"
  default     = "ap-southeast-1"
}

variable "code_deploy_name" {
  default = "nikita-kong-migration"
}

variable "service_name" {
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

variable "task_requires_compatibilities" {
  default = ["FARGATE"]
  type    = list(string)
}

variable "task_cpu" {
  default = "256"
}

variable "task_memory" {
  default = "512"
}

variable "task_network_mode" {
  default = "awsvpc"
}

variable "db_migration_command" {
  description = "Run DB migration command (e.g. mix ecto.migrate)"
  default     = "DB_MIGRATION_COMMAND"
}
