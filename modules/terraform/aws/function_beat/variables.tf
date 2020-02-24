variable "s3_bucket" {
  description = "(Optional, Forces new resource) The name of the bucket. If omitted, Terraform will assign a random, unique name."
  type        = string
  default     = ""
}

variable "service_name" {
  description = "(Optional, Forces new resource) Creates a unique bucket name beginning with the specified prefix. Conflicts with bucket."
  type        = string
  default     = ""
}

variable "environment" {
  description = "(Optional) The canned ACL to apply. Defaults to 'private'."
  type        = string
  default     = ""
}

variable "log_group" {
  description = "(Optional) A valid bucket policy JSON document. Note that if the policy document is not specific enough (but still valid), Terraform may view the policy as constantly changing in a terraform plan. In this case, please make sure you use the verbose/specific version of the policy. For more information about building AWS IAM policy documents with Terraform, see the AWS IAM Policy Document Guide."
  type        = string
  default     = ""
}

variable "cloud_id" {
  description = "(Optional) A mapping of tags to assign to the bucket."
  type        = string
  default     = ""
}

variable "elastic_username" {
  description = "(Optional, Default:false ) A boolean that indicates all objects should be deleted from the bucket so that the bucket can be destroyed without error. These objects are not recoverable."
  type        = string
  default     = ""
}

variable "elastic_password" {
  description = "(Optional) Sets the accelerate configuration of an existing bucket. Can be Enabled or Suspended."
  type        = string
  default     = ""
}

variable "policy_name" {
  description = "(Optional) If specified, the AWS region this bucket should reside in. Otherwise, the region used by the callee."
  type        = string
  default     = "hooq-dev-app-logs-ilm"
}

variable "target" {
  description = "(Optional, Default:false ) A boolean that indicates all objects should be deleted from the bucket so that the bucket can be destroyed without error. These objects are not recoverable."
  type        = string
  default     = ""
}

variable "tags" {
  description = "Additional tags for the S3"
  type        = list
  default     = []
}