variable "service_name" {
  description = "Tag: Service Name for all resources"
  type        = string
  default     = "nikita"
}

variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "nikita"
}

variable "tag" {
  description = "github commit tag"
  type        = string
  default     = "SED_APP_TAG"
}

variable "stream_name" {
  description = "kinesis stream_name"
  type        = string
  default     = "account_stream"
}

variable "lambda_runtime" {
  description = "lambda runtime"
  type        = string
  default     = "go1.x"
}
