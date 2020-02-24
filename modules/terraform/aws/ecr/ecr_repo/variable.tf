variable "ecrname" {
  description = "service name"
}

### Tags
variable "environment" {
  description = "Tag: Environment tag, e.g prod"
}

variable "versions" {
  description = "Tag: Version for this resources"
}

variable "owner" {
  description = "Tag: Will be attached to instance as a tag. Will indicate who will be the owner for the resources."
}

variable "service-name" {
  description = "Tag: Service Name for all resources"
}

