######################################################  RESOURCES  #####################################################
#creating vpc
module "vpc" {
  source               = "../../../modules/terraform/aws/network/vpc"
  enable_dns_hostnames = "true"
  enable_dns_support   = "true"
  instance_tenancy     = "default"
  cidr                 = local.cidr
  tags                 = local.tags
  name                 = "${local.name}-vpc"
  create_igw           = "true"
}
#creating subnets
module "external_subnets" {
  source       = "../../../modules/terraform/aws/network/subnets"
  azs          = var.azs
  vpc_id       = module.vpc.vpc_id
  subnets_cidr = local.public_subnets
  name         = "${local.name}-public"
  tags         = local.tags
}

module "internal_private_subnets" {
  source       = "../../../modules/terraform/aws/network/subnets"
  azs          = var.azs
  vpc_id       = module.vpc.vpc_id
  subnets_cidr = local.private_subnets
  name         = "${local.name}-private"
  tags         = local.tags
}

module "internal_elasticache_subnets" {
  source       = "../../../modules/terraform/aws/network/subnets"
  azs          = var.azs
  vpc_id       = module.vpc.vpc_id
  subnets_cidr = local.elasticache_subnets
  name         = "${local.name}-elastcicache"
  tags         = local.tags
}

#creating natgw and route_table
module "natgw" {
  source                = "../../../modules/terraform/aws/network/nat_gateway"
  public_subnet_ids     = module.external_subnets.subnet_ids.*
  name                  = "${local.name}-nat-gateway"
  tags                  = local.tags
  azs                   = var.azs
}

module "external_route" {
  source     = "../../../modules/terraform/aws/network/route_table/external"
  azs        = var.azs
  vpc_id     = module.vpc.vpc_id
  name       = "${local.name}-route-public"
  gateway_id = module.vpc.igw_id.*
  subnet_ids = module.external_subnets.subnet_ids.*
  subnets    = local.public_subnets
  tags       = local.tags
}

module "internal_private_route" {
  source            = "../../../modules/terraform/aws/network/route_table/internal"
  azs               = var.azs
  vpc_id            = module.vpc.vpc_id
  name              = "${local.name}-route-private"
  tags              = local.tags
  subnet_ids        = module.internal_private_subnets.subnet_ids.*
  subnets           = local.private_subnets
  gateway_id        = module.natgw.natgw_id.*
  attach_natgateway = "true"
  max_subnet_length = local.max_subnet_length
}

module "internal_elasticache_route" {
  source            = "../../../modules/terraform/aws/network/route_table/internal"
  azs               = var.azs
  vpc_id            = module.vpc.vpc_id
  name              = "${local.name}-route-elasticache"
  tags              = local.tags
  subnet_ids        = module.internal_elasticache_subnets.subnet_ids.*
  subnets           = local.elasticache_subnets
  gateway_id        = module.natgw.natgw_id.*
  max_subnet_length = local.max_subnet_length
  attach_natgateway = "true"
}

module "gateway_vpc_endpoints" {
  source                = "../../../modules/terraform/aws/network/vpcendpoints/gateway_endpoint"
  azs                   = var.azs
  vpc_id                = module.vpc.vpc_id
  name                  = "${local.name}-s3-vpc-endpoint"
  tags                  = local.tags
  service_name          = "s3"
  end_point_type        = "Gateway"
  private_routetable_id = module.internal_private_route.route_table_id.*
}

####################################################### OUTPUTS #######################################################

output "vpc_dhcp_id" {
  value = "${module.vpc.vpc_dhcp_id}"
}

output "vpc_id" {
  value = "${module.vpc.vpc_id}"
}

output "public_subnet_ids" {
  value = "${module.external_subnets.subnet_ids}"
}

output "private_subnet_ids" {
  value = "${module.internal_private_subnets.subnet_ids}"
}

output "elasticache-subnet_ids" {
  value = "${module.internal_elasticache_subnets.subnet_ids}"
}

output "igw_id" {
  value = "${module.vpc.igw_id}"
}

output "eip_nat_ids" {
  value = "${module.natgw.eip_nat_ids}"
}

output "eip_nat_ips" {
  value = "${module.natgw.eip_nat_ips}"
}

output "ngw_id" {
  value = "${module.natgw.natgw_id}"
}

output "public_routetable_id" {
  value = "${module.external_route.route_table_id}"
}

output "private_routetable_id" {
  value = "${module.internal_private_route.route_table_id}"
}

output "elasticache_routetable_id" {
  value = "${module.internal_elasticache_route.route_table_id}"
}

output "vpc_gateway_endpoint_s3_id" {
  value = "${module.gateway_vpc_endpoints.vpc_gateway_endpoint_for_s3}"
}
