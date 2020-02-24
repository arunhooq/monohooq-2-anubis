######################################################  Locals  #######################################################

locals {
  name                      = "${var.service_name}-${terraform.workspace}"
  cidr                      = var.env[terraform.workspace].cidr
  private_subnets           = "${split(",", var.env[terraform.workspace].cidrs_private)}"
  public_subnets            = "${split(",", var.env[terraform.workspace].cidrs_public)}"
  elasticache_subnets       = "${split(",", var.env[terraform.workspace].cidrs_elasticache)}"
  max_subnet_length         = max(length(local.private_subnets), )
  domainname                = var.env[terraform.workspace].domain_name

  tags = {
    service_name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }

  target_groups = [
    {
      "name"             = "${local.name}-port80"
      "backend_protocol" = "HTTP"
      "backend_port"     = 80
      "slow_start"       = 0
      "backend_path"     = "/"
      "target_type"      = "instance"
    }
  ]

  http_tcp_listeners = [
    {
      "port"     = 80
      "protocol" = "HTTP"
    }
  ]

  https_tcp_listeners = [
    {
      "port"     = 443
      "protocol" = "HTTPS"
    }
  ]

  #TODO We have to add ACM(AMAZON CERTIFICATE MANAGER) to add HTTPS listeners cert_arn ssl_policy etc...
  asg_name_instances_scale = "asg_policy${terraform.workspace}"
  ec2_root_volume_size     = var.env[terraform.workspace].ec2_root_volume_size
  ec2_instance_type        = var.env[terraform.workspace].ec2_instance_type
  alb_s3_bucket_name       = "${var.env[terraform.workspace] == "prod" ? var.env[terraform.workspace].alb_s3_bucket_name : ""}"
  log_name_map = {
    docker     = "${local.name}/var/log/docker"
    monitoring = "${local.name}-logs"
    dmesg      = "${local.name}/var/log/dmesg"
    ecs-init   = "${local.name}/var/log/ecs/ecs-init.log"
    ecs-agent  = "${local.name}/var/log/ecs/ecs-agent.log"
    metricbeat = "${local.name}-metricbeat-logs"
    audit      = "${local.name}/var/log/ecs/audit.log"
    messages   = "${local.name}/var/log/messages"
  }

  alb_sg_ingress_rules = [
    {
      "description" = "ALLOW HTTP"
      "from_port"   = "80"
      "to_port"     = "80"
      "protocol"    = "tcp"
      "cidr_blocks" = "0.0.0.0/0"
    },
    {
      "description" = "ALLOW HTTPs"
      "from_port"   = "443"
      "to_port"     = "443"
      "protocol"    = "tcp"
      "cidr_blocks" = "0.0.0.0/0"
    }
  ]

  asg_sg_ingress_rules = [
    {
      "description" = "ALLOW ALL PORTS INSIDE VPC CIDR"
      "from_port"   = "${var.any_port}"
      "to_port"     = "${var.to_port}"
      "protocol"    = "tcp"
      "cidr_blocks" = "${local.cidr}"
    }
  ]
}
