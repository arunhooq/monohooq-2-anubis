module "route_53_inbound" {
  source             = "../../../modules/terraform/aws/route53_inbound"
  inbound_name       = "miyazaki_inbound"
  sec_group          = [module.alb_sg.sg_id]
  inbound_ip_address = module.internal_private_subnets.subnet_ids
}
