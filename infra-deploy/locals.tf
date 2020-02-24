######################################################  Locals  #############################################################

locals {
  name                  = "${var.service-name}-${terraform.workspace}"
  cidr                  = var.env[terraform.workspace].cidr
  private_subnets       = "${split(",", var.env[terraform.workspace].cidrs_private)}"
  public_subnets        = "${split(",", var.env[terraform.workspace].cidrs_public)}"
  single_az_nat_gateway = var.env[terraform.workspace].single_az_nat_gateway
  multi_az_nat_gateway  = var.env[terraform.workspace].multi_az_nat_gateway
  max_subnet_length     = max(length(local.private_subnets), )
  nat_gateway_count     = local.single_az_nat_gateway ? 1 : local.multi_az_nat_gateway ? length(var.azs) : local.max_subnet_length
  domainname            = var.env[terraform.workspace].domain_name
  host_header_listener_rule =  "${concat(var.env[terraform.workspace].external_integration_name,["${var.service-name}.${local.domainname}"])}"
  domainname_record_list = var.env[terraform.workspace].external_integration_name
  asg_name_instances_scale = "asg_policy${terraform.workspace}"
  vpc_peer_accepter_id      = var.env[terraform.workspace].vpc_peer_accepter_id

  tags = {
    service_name = var.service-name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }

  any_port     = 0
  any_protocol = "-1"
  tcp_protocol = "tcp"
  all_ips      = ["0.0.0.0/0"]
  http_port    = 80
  https_port   = 443

  target_groups = [
    {
      "name"             = "${local.name}-port80"
      "backend_protocol" = "HTTP"
      "backend_port"     = 80
      "slow_start"       = 0
      "backend_path"     = "/2.0/health"
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
  asg_name-instances-scale   = "asg_policy${terraform.workspace}"
  ec2_root_volume_size       = var.env[terraform.workspace].ec2_root_volume_size
  ec2_instance_type          = var.env[terraform.workspace].ec2_instance_type
  alb_s3_bucket_name         = "${var.env[terraform.workspace] == "prod" ? var.env[terraform.workspace].alb_s3_bucket_name : ""}"
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
}