module "vpc" {
  source = "../../../modules/terraform/aws/network/vpc"

  enable_dns_hostnames = "true"
  enable_dns_support   = "true"
  instance_tenancy     = "default"
  cidr                 = local.cidr
  tags                 = local.tags
  name                 = local.vpc_name
  create_igw           = "true"
}

output "vpc_id" {
  value = module.vpc.vpc_id
}
