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

module "domain_name_external" {
  source                 = "../../../modules/terraform/aws/route53/recordsets"
  domainname_record_list = local.domainname_record_list
  domainname             = local.domainname
  zone_id                = data.aws_route53_zone.main.zone_id
  alb_zone_id            = "${module.alb.alb_zone_id}"
  recordtype             = "CNAME"
  ttl                    = "60"
  domain_record          = "${var.service_name}.${terraform.workspace}-hooq.tv"
}


#######################################################  OUTPUTS #######################################################

output "name_of_app_domain" {
  value = "https://${module.domain_name.name_of_app_domain}.${terraform.workspace}-hooq.tv"
}
