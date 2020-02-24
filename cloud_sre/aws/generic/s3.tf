######################################################  VARIABLES  #####################################################

variable "name" {
  default = "hooq-alb-logs"
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

  tags = {
    owner        = var.owner
    Environment  = terraform.workspace
    Version      = "0.1"
  }

  s3-version = formatdate("YYYY-MM-DD", timestamp())
}


######################################################  RESOURCES  #####################################################
data "aws_elb_service_account" "main" {}

module "alb_accesslogs_s3_bucket" {
  source = "../../../modules/terraform/aws/s3"

  name          = var.name
  acl           = var.acl
  force_destroy = var.force-destroy
  tags          = local.tags
  versioning = {
    enabled = var.versioning
  }
  s3_tags = {
    Name = var.name
  }
  policy = <<EOF
{
      "Version": "2012-10-17",
      "Statement": [
          {
              "Effect": "Allow",
              "Action": [
                "s3:PutObject"
              ],
              "Resource": "arn:aws:s3:::${var.name}/*",
              "Principal": {
                "AWS": [
                  "${data.aws_elb_service_account.main.arn}"
                ]
              }
          }
      ]
  }
EOF
}




#######################################################  OUTPUTS #######################################################
output "s3_bucket_name" {
  value = module.alb_accesslogs_s3_bucket.s3_bucket_name
}
