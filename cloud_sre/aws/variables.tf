variable "Domain-Name" {
  default = {
    dev = {
      hosted_zone = "dev-hooq.tv."
      domain_name = ["*.dev-hooq.tv"]
      subject_alternative_names = ["dev-hooq.tv", "dev-sg-grab.dev-hooq.tv"]

    }
    prod = {
      hosted_zone = "prod-hooq.tv."
      domain_name = ["*.prod-hooq.tv"]
      subject_alternative_names = ["prod-hooq.tv"]
      }
    stag = {
      hosted_zone = "stag-hooq.tv."
      domain_name = ["*.stag-hooq.tv"]
      subject_alternative_names = ["stag-hooq.tv"]
    }
    play = {
      hosted_zone = "play-hooq.tv."
      domain_name = ["*.play-hooq.tv"]
      subject_alternative_names = ["play-hooq.tv"]
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
