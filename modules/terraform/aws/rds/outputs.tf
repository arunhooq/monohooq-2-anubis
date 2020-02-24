output "instance_id" {
  value       = join("", aws_db_instance.db.*.id)
  description = "ID of the instance"
}

output "instance_address" {
  value       = join("", aws_db_instance.db.*.address)
  description = "Address of the instance"
}

output "instance_endpoint" {
  value       = join("", aws_db_instance.db.*.endpoint)
  description = "DNS Endpoint of the instance"
}

output "subnet_group_id" {
  value       = join("", aws_db_subnet_group.default.*.id)
  description = "ID of the Subnet Group"
}

output "parameter_group_id" {
  value       = join("", aws_db_parameter_group.default.*.id)
  description = "ID of the Parameter Group"
}

output "option_group_id" {
  value       = join("", aws_db_option_group.default.*.id)
  description = "ID of the Option Group"
}
