// general
locals {
  name = "${var.service_name}-${terraform.workspace}"

  api_name               = "${var.service_name}-api-${terraform.workspace}"
  portal_name            = "${var.service_name}-portal-${terraform.workspace}"
  kong_main_name         = "${var.service_name}-kong-main-${terraform.workspace}"
  kong_admin_name        = "${var.service_name}-kong-admin-${terraform.workspace}"
  script_runner_name     = "${var.service_name}-script-runner-${terraform.workspace}"
  api_domain_name        = "${var.service_name}-api"
  portal_domain_name     = "${var.service_name}-portal"
  kong_main_domain_name  = "${var.service_name}-kong-main"
  kong_admin_domain_name = "${var.service_name}-kong-admin"
  lambda_role_name       = "${var.service_name}-lambda-role-${terraform.workspace}"
  lambda_policy_name     = "${var.service_name}-lambda-policy-${terraform.workspace}"
  lambda_sg_name         = "${var.service_name}-lambda-security-group-${terraform.workspace}"

  tags = {
    service-name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }
}

// network
locals {
  cidr                      = var.env[terraform.workspace].cidr
  private_subnets           = split(",", var.env[terraform.workspace].cidrs_private)
  public_subnets            = split(",", var.env[terraform.workspace].cidrs_public)
  database_subnets          = split(",", var.env[terraform.workspace].cidrs_database)
  max_private_subnet_length = max(length(local.private_subnets), )
  max_public_subnet_length  = max(length(local.public_subnets), )
}

// names
locals {
  vpc_name         = "${local.name}-vpc"
  nat_gateway_name = "${local.name}-nat-gateway"
  domain_name      = var.env[terraform.workspace].domain_name

  // kinesis streams
  account_stream_name                  = "${local.name}-stream-account"
  transaction_stream_name              = "${local.name}-stream-transaction"
  account_stream_read_policy_name      = "${local.name}-stream-account-policy-read"
  account_stream_write_policy_name     = "${local.name}-stream-account-policy-write"
  transaction_stream_read_policy_name  = "${local.name}-stream-transaction-policy-read"
  transaction_stream_write_policy_name = "${local.name}-stream-transaction-policy-write"

  // subnets
  private_subnet_name  = "${local.name}-subnet-private"
  public_subnet_name   = "${local.name}-subnet-public"
  database_subnet_name = "${local.name}-subnet-database"

  // routes
  public_route_name  = "${local.name}-route-public"
  private_route_name = "${local.name}-route-private"
}

// counts
locals {
  // kinesis
  account_stream_shard_count     = var.env[terraform.workspace].account_stream_shard_count
  transaction_stream_shard_count = var.env[terraform.workspace].transaction_stream_shard_count
}

// nikita database
locals {
  postgres_db_cluster_name              = "${var.service_name}-${terraform.workspace}-db"
  postgres_db_name                      = "${var.service_name}_db"
  postgres_db_username                  = "${var.service_name}_db_user"
  postgres_db_skip_final_snapshot       = var.env[terraform.workspace].postgres_db_skip_final_snapshot
  postgres_db_final_snapshot_identifier = var.env[terraform.workspace].postgres_db_final_snapshot_identifier
}

// kong database
locals {
  kong_db_cluster_name              = "kong-${terraform.workspace}-db"
  kong_db_name                      = "kong_db"
  kong_db_username                  = "kong_db_user"
  kong_db_skip_final_snapshot       = var.env[terraform.workspace].postgres_db_skip_final_snapshot
  kong_db_final_snapshot_identifier = var.env[terraform.workspace].postgres_db_final_snapshot_identifier
}

// SG rules
locals {
  sg_ingress_rules = [
    {
      description = "ALLOW RDS"
      from_port   = var.postgres_db_port
      to_port     = var.postgres_db_port
      protocol    = "tcp"
      cidr_blocks = local.cidr
    }
  ]
}

//ecs

locals {
  api_target_groups = [
    {
      name             = local.api_name
      backend_protocol = "HTTP"
      backend_port     = 8080
      slow_start       = 0
      backend_path     = "/health"
      target_type      = "instance"
    }
  ]

  portal_target_groups = [
    {
      name             = local.portal_name
      backend_protocol = "HTTP"
      backend_port     = 3000
      slow_start       = 0
      backend_path     = "/"
      target_type      = "instance"
    }
  ]

  kong_main_target_groups = [
    {
      name             = local.kong_main_name
      backend_protocol = "HTTP"
      backend_port     = 8080
      slow_start       = 0
      backend_path     = "/api/health"
      target_type      = "instance"
    }
  ]

  kong_admin_target_groups = [
    {
      name             = local.kong_admin_name
      backend_protocol = "HTTP"
      backend_port     = 8080
      slow_start       = 0
      backend_path     = "/"
      target_type      = "instance"
    }
  ]

  http_tcp_listeners = [
    {
      port     = 80
      protocol = "HTTP"
    }
  ]

  https_tcp_listeners = [
    {
      port     = 443
      protocol = "HTTPS"
    }
  ]

  alb_sg_ingress_rules = [
    {
      description = "ALLOW HTTP"
      from_port   = "80"
      to_port     = "80"
      protocol    = "tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      description = "ALLOW HTTPS"
      from_port   = "443"
      to_port     = "443"
      protocol    = "tcp"
      cidr_blocks = "0.0.0.0/0"
    }
  ]

  asg_sg_ingress_rules = [
    {
      description = "ALLOW ALL PORTS INSIDE VPC CIDR"
      from_port   = var.any_port
      to_port     = var.to_port
      protocol    = "tcp"
      cidr_blocks = local.cidr
    },
    {
      description = "ALLOW HTTPS"
      from_port   = "443"
      to_port     = "443"
      protocol    = "tcp"
      cidr_blocks = local.cidr
    }
  ]
  alb_s3_bucket_name       = var.env[terraform.workspace] == "prod" ? var.env[terraform.workspace].alb_s3_bucket_name : ""
  domainname               = var.env[terraform.workspace].domain_name
  ec2_instance_type        = var.env[terraform.workspace].ec2_instance_type
  ec2_root_volume_size     = var.env[terraform.workspace].ec2_root_volume_size
  asg_name_instances_scale = "asg_policy${terraform.workspace}"
}

//logs
locals {
  api_log_name_map = {
    docker     = "${local.api_name}/var/log/docker"
    monitoring = "${local.api_name}-logs"
    dmesg      = "${local.api_name}/var/log/dmesg"
    ecs-init   = "${local.api_name}/var/log/ecs/ecs-init.log"
    ecs-agent  = "${local.api_name}/var/log/ecs/ecs-agent.log"
    metricbeat = "${local.api_name}-metricbeat-logs"
    audit      = "${local.api_name}/var/log/ecs/audit.log"
    messages   = "${local.api_name}/var/log/messages"
  }

  portal_log_name_map = {
    docker     = "${local.portal_name}/var/log/docker"
    monitoring = "${local.portal_name}-logs"
    dmesg      = "${local.portal_name}/var/log/dmesg"
    ecs-init   = "${local.portal_name}/var/log/ecs/ecs-init.log"
    ecs-agent  = "${local.portal_name}/var/log/ecs/ecs-agent.log"
    metricbeat = "${local.portal_name}-metricbeat-logs"
    audit      = "${local.portal_name}/var/log/ecs/audit.log"
    messages   = "${local.portal_name}/var/log/messages"
  }

  kong_main_log_name_map = {
    docker     = "${local.kong_main_name}/var/log/docker"
    monitoring = "${local.kong_main_name}-logs"
    dmesg      = "${local.kong_main_name}/var/log/dmesg"
    ecs-init   = "${local.kong_main_name}/var/log/ecs/ecs-init.log"
    ecs-agent  = "${local.kong_main_name}/var/log/ecs/ecs-agent.log"
    metricbeat = "${local.kong_main_name}-metricbeat-logs"
    audit      = "${local.kong_main_name}/var/log/ecs/audit.log"
    messages   = "${local.kong_main_name}/var/log/messages"
  }

  kong_admin_log_name_map = {
    docker     = "${local.kong_admin_name}/var/log/docker"
    monitoring = "${local.kong_admin_name}-logs"
    dmesg      = "${local.kong_admin_name}/var/log/dmesg"
    ecs-init   = "${local.kong_admin_name}/var/log/ecs/ecs-init.log"
    ecs-agent  = "${local.kong_admin_name}/var/log/ecs/ecs-agent.log"
    metricbeat = "${local.kong_admin_name}-metricbeat-logs"
    audit      = "${local.kong_admin_name}/var/log/ecs/audit.log"
    messages   = "${local.kong_admin_name}/var/log/messages"
  }

  script_runner_log_name_map = {
    docker     = "${local.script_runner_name}/var/log/docker"
    monitoring = "${local.script_runner_name}-logs"
    dmesg      = "${local.script_runner_name}/var/log/dmesg"
    ecs-init   = "${local.script_runner_name}/var/log/ecs/ecs-init.log"
    ecs-agent  = "${local.script_runner_name}/var/log/ecs/ecs-agent.log"
    metricbeat = "${local.script_runner_name}-metricbeat-logs"
    audit      = "${local.script_runner_name}/var/log/ecs/audit.log"
    messages   = "${local.script_runner_name}/var/log/messages"
  }
}
