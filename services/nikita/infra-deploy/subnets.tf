module "private_subnet" {
  source = "../../../modules/terraform/aws/network/subnets"

  azs          = var.azs
  subnets_cidr = local.private_subnets
  name         = local.private_subnet_name
  vpc_id       = module.vpc.vpc_id
  tags         = local.tags
}

module "public_subnet" {
  source = "../../../modules/terraform/aws/network/subnets"

  azs          = var.azs
  subnets_cidr = local.public_subnets
  name         = local.public_subnet_name
  vpc_id       = module.vpc.vpc_id
  tags         = local.tags
}

module "database_subnet" {
  source = "../../../modules/terraform/aws/network/subnets"

  azs          = var.azs
  subnets_cidr = local.database_subnets
  name         = local.database_subnet_name
  vpc_id       = module.vpc.vpc_id
  tags         = local.tags
}

output "public_subnet_ids" {
  value = module.public_subnet.subnet_ids
}

output "private_subnet_ids" {
  value = module.private_subnet.subnet_ids
}

output "database_subnet_ids" {
  value = module.database_subnet.subnet_ids
}
