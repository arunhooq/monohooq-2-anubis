variable "availability-zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env"  {{TAGS}}

variable "min_capacity" {
  default = "1"
}

variable "max_capacity" {
  default = "3"
}

variable "service-name" {
  description = "Tag: Service Name for all resources"
  default     = "{{SERVICE_NAME}}"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "{{SERVICE_NAME}}"
}

variable "tag" {
  default = "GITHUBSHA"
}