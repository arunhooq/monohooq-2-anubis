data "aws_route53_zone" "main" {
  name         = "${local.domain_name}." # Notice the dot!!!
  private_zone = false
}

######################################################  RESOURCES  #####################################################

module "portal_domain_name" {
  source        = "../../../modules/terraform/aws/route53/recordsets"
  domainname    = local.domain_name
  zone_id       = data.aws_route53_zone.main.zone_id
  subdomainame  = local.portal_domain_name
  recordtype    = "A"
  alb_zone_id   = module.portal_alb.alb_zone_id
  domain_record = module.portal_alb.alb_dnsname
  is_A_record   = true
}

module "api_domain_name" {
  source        = "../../../modules/terraform/aws/route53/recordsets"
  domainname    = local.domain_name
  zone_id       = data.aws_route53_zone.main.zone_id
  subdomainame  = local.api_domain_name
  recordtype    = "A"
  alb_zone_id   = module.api_alb.alb_zone_id
  domain_record = module.api_alb.alb_dnsname
  is_A_record   = true
}

module "kong_main_domain_name" {
  source        = "../../../modules/terraform/aws/route53/recordsets"
  domainname    = local.domain_name
  zone_id       = data.aws_route53_zone.main.zone_id
  subdomainame  = local.kong_main_domain_name
  recordtype    = "A"
  alb_zone_id   = module.kong_main_alb.alb_zone_id
  domain_record = module.kong_main_alb.alb_dnsname
  is_A_record   = true
}

module "kong_admin_domain_name" {
  source        = "../../../modules/terraform/aws/route53/recordsets"
  domainname    = local.domain_name
  zone_id       = data.aws_route53_zone.main.zone_id
  subdomainame  = local.kong_admin_domain_name
  recordtype    = "A"
  alb_zone_id   = module.kong_admin_alb.alb_zone_id
  domain_record = module.kong_admin_alb.alb_dnsname
  is_A_record   = true
}

#######################################################  OUTPUTS #######################################################

output "portal_domain" {
  value = "${module.portal_domain_name.name_of_app_domain}.${terraform.workspace}-hooq.tv"
}

output "api_domain" {
  value = "${module.api_domain_name.name_of_app_domain}.${terraform.workspace}-hooq.tv"
}

output "kong_main_domain" {
  value = "${module.kong_main_domain_name.name_of_app_domain}.${terraform.workspace}-hooq.tv"
}

output "kong_admin_domain" {
  value = "${module.kong_admin_domain_name.name_of_app_domain}.${terraform.workspace}-hooq.tv"
}
