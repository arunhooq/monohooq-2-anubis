module "nat_gateway" {
  source = "../../../modules/terraform/aws/network/nat_gateway"

  public_subnet_ids     = module.public_subnet.subnet_ids.*
  name                  = local.nat_gateway_name
  tags                  = local.tags
  azs                   = var.azs
}
