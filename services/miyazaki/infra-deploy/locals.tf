######################################################  Locals  #######################################################

locals {
  name                         = "${var.service_name}-${terraform.workspace}"
  cidr                         = var.env[terraform.workspace].cidr
  private_subnets              = split(",", var.env[terraform.workspace].cidrs_private)
  public_subnets               = split(",", var.env[terraform.workspace].cidrs_public)
  elasticache_subnets          = split(",", var.env[terraform.workspace].cidrs_elasticache)
  max_subnet_length            = max(length(local.private_subnets), )
  domainname                   = var.env[terraform.workspace].domain_name
  host_header_listener_rule    = concat(var.env[terraform.workspace].external_integration_name, ["${var.service_name}.${local.domainname}"])
  domainname_record_list       = var.env[terraform.workspace].external_integration_name
  hooq_domain_name = terraform.workspace == "prod" ? var.env[terraform.workspace].hooq_domain_name : []
  hooq_subject_alternative_names = terraform.workspace == "prod" ? var.env[terraform.workspace].hooq_subject_alternative_names: []
  vpc_peer_accepter_id         = var.env[terraform.workspace].vpc_peer_accepter_id
  autoscaling_max_size         = var.env[terraform.workspace].autoscaling_ec2_max_size
  autoscaling_min_size         = var.env[terraform.workspace].autoscaling_ec2_min_size
  autoscaling_desired_capacity = var.env[terraform.workspace].autoscaling_ec2_desired_capacity
  scaleup_cloudwatch_alarm_evaluation_periods = var.env[terraform.workspace].scaleup_cloudwatch_alarm_evaluation_periods
  scaleup_cloudwatch_datapoints_to_alarm = var.env[terraform.workspace].scaleup_cloudwatch_datapoints_to_alarm
  scaledown_cloudwatch_alarm_evaluation_periods = var.env[terraform.workspace].scaledown_cloudwatch_alarm_evaluation_periods
  scaledown_cloudwatch_datapoints_to_alarm = var.env[terraform.workspace].scaledown_cloudwatch_datapoints_to_alarm

  tags = {
    Name = local.name
    service_name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }

  target_groups = [
    {
      "name"             = "${local.name}-port80"
      "backend_protocol" = "HTTP"
      "backend_port"     = var.http_port
      "slow_start"       = 0
      "backend_path"     = "/2.0/health"
      "target_type"      = "instance"
    }
  ]


  #TODO We have to add ACM(AMAZON CERTIFICATE MANAGER) to add HTTPS listeners cert_arn ssl_policy etc...
  asg_name_instances_scale = "asg_policy${terraform.workspace}"
  elasticache_intance_type = var.env[terraform.workspace].elasticache_intance_type
  ec2_root_volume_size     = var.env[terraform.workspace].ec2_root_volume_size
  ec2_instance_type        = var.env[terraform.workspace].ec2_instance_type
  alb_s3_bucket_name       = terraform.workspace == "prod" ? var.env[terraform.workspace].alb_s3_bucket_name : ""
  alb_certificate_arn      = terraform.workspace == "prod" ?  module.hooq_tv_aws_ssl_certs.acm_certificate_arn : data.terraform_remote_state.cloud_sre_aws_state.outputs.acm_cert_arn
  alb_additional_certificate_count    = terraform.workspace == "prod" ? 1 : 0




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
      "from_port"   = var.http_port
      "to_port"     = var.http_port
      "protocol"    = var.tcp_protocol
      "cidr_blocks" = var.all_ips[0]
    },
    {
      "description" = "ALLOW HTTPS"
      "from_port"   = var.https_port
      "to_port"     = var.https_port
      "protocol"    = var.tcp_protocol
      "cidr_blocks" = var.all_ips[0]
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
