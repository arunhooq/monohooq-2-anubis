variable "stream_name" {
  description = "(Optional, Forces new resource) AWS kinesis-stream name. If omitted, Terraform will assign a random, unique name."
  type        = "string"
  default     = null
}

variable "shard_count" {
  description = "(Optional) AWS kinesis-stream."
  type        = number
  default     = 1
}

variable "retention_period" {
  description = "(Optional) AWS kinesis-stream data-record retention period in hours."
  type        = number
  default     = 24
}

variable "shard_level_metrics" {
  description = "(Optional) AWS kinesis-stream shard level metrics"
  type        = list(string)
  default     = ["IncomingBytes", "OutgoingBytes"]
}

variable "tags" {
  description = "(Optional) A mapping of tags to assign to AWS kinesis stream."
  type        = map(string)
  default     = {}
}
