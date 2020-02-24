######################################################  VARIABLES  #####################################################

variable "env" {
  default = {
    play = {
      name              = "hooq-functionbeat-play"
    }
    dev = {
      name              = "hooq-functionbeat-dev"
    }
    stag = {
      name              = "hooq-functionbeat-stag"
    }
    prod = {
      name              = "hooq-functionbeat-prod"
    }
  }

}

variable "acl"{
  description = "Access Control List"
  default     = "private"
}

variable "versioning"{
  description = "Versioning"
  default     = true
}

variable "force-destroy"{
  description = "Force Destroy Option"
  default     = true
}

locals {

  s3-version = formatdate("YYYY-MM-DD", timestamp())
}


######################################################  RESOURCES  #####################################################


module "functionbeat_s3_bucket" {
  source = "../../modules/terraform/aws/s3/bucket"
  name          = var.env[terraform.workspace].name
  acl           = var.acl
  force_destroy = var.force-destroy
  tags          = local.tags
  versioning = {
    enabled = var.versioning
  }
  s3_tags = {
    Name = var.env[terraform.workspace].name
  }
}

#######################################################  OUTPUTS #######################################################

output "functionbeat_s3_bucket_name" {
  value = module.functionbeat_s3_bucket.s3_bucket_name
}
