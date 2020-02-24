variable "name" {
  description = "The name of redis."
  type  = string
}

variable "redis_description" {
  description = "The description of redis."
  type  = string
}

variable "automatic_failover_enabled" {
  description = "Specifies whether a read-only replica will be automatically promoted to read/write primary if the existing primary fails."
  default = false
}

variable "desired_clusters" {
  description = "The number of cache clusters (primary and replicas) this replication group will have."
  default = "1"
}

variable "instance_type" {
  description = "The compute and memory capacity of the nodes in the node group."
  default = "cache.t2.micro"
}

variable "engine_version" {
  description = "The version number of the cache engine to be used for the cache clusters in this replication group."
  default = "3.2.4"
}

variable "parameter_group_name" {
  description = "The name of existing parameter group to associate with this replication group."
  default = ""
}

variable "redis_parameter_group" {
  description = "The name of the parameter group family to associate with this replication group."
  default = null
}

variable "redis_parameter" {
  type = list(object({
    name         = string
    value        = string
  }))
  default     = []
  description = "A list of redis parameters to apply. Note that parameters may differ from a DB family to another for more details: https://docs.aws.amazon.com/AmazonElastiCache/latest/red-ug/ParameterGroups.Redis.html#ParameterGroups.Redis.3-2-4"
}

variable "subnet_ids" {
  description = "One or more Amazon VPC subnet IDs that will be associated to elasticache subnet group."
  type        = list(string)
  default     = []
}

variable "redis_subnet_group" {
  description = "The name of existing cache subnet group to be used for the replication group."
  default = ""
}


variable "security_groups" {
  description = "One or more Amazon VPC security groups associated with this replication group."
  type        = list(string)
  default     = []
}

variable "maintenance_window" {
  description = "Specifies the weekly time range for when maintenance on the cache cluster is performed."
  default = null
}

variable "notification_topic_arn" {
  description = "An Amazon Resource Name (ARN) of an SNS topic to send ElastiCache notifications to."
  default = null
}

variable "redis_port" {
  description = "The port number on which each of the cache nodes will accept connections. "
  default = "6379"
}

variable "redis_tags" {
  description = "Additional tags for the redis"
  type        = map(string)
  default     = {}
}

variable "availability_zones" {
  description = "One or more availability zones."
  type        = list(string)
  default     = []
}

variable "transit_encryption_enabled" {
  description = "Whether to enable encryption in transit."
  default = false
}

variable "auth_token" {
  description = "The password used to access a password protected server. Can be specified only if transit_encryption_enabled = true."
  default = null
}