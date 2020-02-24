variable "Domain-Name" {
  default = {
    dev = {
      domain_name = "dev-hooq.tv"
    }
    prod = {
      domain_name = "prod-hooq.tv"
    }
    stag = {
      domain_name = "stag-hooq.tv"
    }
    play = {
      domain_name = "play-hooq.tv"
    }
  }
}



variable "tag" {
  default = {
    dev = {
      version = "0.1"
      comment = "Managed by Terraform"

    }
    prod = {
      version = "0.1"
      comment = "Managed by Terraform"
    }
    play = {
      version = "0.1"
      comment = "Managed by Terraform"
    }
    stag = {
      version = "0.1"
      comment = "Managed by Terraform"
    }
  }
}


variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "cloud_sre"
}

variable "tags" {
  description = "Common service and env tags"
  type        = map(string)
  default     = {}
}
