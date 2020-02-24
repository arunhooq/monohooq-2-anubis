output "initiator_account_id" {
  value = data.aws_caller_identity.initiator.account_id
}

output "accepter_account_id" {
  value = data.aws_caller_identity.accepter.account_id
}

output "initiator_vpc_id" {
  value = data.aws_vpc.initiator_vpc.*.id
}

output "accepter_vpc_id" {
  value = data.aws_vpc.accepter_vpc.*.id
}

output "initiator_vpc_cidr" {
  value = data.aws_vpc.initiator_vpc.*.cidr_block
}

output "accepter_vpc_cidr" {
  value = data.aws_vpc.accepter_vpc.*.cidr_block
}

output "initiator_public_routetable_id" {
  value = aws_route.peer_vpc_public_route_table.*.route_table_id
}

output "initiator_private_routetable_id" {
  value = aws_route.peer_vpc_private_route_table.*.route_table_id
}

output "peering_vpc_status" {
  value = aws_vpc_peering_connection.vpc_peering.*.accept_status
}
