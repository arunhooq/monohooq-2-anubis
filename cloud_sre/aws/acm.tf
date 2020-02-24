########################################################  VARIABLES  #####################################################

locals {
  hosted_zone		= var.Domain-Name[terraform.workspace].hosted_zone
  domain_name		= var.Domain-Name[terraform.workspace].domain_name
  subject_alternative_names= var.Domain-Name[terraform.workspace].subject_alternative_names
  comment = var.tag[terraform.workspace].comment
  tags = {
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag[terraform.workspace].version
    name         = "hooq-${terraform.workspace}"
  }
}

#######################################################  RESOURCES  #####################################################

# only used for play,dev and staging
data "aws_route53_zone" "main" {
  name = local.hosted_zone
  private_zone = false
}


module "aws_ssl_certs" {
  source = "../../modules/terraform/aws/acm"
  domainname = local.domain_name
  zone_id  = data.aws_route53_zone.main.zone_id
  subject_alternative_names = local.subject_alternative_names
  wait_for_validation = true # false
  tags = local.tags
}

#######################################################  OUTPUTS #######################################################

output "acm_cert_arn" {
  value = module.aws_ssl_certs.acm_certificate_arn
}
