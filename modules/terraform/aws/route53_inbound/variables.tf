variable "inbound_name" {}

variable "sec_group" {
  type = list(string)
  default     = []
}

variable "inbound_ip_address" {
  type = any
  default     = []
}