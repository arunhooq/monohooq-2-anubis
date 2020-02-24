provider "aws" {
  alias      = "initiator"
  region     = "ap-southeast-1"
  access_key = "AWS_INITIATOR_ACCESS_KEY"
  secret_key = "AWS_INITIATOR_SECRET_KEY"
}

provider "aws" {
  alias      = "accepter"
  region     = "ap-southeast-1"
  access_key = "AWS_ACCEPTER_ACCESS_KEY"
  secret_key = "AWS_ACCEPTER_SECRET_KEY"
}

module "vpc_peering" {
  source    = "../../modules/terraform/aws/anubis_vpc_peering"
  providers = {
    aws.accepter  = aws.accepter
    aws.initiator = aws.initiator
  }
  
  initiator_vpc_id            = "${module.vpc.vpc_id}"
  initiator_public_rt_id      = "${module.external_route.route_table_id}"
  initiator_private_rt_id     = "${element(module.internal_private_route.route_table_id, 0)}"
  accepter_vpc_id             = local.vpc_peer_accepter_id
  service_name                = local.name
  environment                 = terraform.workspace
}

output "initiator_account_id" {
  value = module.vpc_peering.initiator_account_id
}

output "accepter_account_id" {
  value = module.vpc_peering.accepter_account_id
}

output "initiator_public_routetable_id" {
  value = module.vpc_peering.initiator_public_routetable_id
}

output "initiator_private_routetable_id" {
  value = module.vpc_peering.initiator_private_routetable_id
}

output "vpc_peer_status" {
  value = module.vpc_peering.peering_vpc_status
}
