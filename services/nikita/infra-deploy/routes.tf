module "public_route" {
  source = "../../../modules/terraform/aws/network/route_table/external"

  azs        = var.azs
  vpc_id     = module.vpc.vpc_id
  name       = local.public_route_name
  gateway_id = module.vpc.igw_id.*
  subnet_ids = module.public_subnet.subnet_ids.*
  subnets    = local.public_subnets
  tags       = local.tags
}

module "private_route" {
  source = "../../../modules/terraform/aws/network/route_table/internal"

  azs               = var.azs
  vpc_id            = module.vpc.vpc_id
  name              = "${local.name}-route-private"
  tags              = local.tags
  subnet_ids        = concat(module.private_subnet.subnet_ids.*, module.database_subnet.subnet_ids.*)
  subnets           = local.private_subnets
  gateway_id        = module.nat_gateway.natgw_id.*
  attach_natgateway = "true"
  max_subnet_length = local.max_private_subnet_length
}

module "database_route" {
  source = "../../../modules/terraform/aws/network/route_table/internal"

  azs               = var.azs
  vpc_id            = module.vpc.vpc_id
  name              = "${local.name}-route-database"
  tags              = local.tags
  subnet_ids        = module.database_subnet.subnet_ids.*
  subnets           = local.database_subnets
  gateway_id        = module.nat_gateway.natgw_id.*
  attach_natgateway = "true"
  max_subnet_length = local.max_private_subnet_length
}
