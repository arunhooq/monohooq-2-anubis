data "aws_route53_zone" "main" {
  name         = "${local.domainname}." # Notice the dot!!!
  private_zone = false
}

######################################################  RESOURCES  #####################################################

module "domain_name" {
  source        = "../../../modules/terraform/aws/route53/recordsets"
  domainname    = local.domainname
  zone_id       = data.aws_route53_zone.main.zone_id
  subdomainame  = var.service_name
  recordtype    = "A"
  alb_zone_id   = "${module.alb.alb_zone_id}"
  domain_record = "${module.alb.alb_dnsname}"
  is_A_record   = true
}

#######################################################  OUTPUTS #######################################################

output "name_of_app_domain" {
  value = "https://${module.domain_name.name_of_app_domain}.${terraform.workspace}-hooq.tv"
}

