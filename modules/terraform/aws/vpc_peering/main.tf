# Providers are required because of cross-region
provider "aws" {
  alias = "initiator"
}

provider "aws" {
  alias = "accepter"
}

data "aws_caller_identity" "initiator" {
  provider = aws.initiator
}
data "aws_region" "initiator" {
  provider = aws.initiator
}

data "aws_caller_identity" "accepter" {
  provider = aws.accepter
}

data "aws_region" "accepter" {
  provider = aws.accepter
}

#### All this related VPC peering resources will be created only stag and prod with this count condition based on environment value ####

data "aws_vpc" "initiator_vpc" {
  count         = "${(var.environment == "play" ) ? 0 : (var.environment == "dev" ) ? 0 : 1 }"
  provider      = aws.initiator
  id            = "${var.initiator_vpc_id}"
}

data "aws_vpc" "accepter_vpc" {
  count         = "${(var.environment == "play" ) ? 0 : (var.environment == "dev" ) ? 0 : 1 }"
  provider      = aws.accepter
  id            = "${var.accepter_vpc_id}"
}

resource "aws_vpc_peering_connection" "vpc_peering" {
  count         = "${(var.environment == "play" ) ? 0 : (var.environment == "dev" ) ? 0 : 1 }"
  provider      = aws.initiator
  peer_owner_id = data.aws_caller_identity.accepter.account_id
  peer_vpc_id   =  concat(data.aws_vpc.accepter_vpc.*.id, [""])[0]
  vpc_id        =  concat(data.aws_vpc.initiator_vpc.*.id, [""])[0]
  auto_accept   = false
  tags          = {
                  Name = "Peering-VPC-${var.service_name}"
                }
}

resource "aws_vpc_peering_connection_accepter" "vpc_peering_accepter" {
  count                     = "${(var.environment == "play" ) ? 0 : (var.environment == "dev" ) ? 0 : 1 }"
  provider                  = aws.accepter
  vpc_peering_connection_id = concat(aws_vpc_peering_connection.vpc_peering.*.id,[""])[0]
  auto_accept               = "${(var.environment == "play" ) ? false : (var.environment == "dev" ) ? false : true }"
  tags                      = {
                              Name = "Peering-VPC-${var.service_name}"
                             }
}

resource "aws_route" "peer_vpc_private_route_table" {
  count                     = "${(var.environment == "play" ) ? 0 : (var.environment == "dev" ) ? 0 : 1 }"
  provider                  = aws.initiator
  route_table_id            = var.initiator_private_rt_id
  destination_cidr_block    = concat(data.aws_vpc.accepter_vpc.*.cidr_block,[""])[0]
  vpc_peering_connection_id = concat(aws_vpc_peering_connection.vpc_peering.*.id,[""])[0]
}

resource "aws_route" "peer_vpc_public_route_table" {
  count                     = "${(var.environment == "play" ) ? 0 : (var.environment == "dev" ) ? 0 : 1 }"
  provider                  = aws.initiator
  route_table_id            = var.initiator_public_rt_id
  destination_cidr_block    = concat(data.aws_vpc.accepter_vpc.*.cidr_block,[""])[0]
  vpc_peering_connection_id = concat(aws_vpc_peering_connection.vpc_peering.*.id,[""])[0]
}

#######################################################################################################################################