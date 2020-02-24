###################
# REDIS
###################

resource "aws_elasticache_replication_group" "redis" {
  replication_group_id          = lower(var.name)
  availability_zones            = var.availability_zones
  replication_group_description = var.redis_description
  automatic_failover_enabled    = var.automatic_failover_enabled
  number_cache_clusters         = var.desired_clusters
  node_type                     = var.instance_type
  engine_version                = var.engine_version
  parameter_group_name          = length(var.parameter_group_name) > 0 ? var.parameter_group_name : join("", aws_elasticache_parameter_group.default.*.name)
  subnet_group_name             = length(var.subnet_ids) > 0 ? join("", aws_elasticache_subnet_group.default.*.name) : var.redis_subnet_group
  security_group_ids            = var.security_groups
  maintenance_window            = var.maintenance_window
  notification_topic_arn        = var.notification_topic_arn
  port                          = var.redis_port
  tags                          = merge(
                                        {
                                        "Name" = format("%s", var.name)
                                        },
                                        var.redis_tags,
                                  )
  transit_encryption_enabled    = var.transit_encryption_enabled
  auth_token                    = var.transit_encryption_enabled == true ? var.auth_token : null
}




resource "aws_elasticache_parameter_group" "default" {
  count       = length(var.parameter_group_name) > 0 ? 1 : 0
  name        = "${var.name}-parameter-group"
  family      = var.redis_parameter_group
  description = "${var.redis_description} parameter group"


  dynamic "parameter" {
    for_each = var.redis_parameter
    content {
      name         = parameter.value.name
      value        = parameter.value.value
    }
  }
}


resource "aws_elasticache_subnet_group" "default" {
  count       = length(var.subnet_ids) > 0 ? 1 : 0
  name        = "${var.name}-subnet-group"
  subnet_ids  = var.subnet_ids
  description = "${var.redis_description} subnet group"

}
