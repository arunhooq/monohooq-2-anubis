module "postgres_db" {
  source                          = "../../../modules/terraform/aws/rds_aurora"
  name                            = local.postgres_db_cluster_name
  engine                          = var.postgres_engine
  engine_version                  = var.postgres_engine_version
  vpc_id                          = module.vpc.vpc_id
  subnets                         = module.database_subnet.subnet_ids
  username                        = local.postgres_db_username
  password                        = "SED_PG_DB_PASSWORD"
  database_port                   = var.postgres_db_port
  database_name                   = local.postgres_db_name
  replica_count                   = var.postgres_db_replica_count
  allowed_security_groups         = [module.postgres_sg.sg_id]
  allowed_cidr_blocks             = [local.cidr]
  instance_type                   = var.postgres_instance_type
  skip_final_snapshot             = local.postgres_db_skip_final_snapshot
  final_snapshot_identifier       = local.postgres_db_final_snapshot_identifier
  storage_encrypted               = var.postgres_db_storage_encrypted
  apply_immediately               = var.postgres_db_apply_immediately
  monitoring_interval             = var.postgres_db_monitoring_interval
  db_parameter_group_name         = var.postgres_db_parameter_group
  db_cluster_parameter_group_name = var.postgres_db_cluster_group
  enabled_cloudwatch_logs_exports = var.postgres_db_log_exports
  monitoring_rds_service_role_arn = module.monitoring_rds_service_role.role_arn
  security_group_ids              = [module.postgres_sg.sg_id]
  tags                            = local.tags
}

module "kong_db" {
  source                          = "../../../modules/terraform/aws/rds_aurora"
  name                            = local.kong_db_cluster_name
  engine                          = var.postgres_engine
  engine_version                  = var.postgres_engine_version
  vpc_id                          = module.vpc.vpc_id
  subnets                         = module.database_subnet.subnet_ids
  username                        = local.kong_db_username
  password                        = "SED_KONG_DB_PASSWORD"
  database_port                   = var.postgres_db_port
  database_name                   = local.kong_db_name
  replica_count                   = var.postgres_db_replica_count
  allowed_security_groups         = [module.postgres_sg.sg_id]
  allowed_cidr_blocks             = [local.cidr]
  instance_type                   = var.postgres_instance_type
  skip_final_snapshot             = local.kong_db_skip_final_snapshot
  final_snapshot_identifier       = local.kong_db_final_snapshot_identifier
  storage_encrypted               = var.postgres_db_storage_encrypted
  apply_immediately               = var.postgres_db_apply_immediately
  monitoring_interval             = var.postgres_db_monitoring_interval
  db_parameter_group_name         = var.postgres_db_parameter_group
  db_cluster_parameter_group_name = var.postgres_db_cluster_group
  enabled_cloudwatch_logs_exports = var.postgres_db_log_exports
  monitoring_rds_service_role_arn = module.monitoring_rds_service_role.role_arn
  security_group_ids              = [module.postgres_sg.sg_id]
  tags                            = local.tags
}

########################################################################################################################
# Outputs
########################################################################################################################
output "postgres_db_arn" {
  value       = module.postgres_db.this_rds_cluster_arn
  description = "Postgres DB arn"
}

output "postgres_db_endpoint" {
  value       = module.postgres_db.this_rds_cluster_endpoint
  description = "Postgres DB endpoint"
}

output "postgres_db_port" {
  value       = module.postgres_db.this_rds_cluster_port
  description = "Postgres DB port"
}

output "postgres_db_name" {
  value       = module.postgres_db.this_rds_cluster_database_name
  description = "Postgres DB name"
}

output "postgres_db_username" {
  value       = module.postgres_db.this_rds_cluster_master_username
  description = "Postgres DB username"
}

output "kong_db_arn" {
  value       = module.kong_db.this_rds_cluster_arn
  description = "Kong DB arn"
}

output "kong_db_endpoint" {
  value       = module.kong_db.this_rds_cluster_endpoint
  description = "Kong DB endpoint"
}

output "kong_db_port" {
  value       = module.kong_db.this_rds_cluster_port
  description = "Kong DB port"
}

output "kong_db_name" {
  value       = module.kong_db.this_rds_cluster_database_name
  description = "Kong DB name"
}

output "kong_db_username" {
  value       = module.kong_db.this_rds_cluster_master_username
  description = "Kong DB username"
}
