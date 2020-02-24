locals {
  name     = "${var.service_name}-${terraform.workspace}"
  vpc_name = "${local.name}-vpc"

  domainname                = var.env[terraform.workspace].domain_name
  cidr                      = var.env[terraform.workspace].cidr
  private_subnets           = split(",", var.env[terraform.workspace].cidrs_private)
  public_subnets            = split(",", var.env[terraform.workspace].cidrs_public)
  elasticache_subnets       = split(",", var.env[terraform.workspace].cidrs_elasticache)
  max_private_subnet_length = max(length(local.private_subnets), )
  max_public_subnet_length  = max(length(local.public_subnets), )
  private_subnet_name       = "${local.name}-subnet-private"
  public_subnet_name        = "${local.name}-subnet-public"

  vpc_peer_accepter_id                          = var.env[terraform.workspace].vpc_peer_accepter_id
  autoscaling_max_size                          = var.env[terraform.workspace].autoscaling_ec2_max_size
  autoscaling_min_size                          = var.env[terraform.workspace].autoscaling_ec2_min_size
  autoscaling_desired_capacity                  = var.env[terraform.workspace].autoscaling_ec2_desired_capacity
  scaleup_cloudwatch_alarm_evaluation_periods   = var.env[terraform.workspace].scaleup_cloudwatch_alarm_evaluation_periods
  scaleup_cloudwatch_datapoints_to_alarm        = var.env[terraform.workspace].scaleup_cloudwatch_datapoints_to_alarm
  scaledown_cloudwatch_alarm_evaluation_periods = var.env[terraform.workspace].scaledown_cloudwatch_alarm_evaluation_periods
  scaledown_cloudwatch_datapoints_to_alarm      = var.env[terraform.workspace].scaledown_cloudwatch_datapoints_to_alarm

  tags = {
    service_name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }

  target_groups = [
    {
      name             = local.name
      backend_protocol = "HTTP"
      backend_port     = 6201
      slow_start       = 0
      backend_path     = "/"
      target_type      = "instance"
    }
  ]

  elasticache_instance_type = var.env[terraform.workspace].elasticache_instance_type

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
    audit      = "${local.name}/var/log/ecs/audit.log"
    messages   = "${local.name}/var/log/messages"
  }

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

  asg_sg_ingress_rules_alb = [
    {
      "description" = "Allow dynamic allocated ports from alb to containers"
      "from_port"   = var.from_port
      "to_port"     = var.to_port
      "protocol"    = var.tcp_protocol
      "cidr_blocks" = "${local.cidr}"
    }
  ]

  asg_sg_ingress_rules_cidr = [
    {
      "description" = "Allow all ports for communication within containers within VPC"
      "from_port"   = var.any_port
      "to_port"     = var.to_port
      "protocol"    = var.tcp_protocol
      "cidr_blocks" = "${local.cidr}"
    }
  ]
  
  redis_sg_ingress_rules = [
    {
      "description" = "ALLOW ElasticCache"
      "from_port"   = var.elasticache_port
      "to_port"     = var.elasticache_port
      "protocol"    = var.tcp_protocol
    }
  ]
}
