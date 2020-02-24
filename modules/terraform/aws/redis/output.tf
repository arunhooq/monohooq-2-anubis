output "elasticache_id" {
  value = aws_elasticache_replication_group.redis.id
}

output "elasticache__security_group_id" {
  value = aws_elasticache_replication_group.redis.security_group_ids
}

output "elasticache_port" {
  value = aws_elasticache_replication_group.redis.port
}

output "elasticache_endpoint" {
  value = aws_elasticache_replication_group.redis.primary_endpoint_address
}

output "elasticache_auth_token" {
  value = aws_elasticache_replication_group.redis.auth_token
}
