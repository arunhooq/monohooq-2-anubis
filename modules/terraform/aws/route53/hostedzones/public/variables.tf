variable "public_hosted_zones" {
  description = "List of domains or subdomains for which to create public hosted zones."
  type        = "list"
  default     = []
}

variable "comment" {}

variable "tags" {}

