###################
# DB
###################

locals {
  computed_major_engine_version = join(".", slice(split(".", var.engine_version), 0, 2))
  major_engine_version          = var.major_engine_version == "" ? local.computed_major_engine_version : var.major_engine_version
  timestamp = formatdate("YYYYMMDDhhmmss", timestamp())
}

# DB Instance
resource "aws_db_instance" "db" {

  // Required Properties
  name                        = var.database_name
  engine                      = var.engine
  username                    = var.database_user
  password                    = var.database_password
  instance_class              = var.instance_class
  allocated_storage           = var.allocated_storage

  // Optional Properties
  identifier                  = var.database_identifier
  port                        = var.database_port
  engine_version              = var.engine_version
  storage_encrypted           = var.storage_encrypted
  kms_key_id                  = var.kms_key_arn
  vpc_security_group_ids      = var.security_group_ids
  db_subnet_group_name        = length(var.subnet_ids) > 0 ? join("", aws_db_subnet_group.default.*.name) : var.subnet_group_name 
  parameter_group_name        = length(var.parameter_group_name) > 0 ? var.parameter_group_name : join("", aws_db_parameter_group.default.*.name)
  option_group_name           = length(var.option_group_name) > 0 ? var.option_group_name : join("", aws_db_option_group.default.*.name)
  license_model               = var.license_model
  multi_az                    = var.multi_az
  storage_type                = var.storage_type
  iops                        = var.iops
  publicly_accessible         = var.publicly_accessible
  snapshot_identifier         = var.snapshot_identifier
  allow_major_version_upgrade = var.allow_major_version_upgrade
  auto_minor_version_upgrade  = var.auto_minor_version_upgrade
  apply_immediately           = var.apply_immediately
  maintenance_window          = var.maintenance_window
  skip_final_snapshot         = var.skip_final_snapshot
  copy_tags_to_snapshot       = var.copy_tags_to_snapshot
  backup_retention_period     = var.backup_retention_period
  backup_window               = var.backup_window
  deletion_protection         = var.deletion_protection
  final_snapshot_identifier   = length(var.final_snapshot_identifier) > 0 ? var.final_snapshot_identifier : "${var.database_name}-final-snapshot-${local.timestamp}"
  tags                        = merge(
                                    {
                                      "Name" = format("%s", var.database_name)
                                    },
                                    var.db_tags,
                                )

  lifecycle {
    ignore_changes = ["password"]
  }
}

# DB Subnet Group
resource "aws_db_subnet_group" "default" {
  count      = length(var.subnet_ids) > 0 ? 1 : 0
  subnet_ids = var.subnet_ids
  tags = merge(
    {
      "Name" = format("%s", var.database_name)
    },
    var.db_tags,
  )
}

# DB Parameter Group
resource "aws_db_parameter_group" "default" {
  count  = length(var.parameter_group_name) == 0 ? 1 : 0
  family = var.db_parameter_group
  tags = merge(
    {
      "Name" = format("%s", var.database_name)
    },
    var.db_tags,
  )

  dynamic "parameter" {
    for_each = var.db_parameter
    content {
      apply_method = lookup(parameter.value, "apply_method", null)
      name         = parameter.value.name
      value        = parameter.value.value
    }
  }
}

#DB Option Group
resource "aws_db_option_group" "default" {
  count                = length(var.option_group_name) == 0 ? 1 : 0
  engine_name          = var.engine
  major_engine_version = local.major_engine_version
  tags                 = merge(
                          {
                            "Name" = format("%s", var.database_name)
                          },
                          var.db_tags,
                        )

  dynamic "option" {
    for_each = var.db_options
    content {
      db_security_group_memberships  = lookup(option.value, "db_security_group_memberships", null)
      option_name                    = option.value.option_name
      port                           = lookup(option.value, "port", null)
      version                        = lookup(option.value, "version", null)
      vpc_security_group_memberships = lookup(option.value, "vpc_security_group_memberships", null)

      dynamic "option_settings" {
        for_each = lookup(option.value, "option_settings", [])
        content {
          name  = option_settings.value.name
          value = option_settings.value.value
        }
      }
    }
  }

  lifecycle {
    create_before_destroy = true
  }
}