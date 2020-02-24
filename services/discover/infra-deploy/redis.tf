module "redis" {
  source                     = "../../../modules/terraform/aws/redis"
  name                       = "redis-${local.name}"
  redis_description          = "redis for discover"
  automatic_failover_enabled = false
  desired_clusters           = 1
  instance_type              = local.elasticache_instance_type
  engine_version             = var.elasticache_engine_version
  redis_tags                 = local.tags
  subnet_ids                 = module.internal_elasticache_subnets.subnet_ids
  transit_encryption_enabled = false
  security_groups            = [module.redis_sg.sg_id]
}

output "elasticache_port" {
  value = "${module.redis.elasticache_port}"
}

output "elasticache_endpoint" {
  value = "${module.redis.elasticache_endpoint}"
}
