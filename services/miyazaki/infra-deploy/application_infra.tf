######data for remote state#####
data "terraform_remote_state" "cloud_sre_aws_state" {
  backend = "remote"
  config = {
    organization = "Cloud_SRE"
    token        = "TF_TOKEN_CLOUD_SRE"
    workspaces = {
      name = "cloud-sre-aws-${terraform.workspace}"
    }
  }
}

data "aws_ami" "aws_optimized_ecs" {
  most_recent = true

  filter {
    name   = "name"
    values = ["amzn-ami*amazon-ecs-optimized"]
  }

  filter {
    name   = "architecture"
    values = ["x86_64"]
  }

  owners = ["amazon"]
}
#####################################   ACM    ##################################

#only used for prod and validation done by CC_PROD
module "hooq_tv_aws_ssl_certs" {
  source = "../../../modules/terraform/aws/acm"
  domainname = local.hooq_domain_name
  subject_alternative_names = local.hooq_subject_alternative_names
  wait_for_validation = false
  validate_certificate = false
  tags = local.tags
}

######################################################  RESOURCES  #####################################################

module "ecs_cluster" {
  source = "../../../modules/terraform/aws/ecs/cluster"
  name   = local.name
  vpc_id = module.vpc.vpc_id
  tags   = local.tags
}

module "alb_targetgroups" {
  source        = "../../../modules/terraform/aws/loadbalancer/alb_targetgroup"
  vpc_id        = module.vpc.vpc_id
  name          = local.name
  tags          = local.tags
  target_groups = local.target_groups
}

module "alb" {
  source          = "../../../modules/terraform/aws/loadbalancer/alb"
  vpc_id          = module.vpc.vpc_id
  security_groups = module.alb_sg.sg_id
  name            = local.name
  lb_name         = local.name
  tags            = local.tags
  subnets         = "${module.external_subnets.subnet_ids}"
  is_internal     = "false"
  environment     = terraform.workspace
  service         = var.service_name
  create_alb      = "true"
  log_bucket_name = local.alb_s3_bucket_name
}

module "alb_listener_http" {
  source             = "../../../modules/terraform/aws/loadbalancer/alb_listeners/http"
  alb_arn            = module.alb.alb_arn
  http_tcp_listeners = var.http_tcp_listeners
}

module "alb_listener_https" {
  source                    = "../../../modules/terraform/aws/loadbalancer/alb_listeners/https"
  vpc_id                    = module.vpc.vpc_id
  name                      = local.name
  alb_arn                   = module.alb.alb_arn
  tg_arn                    = module.alb_targetgroups.target_group_arns
  tags                      = local.tags
  environment               = terraform.workspace
  service                   = var.service_name
  https_tcp_listeners       = var.https_tcp_listeners
  https_tcp_listeners_count = "${length(var.https_tcp_listeners)}"
  ssl_policy                = var.default_alb_ssl_policy
  certificate_arn           = local.alb_certificate_arn
}


module "alb_listener_certificate" {
  source          = "../../../modules/terraform/aws/loadbalancer/alb_listener_certificate"
  listener_arn    = module.alb_listener_https.alb_https_listener_arn[0]
  certificate_arn = data.terraform_remote_state.cloud_sre_aws_state.outputs.acm_cert_arn
  alb_additional_certificate_count = local.alb_additional_certificate_count

}
###
module "alb_http_listener_rule_host_header" {
  source                     = "../../../modules/terraform/aws/loadbalancer/alb_listener_rules/host_header"
  vpc_id                     = module.vpc.vpc_id
  name                       = local.name
  tg_arn                     = module.alb_targetgroups.target_group_arns
  http_listener_arn          = module.alb_listener_http.alb_http_listener_arn
  tags                       = local.tags
  environment                = terraform.workspace
  host_header_listener_count = length(local.host_header_listener_rule)
  host_header_listener_rules = local.host_header_listener_rule
  is_hostheader                  = false
}

###
module "alb_https_listener_rule_host_header" {
  source                     = "../../../modules/terraform/aws/loadbalancer/alb_listener_rules/host_header"
  vpc_id                     = module.vpc.vpc_id
  name                       = local.name
  tg_arn                     = module.alb_targetgroups.target_group_arns
  http_listener_arn          = module.alb_listener_https.alb_https_listener_arn
  tags                       = local.tags
  environment                = terraform.workspace
  host_header_listener_count = length(local.host_header_listener_rule)
  host_header_listener_rules = local.host_header_listener_rule
  is_hostheader                   = true
}

module "aws_monitoring_log_group" {
  source            = "../../../modules/terraform/aws/cloudwatch/log_group"
  log_groups        = local.log_name_map
  retention_in_days = var.loggroup_retention_in_days
  tags              = local.tags
}

module "launch_configuration" {
  source                          = "../../../modules/terraform/aws/autoscaling/launch_configuration"
  name                            = local.name
  instance_type                   = local.ec2_instance_type
  security_groups                 = module.autoscalinggroup_sg.sg_id
  ami_id                          = data.aws_ami.aws_optimized_ecs.id
  cluster_name                    = module.ecs_cluster.ecs_cluster_name
  root_volume_size                = local.ec2_root_volume_size
  iam_instance_profile            = module.ecs_ec2_role.instance_profile_name
  tags                            = local.tags
  service                         = var.service_name
  cloudwatch_prefix               = local.name
  private_registry_token          = "PRIVATE_REGISTRY_TOKEN"
  environment                     = terraform.workspace
  region                          = var.region
  volume_type                     = var.volume_type
}


module "autoscaling_group" {
  source             = "../../../modules/terraform/aws/autoscaling/autoscaling_group_alb"
  launch_config_name = module.launch_configuration.launch_configuration_name
  vpc                = "${module.internal_private_subnets.subnet_ids}"
  target_group_arn   = module.alb_targetgroups.target_group_arns
  max_size           = local.autoscaling_max_size
  min_size           = local.autoscaling_min_size
  desired_capacity   = local.autoscaling_desired_capacity
  name               = local.name
  owner              = var.owner
  environment        = terraform.workspace
  health_check_type  = var.health_check_type
  termination_policy = var.termination_policy
  health_check_grace_period = var.health_check_grace_period
  default_cooldown   = var.cooldown_period
}

module "asg_autoscaling_policy_scale_up" {
  name                     = "${module.autoscaling_group.aws_asg_name}-instances-scale-up"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaleup"
  asg_name_instances_scale = local.asg_name_instances_scale
  scaling_adjust           = var.autoscaling_scaling_adjust_scaleup
  cooldown                 = var.autoscaling_scaling_adjust_cooldown
  asg_name                 = module.autoscaling_group.aws_asg_name
  adjustment_type          = var.autoscaling_policy_adjustment_type
}

module "asg_autoscaling_policy_scale_down" {
  name                     = "${module.autoscaling_group.aws_asg_name}-instances-scale-down"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaledown"
  asg_name_instances_scale = local.name
  scaling_adjust           = var.autoscaling_scaling_adjust_scaledown
  cooldown                 = var.autoscaling_scaling_adjust_cooldown
  asg_name                 = module.autoscaling_group.aws_asg_name
  adjustment_type          = var.autoscaling_policy_adjustment_type
}

module "cloudwatch_alarm_ec2_scaleup" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = local.scaleup_cloudwatch_alarm_evaluation_periods
  period               = var.cloudwatch_alarm_period
  metric_namespace     = var.cloudwatch_alarm_metric_namespace
  statistic            = var.cloudwatch_alarm_cpu_statistic
  metric_name          = var.cpu_metric_name
  comparison_operator  = var.cpu_scaleup_operator
  threshold            = var.cpu_scaleup_threshold
  alarm_description    = "This alarm monitors ${module.ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling up"
  alarm_actions        = module.asg_autoscaling_policy_scale_up.scaleup_arn
  alarm_name           = "${module.ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Above-${var.cpu_scaleup_threshold}"
  autoscalinggroupname = module.autoscaling_group.aws_asg_name
  datapoints_to_alarm  = local.scaleup_cloudwatch_datapoints_to_alarm
}

module "cloudwatch_alarm_ec2_scaledown" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = local.scaledown_cloudwatch_alarm_evaluation_periods
  period               = var.cloudwatch_alarm_period
  metric_namespace     = var.cloudwatch_alarm_metric_namespace
  statistic            = var.cloudwatch_alarm_cpu_statistic
  metric_name          = var.cpu_metric_name
  comparison_operator  = var.cpu_scaledown_operator
  threshold            = var.cpu_scaledown_threshold
  alarm_description    = "This alarm monitors ${module.ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling down"
  alarm_actions        = module.asg_autoscaling_policy_scale_down.scaledown_arn
  alarm_name           = "${module.ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Below-${var.cpu_scaledown_threshold}"
  autoscalinggroupname = module.autoscaling_group.aws_asg_name
  datapoints_to_alarm  = local.scaledown_cloudwatch_datapoints_to_alarm
}

########################################################  OUTPUTS ######################################################


output "hooq_tv_acm_cert_arn" {
  value = module.hooq_tv_aws_ssl_certs.acm_certificate_arn
}

output "ecs_cluster_name" {
  value = module.ecs_cluster.ecs_cluster_name
}

output "alb_targetgroup_names" {
  value = module.alb_targetgroups.target_group_names
}

output "alb_targetgroups_arns" {
  value = module.alb_targetgroups.target_group_arns
}

output "alb_dnsname" {
  value = module.alb.alb_dnsname
}

output "alb_arn" {
  value = module.alb.alb_arn
}

output "alb_http_listeners" {
  value = module.alb_listener_http.alb_http_listener_arn
}

output "alb_https_listeners" {
  value = module.alb_listener_https.alb_https_listener_arn
}

output "alb_http_listener_rule_host_header" {
  value = module.alb_http_listener_rule_host_header.host_header_http_listener_rules
}

output "alb_https_listener_rule_host_header" {
  value = module.alb_https_listener_rule_host_header.host_header_http_listener_rules
}

output "asg_name" {
  value = module.autoscaling_group.aws_asg_name
}

output "cloudwatch_loggroups" {
  value = module.aws_monitoring_log_group.loggroup_names
}

output "cloudwatch_ec2_scaleup_cpuutilization_alarm" {
  value = "${module.cloudwatch_alarm_ec2_scaleup.cloudwatch_alarm_arn}"
}

output "cloudwatch_ec2_scaledown_cpuutilization_alarm" {
  value = "${module.cloudwatch_alarm_ec2_scaledown.cloudwatch_alarm_arn}"
}
