variable "domainname" {
  type        = string
  default     = null
}

variable "subdomainame" {
  type        = string
  default     = null
}

variable "recordtype" {
  type        = string
}

variable "ttl" {
  type        = number
  default     = 300
}

variable "domain_record" {
}

variable "domainname_record_list" {
  type = list
  default = null
}

variable "is_A_record" {
  default = false
}

variable "zone_id" {}

variable "alb_zone_id" {}
