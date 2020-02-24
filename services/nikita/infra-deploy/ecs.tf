######data for remote state####
data "terraform_remote_state" "cloud_sre_aws_state" {
  backend = "remote"
  config = {
    organization = "Cloud_SRE"
    token        = "SED_TF_TOKEN_CLOUD_SRE"
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

######################################################  RESOURCES  #####################################################
module "api_ecs_cluster" {
  source = "../../../modules/terraform/aws/ecs/cluster"
  name   = local.api_name
  vpc_id = module.vpc.vpc_id
  tags   = local.tags
}

module "portal_ecs_cluster" {
  source = "../../../modules/terraform/aws/ecs/cluster"
  name   = local.portal_name
  vpc_id = module.vpc.vpc_id
  tags   = local.tags
}

module "kong_main_ecs_cluster" {
  source = "../../../modules/terraform/aws/ecs/cluster"
  name   = local.kong_main_name
  vpc_id = module.vpc.vpc_id
  tags   = local.tags
}

module "kong_admin_ecs_cluster" {
  source = "../../../modules/terraform/aws/ecs/cluster"
  name   = local.kong_admin_name
  vpc_id = module.vpc.vpc_id
  tags   = local.tags
}

module "script_runner_ecs_cluster" {
  source = "../../../modules/terraform/aws/ecs/cluster"
  name   = local.script_runner_name
  vpc_id = module.vpc.vpc_id
  tags   = local.tags
}

module "api_alb_targetgroups" {
  source        = "../../../modules/terraform/aws/loadbalancer/alb_targetgroup"
  vpc_id        = module.vpc.vpc_id
  name          = local.api_name
  tags          = local.tags
  target_groups = local.api_target_groups
}

module "portal_alb_targetgroups" {
  source        = "../../../modules/terraform/aws/loadbalancer/alb_targetgroup"
  vpc_id        = module.vpc.vpc_id
  name          = local.portal_name
  tags          = local.tags
  target_groups = local.portal_target_groups
}

module "kong_main_alb_targetgroups" {
  source        = "../../../modules/terraform/aws/loadbalancer/alb_targetgroup"
  vpc_id        = module.vpc.vpc_id
  name          = local.kong_main_name
  tags          = local.tags
  target_groups = local.kong_main_target_groups
}

module "kong_admin_alb_targetgroups" {
  source        = "../../../modules/terraform/aws/loadbalancer/alb_targetgroup"
  vpc_id        = module.vpc.vpc_id
  name          = local.kong_admin_name
  tags          = local.tags
  target_groups = local.kong_admin_target_groups
}

module "api_alb" {
  source          = "../../../modules/terraform/aws/loadbalancer/alb"
  vpc_id          = module.vpc.vpc_id
  security_groups = module.alb_sg.sg_id
  name            = local.api_name
  lb_name         = local.api_name
  tags            = local.tags
  subnets         = module.public_subnet.subnet_ids
  is_internal     = "false"
  environment     = terraform.workspace
  service         = var.service_name
  create_alb      = "true"
  log_bucket_name = local.alb_s3_bucket_name
}

module "portal_alb" {
  source          = "../../../modules/terraform/aws/loadbalancer/alb"
  vpc_id          = module.vpc.vpc_id
  security_groups = module.alb_sg.sg_id
  name            = local.portal_name
  lb_name         = local.portal_name
  tags            = local.tags
  subnets         = module.public_subnet.subnet_ids
  is_internal     = "false"
  environment     = terraform.workspace
  service         = var.service_name
  create_alb      = "true"
  log_bucket_name = local.alb_s3_bucket_name
}

module "kong_main_alb" {
  source          = "../../../modules/terraform/aws/loadbalancer/alb"
  vpc_id          = module.vpc.vpc_id
  security_groups = module.alb_sg.sg_id
  name            = local.kong_main_name
  lb_name         = local.kong_main_name
  tags            = local.tags
  subnets         = module.public_subnet.subnet_ids
  is_internal     = "false"
  environment     = terraform.workspace
  service         = var.service_name
  create_alb      = "true"
  log_bucket_name = local.alb_s3_bucket_name
}

module "kong_admin_alb" {
  source          = "../../../modules/terraform/aws/loadbalancer/alb"
  vpc_id          = module.vpc.vpc_id
  security_groups = module.alb_sg.sg_id
  name            = local.kong_admin_name
  lb_name         = local.kong_admin_name
  tags            = local.tags
  subnets         = module.public_subnet.subnet_ids
  is_internal     = "false"
  environment     = terraform.workspace
  service         = var.service_name
  create_alb      = "true"
  log_bucket_name = local.alb_s3_bucket_name
}

module "api_alb_listener_http" {
  source             = "../../../modules/terraform/aws/loadbalancer/alb_listeners/http"
  alb_arn            = module.api_alb.alb_arn
  http_tcp_listeners = local.http_tcp_listeners
  default_action     = "forward"
  default_tg_arn     = module.api_alb_targetgroups.target_group_arns
}

module "portal_alb_listener_http" {
  source             = "../../../modules/terraform/aws/loadbalancer/alb_listeners/http"
  alb_arn            = module.portal_alb.alb_arn
  http_tcp_listeners = local.http_tcp_listeners
}

module "kong_main_alb_listener_http" {
  source             = "../../../modules/terraform/aws/loadbalancer/alb_listeners/http"
  alb_arn            = module.kong_main_alb.alb_arn
  http_tcp_listeners = local.http_tcp_listeners
}

module "kong_admin_alb_listener_http" {
  source             = "../../../modules/terraform/aws/loadbalancer/alb_listeners/http"
  alb_arn            = module.kong_admin_alb.alb_arn
  http_tcp_listeners = local.http_tcp_listeners
}

module "api_alb_listener_https" {
  source                    = "../../../modules/terraform/aws/loadbalancer/alb_listeners/https"
  vpc_id                    = module.vpc.vpc_id
  name                      = local.api_name
  alb_arn                   = module.api_alb.alb_arn
  tg_arn                    = module.api_alb_targetgroups.target_group_arns
  tags                      = local.tags
  environment               = terraform.workspace
  service                   = var.service_name
  https_tcp_listeners       = local.https_tcp_listeners
  https_tcp_listeners_count = length(local.https_tcp_listeners)
  ssl_policy                = "ELBSecurityPolicy-2016-08"
  certificate_arn           = data.terraform_remote_state.cloud_sre_aws_state.outputs.acm_cert_arn
}

module "portal_alb_listener_https" {
  source                    = "../../../modules/terraform/aws/loadbalancer/alb_listeners/https"
  vpc_id                    = module.vpc.vpc_id
  name                      = local.portal_name
  alb_arn                   = module.portal_alb.alb_arn
  tg_arn                    = module.portal_alb_targetgroups.target_group_arns
  tags                      = local.tags
  environment               = terraform.workspace
  service                   = var.service_name
  https_tcp_listeners       = local.https_tcp_listeners
  https_tcp_listeners_count = length(local.https_tcp_listeners)
  ssl_policy                = "ELBSecurityPolicy-2016-08"
  certificate_arn           = data.terraform_remote_state.cloud_sre_aws_state.outputs.acm_cert_arn
}

module "kong_main_alb_listener_https" {
  source                    = "../../../modules/terraform/aws/loadbalancer/alb_listeners/https"
  vpc_id                    = module.vpc.vpc_id
  name                      = local.kong_main_name
  alb_arn                   = module.kong_main_alb.alb_arn
  tg_arn                    = module.kong_main_alb_targetgroups.target_group_arns
  tags                      = local.tags
  environment               = terraform.workspace
  service                   = var.service_name
  https_tcp_listeners       = local.https_tcp_listeners
  https_tcp_listeners_count = length(local.https_tcp_listeners)
  ssl_policy                = "ELBSecurityPolicy-2016-08"
  certificate_arn           = data.terraform_remote_state.cloud_sre_aws_state.outputs.acm_cert_arn
}

module "kong_admin_alb_listener_https" {
  source                    = "../../../modules/terraform/aws/loadbalancer/alb_listeners/https"
  vpc_id                    = module.vpc.vpc_id
  name                      = local.kong_admin_name
  alb_arn                   = module.kong_admin_alb.alb_arn
  tg_arn                    = module.kong_admin_alb_targetgroups.target_group_arns
  tags                      = local.tags
  environment               = terraform.workspace
  service                   = var.service_name
  https_tcp_listeners       = local.https_tcp_listeners
  https_tcp_listeners_count = length(local.https_tcp_listeners)
  ssl_policy                = "ELBSecurityPolicy-2016-08"
  certificate_arn           = data.terraform_remote_state.cloud_sre_aws_state.outputs.acm_cert_arn
}

module "api_aws_monitoring_log_group" {
  source            = "../../../modules/terraform/aws/cloudwatch/log_group"
  log_groups        = local.api_log_name_map
  retention_in_days = 180
  tags              = local.tags
}

module "portal_aws_monitoring_log_group" {
  source            = "../../../modules/terraform/aws/cloudwatch/log_group"
  log_groups        = local.portal_log_name_map
  retention_in_days = 180
  tags              = local.tags
}

module "kong_main_aws_monitoring_log_group" {
  source            = "../../../modules/terraform/aws/cloudwatch/log_group"
  log_groups        = local.kong_main_log_name_map
  retention_in_days = 180
  tags              = local.tags
}

module "kong_admin_aws_monitoring_log_group" {
  source            = "../../../modules/terraform/aws/cloudwatch/log_group"
  log_groups        = local.kong_admin_log_name_map
  retention_in_days = 180
  tags              = local.tags
}

module "script_runner_aws_monitoring_log_group" {
  source            = "../../../modules/terraform/aws/cloudwatch/log_group"
  log_groups        = local.script_runner_log_name_map
  retention_in_days = 180
  tags              = local.tags
}

module "api_launch_configuration" {
  source                 = "../../../modules/terraform/aws/autoscaling/launch_configuration"
  name                   = local.api_name
  instance_type          = local.ec2_instance_type
  security_groups        = module.autoscalinggroup_sg.sg_id
  ami_id                 = data.aws_ami.aws_optimized_ecs.id
  cluster_name           = module.api_ecs_cluster.ecs_cluster_name
  root_volume_size       = local.ec2_root_volume_size
  iam_instance_profile   = module.ecs_ec2_role.instance_profile_name
  tags                   = local.tags
  service                = var.service_name
  cloudwatch_prefix      = local.api_name
  private_registry_token = "SED_PRIVATE_REGISTRY_TOKEN"
  environment            = terraform.workspace
  region                 = "ap-southeast-1"
  volume_type            = "gp2"
}

module "portal_launch_configuration" {
  source                 = "../../../modules/terraform/aws/autoscaling/launch_configuration"
  name                   = local.portal_name
  instance_type          = local.ec2_instance_type
  security_groups        = module.autoscalinggroup_sg.sg_id
  ami_id                 = data.aws_ami.aws_optimized_ecs.id
  cluster_name           = module.portal_ecs_cluster.ecs_cluster_name
  root_volume_size       = local.ec2_root_volume_size
  iam_instance_profile   = module.ecs_ec2_role.instance_profile_name
  tags                   = local.tags
  service                = var.service_name
  cloudwatch_prefix      = local.portal_name
  private_registry_token = "SED_PRIVATE_REGISTRY_TOKEN"
  environment            = terraform.workspace
  region                 = "ap-southeast-1"
  volume_type            = "gp2"
}

module "kong_main_launch_configuration" {
  source                 = "../../../modules/terraform/aws/autoscaling/launch_configuration"
  name                   = local.kong_main_name
  instance_type          = local.ec2_instance_type
  security_groups        = module.autoscalinggroup_sg.sg_id
  ami_id                 = data.aws_ami.aws_optimized_ecs.id
  cluster_name           = module.kong_main_ecs_cluster.ecs_cluster_name
  root_volume_size       = local.ec2_root_volume_size
  iam_instance_profile   = module.ecs_ec2_role.instance_profile_name
  tags                   = local.tags
  service                = var.service_name
  cloudwatch_prefix      = local.kong_main_name
  private_registry_token = "SED_PRIVATE_REGISTRY_TOKEN"
  environment            = terraform.workspace
  region                 = "ap-southeast-1"
  volume_type            = "gp2"
}

module "kong_admin_launch_configuration" {
  source                 = "../../../modules/terraform/aws/autoscaling/launch_configuration"
  name                   = local.kong_admin_name
  instance_type          = local.ec2_instance_type
  security_groups        = module.autoscalinggroup_sg.sg_id
  ami_id                 = data.aws_ami.aws_optimized_ecs.id
  cluster_name           = module.kong_admin_ecs_cluster.ecs_cluster_name
  root_volume_size       = local.ec2_root_volume_size
  iam_instance_profile   = module.ecs_ec2_role.instance_profile_name
  tags                   = local.tags
  service                = var.service_name
  cloudwatch_prefix      = local.kong_admin_name
  private_registry_token = "SED_PRIVATE_REGISTRY_TOKEN"
  environment            = terraform.workspace
  region                 = "ap-southeast-1"
  volume_type            = "gp2"
}

module "api_autoscaling_group" {
  source                    = "../../../modules/terraform/aws/autoscaling/autoscaling_group_alb"
  launch_config_name        = module.api_launch_configuration.launch_configuration_name
  vpc                       = module.private_subnet.subnet_ids
  target_group_arn          = module.api_alb_targetgroups.target_group_arns
  max_size                  = 3
  min_size                  = 1
  desired_capacity          = 2
  name                      = local.api_name
  owner                     = var.owner
  environment               = terraform.workspace
  health_check_type         = "ELB"
  termination_policy        = "OldestLaunchConfiguration"
  health_check_grace_period = "120"
  default_cooldown          = "60"
}

module "portal_autoscaling_group" {
  source                    = "../../../modules/terraform/aws/autoscaling/autoscaling_group_alb"
  launch_config_name        = module.portal_launch_configuration.launch_configuration_name
  vpc                       = module.private_subnet.subnet_ids
  target_group_arn          = module.portal_alb_targetgroups.target_group_arns
  max_size                  = 3
  min_size                  = 1
  desired_capacity          = 2
  name                      = local.portal_name
  owner                     = var.owner
  environment               = terraform.workspace
  health_check_type         = "ELB"
  termination_policy        = "OldestLaunchConfiguration"
  health_check_grace_period = "120"
  default_cooldown          = "60"
}

module "kong_main_autoscaling_group" {
  source                    = "../../../modules/terraform/aws/autoscaling/autoscaling_group_alb"
  launch_config_name        = module.kong_main_launch_configuration.launch_configuration_name
  vpc                       = module.private_subnet.subnet_ids
  target_group_arn          = module.kong_main_alb_targetgroups.target_group_arns
  max_size                  = 3
  min_size                  = 1
  desired_capacity          = 2
  name                      = local.kong_main_name
  owner                     = var.owner
  environment               = terraform.workspace
  health_check_type         = "ELB"
  termination_policy        = "OldestLaunchConfiguration"
  health_check_grace_period = "120"
  default_cooldown          = "60"
}

module "kong_admin_autoscaling_group" {
  source                    = "../../../modules/terraform/aws/autoscaling/autoscaling_group_alb"
  launch_config_name        = module.kong_admin_launch_configuration.launch_configuration_name
  vpc                       = module.private_subnet.subnet_ids
  target_group_arn          = module.kong_admin_alb_targetgroups.target_group_arns
  max_size                  = 3
  min_size                  = 1
  desired_capacity          = 2
  name                      = local.kong_admin_name
  owner                     = var.owner
  environment               = terraform.workspace
  health_check_type         = "ELB"
  termination_policy        = "OldestLaunchConfiguration"
  health_check_grace_period = "120"
  default_cooldown          = "60"
}

module "api_asg_autoscaling_policy_scale_up" {
  name                     = "${module.api_autoscaling_group.aws_asg_name}-instances-scale-up"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaleup"
  asg_name_instances_scale = local.asg_name_instances_scale
  scaling_adjust           = 1
  cooldown                 = 300
  asg_name                 = module.api_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "portal_asg_autoscaling_policy_scale_up" {
  name                     = "${module.portal_autoscaling_group.aws_asg_name}-instances-scale-up"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaleup"
  asg_name_instances_scale = local.asg_name_instances_scale
  scaling_adjust           = 1
  cooldown                 = 300
  asg_name                 = module.portal_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "kong_main_asg_autoscaling_policy_scale_up" {
  name                     = "${module.kong_main_autoscaling_group.aws_asg_name}-instances-scale-up"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaleup"
  asg_name_instances_scale = local.asg_name_instances_scale
  scaling_adjust           = 1
  cooldown                 = 300
  asg_name                 = module.kong_main_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "kong_admin_asg_autoscaling_policy_scale_up" {
  name                     = "${module.kong_admin_autoscaling_group.aws_asg_name}-instances-scale-up"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaleup"
  asg_name_instances_scale = local.asg_name_instances_scale
  scaling_adjust           = 1
  cooldown                 = 300
  asg_name                 = module.kong_admin_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "api_asg_autoscaling_policy_scale_down" {
  name                     = "${module.api_autoscaling_group.aws_asg_name}-instances-scale-down"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaledown"
  asg_name_instances_scale = local.api_name
  scaling_adjust           = -1
  cooldown                 = 300
  asg_name                 = module.api_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "portal_asg_autoscaling_policy_scale_down" {
  name                     = "${module.portal_autoscaling_group.aws_asg_name}-instances-scale-down"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaledown"
  asg_name_instances_scale = local.portal_name
  scaling_adjust           = -1
  cooldown                 = 300
  asg_name                 = module.portal_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "kong_main_asg_autoscaling_policy_scale_down" {
  name                     = "${module.kong_main_autoscaling_group.aws_asg_name}-instances-scale-down"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaledown"
  asg_name_instances_scale = local.kong_main_name
  scaling_adjust           = -1
  cooldown                 = 300
  asg_name                 = module.kong_main_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "kong_admin_asg_autoscaling_policy_scale_down" {
  name                     = "${module.kong_admin_autoscaling_group.aws_asg_name}-instances-scale-down"
  source                   = "../../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaledown"
  asg_name_instances_scale = local.kong_admin_name
  scaling_adjust           = -1
  cooldown                 = 300
  asg_name                 = module.kong_admin_autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "api_cloudwatch_alarm_ec2_scaleup" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "GreaterThanOrEqualToThreshold"
  threshold            = "65"
  alarm_description    = "This alarm monitors ${module.api_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling up"
  alarm_actions        = module.api_asg_autoscaling_policy_scale_up.scaleup_arn
  alarm_name           = "${module.api_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Above-65"
  autoscalinggroupname = module.api_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

module "portal_cloudwatch_alarm_ec2_scaleup" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "GreaterThanOrEqualToThreshold"
  threshold            = "65"
  alarm_description    = "This alarm monitors ${module.portal_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling up"
  alarm_actions        = module.portal_asg_autoscaling_policy_scale_up.scaleup_arn
  alarm_name           = "${module.portal_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Above-65"
  autoscalinggroupname = module.portal_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

module "kong_main_cloudwatch_alarm_ec2_scaleup" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "GreaterThanOrEqualToThreshold"
  threshold            = "65"
  alarm_description    = "This alarm monitors ${module.kong_main_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling up"
  alarm_actions        = module.kong_main_asg_autoscaling_policy_scale_up.scaleup_arn
  alarm_name           = "${module.kong_main_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Above-65"
  autoscalinggroupname = module.kong_main_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

module "kong_admin_cloudwatch_alarm_ec2_scaleup" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "GreaterThanOrEqualToThreshold"
  threshold            = "65"
  alarm_description    = "This alarm monitors ${module.kong_admin_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling up"
  alarm_actions        = module.kong_admin_asg_autoscaling_policy_scale_up.scaleup_arn
  alarm_name           = "${module.kong_admin_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Above-65"
  autoscalinggroupname = module.kong_admin_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

module "api_cloudwatch_alarm_ec2_scaledown" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "LessThanOrEqualToThreshold"
  threshold            = "30"
  alarm_description    = "This alarm monitors ${module.api_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling down"
  alarm_actions        = module.api_asg_autoscaling_policy_scale_down.scaledown_arn
  alarm_name           = "${module.api_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Below-30"
  autoscalinggroupname = module.api_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

module "portal_cloudwatch_alarm_ec2_scaledown" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "LessThanOrEqualToThreshold"
  threshold            = "30"
  alarm_description    = "This alarm monitors ${module.portal_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling down"
  alarm_actions        = module.portal_asg_autoscaling_policy_scale_down.scaledown_arn
  alarm_name           = "${module.portal_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Below-30"
  autoscalinggroupname = module.portal_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

module "kong_main_cloudwatch_alarm_ec2_scaledown" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "LessThanOrEqualToThreshold"
  threshold            = "30"
  alarm_description    = "This alarm monitors ${module.kong_main_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling down"
  alarm_actions        = module.kong_main_asg_autoscaling_policy_scale_down.scaledown_arn
  alarm_name           = "${module.kong_main_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Below-30"
  autoscalinggroupname = module.kong_main_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

module "kong_admin_cloudwatch_alarm_ec2_scaledown" {
  source               = "../../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods   = "1"
  period               = "60"
  metric_namespace     = "AWS/EC2"
  statistic            = "Average"
  metric_name          = "CPUUtilization"
  comparison_operator  = "LessThanOrEqualToThreshold"
  threshold            = "30"
  alarm_description    = "This alarm monitors ${module.kong_admin_ecs_cluster.ecs_cluster_name} instances CPU utilization for scaling down"
  alarm_actions        = module.kong_admin_asg_autoscaling_policy_scale_down.scaledown_arn
  alarm_name           = "${module.kong_admin_ecs_cluster.ecs_cluster_name}-instances-CPU-Utilization-Below-30"
  autoscalinggroupname = module.kong_admin_autoscaling_group.aws_asg_name
  datapoints_to_alarm  = "1"
}

#######################################################  OUTPUTS #######################################################

output "api_ecs_cluster_name" {
  value = module.api_ecs_cluster.ecs_cluster_name
}

output "portal_ecs_cluster_name" {
  value = module.portal_ecs_cluster.ecs_cluster_name
}

output "kong_main_ecs_cluster_name" {
  value = module.kong_main_ecs_cluster.ecs_cluster_name
}

output "kong_admin_ecs_cluster_name" {
  value = module.kong_admin_ecs_cluster.ecs_cluster_name
}

output "script_runner_ecs_cluster_name" {
  value = module.script_runner_ecs_cluster.ecs_cluster_name
}

output "api_alb_targetgroup_names" {
  value = module.api_alb_targetgroups.target_group_names
}

output "portal_alb_targetgroup_names" {
  value = module.portal_alb_targetgroups.target_group_names
}

output "kong_main_alb_targetgroup_names" {
  value = module.kong_main_alb_targetgroups.target_group_names
}

output "kong_admin_alb_targetgroup_names" {
  value = module.kong_admin_alb_targetgroups.target_group_names
}

output "api_alb_targetgroups_arns" {
  value = module.api_alb_targetgroups.target_group_arns
}

output "portal_alb_targetgroups_arns" {
  value = module.portal_alb_targetgroups.target_group_arns
}

output "kong_main_alb_targetgroups_arns" {
  value = module.kong_main_alb_targetgroups.target_group_arns
}

output "kong_admin_alb_targetgroups_arns" {
  value = module.kong_admin_alb_targetgroups.target_group_arns
}

output "api_alb_dnsname" {
  value = module.api_alb.alb_dnsname
}

output "portal_alb_dnsname" {
  value = module.portal_alb.alb_dnsname
}

output "kong_main_alb_dnsname" {
  value = module.kong_main_alb.alb_dnsname
}

output "kong_admin_alb_dnsname" {
  value = module.kong_admin_alb.alb_dnsname
}

output "api_alb_arn" {
  value = module.api_alb.alb_arn
}

output "portal_alb_arn" {
  value = module.portal_alb.alb_arn
}

output "kong_main_alb_arn" {
  value = module.kong_main_alb.alb_arn
}

output "kong_admin_alb_arn" {
  value = module.kong_admin_alb.alb_arn
}

output "api_alb_http_listeners" {
  value = module.api_alb_listener_http.alb_http_listener_arn
}

output "portal_alb_http_listeners" {
  value = module.portal_alb_listener_http.alb_http_listener_arn
}

output "kong_main_alb_http_listeners" {
  value = module.kong_main_alb_listener_http.alb_http_listener_arn
}

output "kong_admin_alb_http_listeners" {
  value = module.kong_admin_alb_listener_http.alb_http_listener_arn
}

output "api_alb_https_listeners" {
  value = module.api_alb_listener_https.alb_https_listener_arn
}

output "portal_alb_https_listeners" {
  value = module.portal_alb_listener_https.alb_https_listener_arn
}

output "kong_main_alb_https_listeners" {
  value = module.kong_main_alb_listener_https.alb_https_listener_arn
}

output "kong_admin_alb_https_listeners" {
  value = module.kong_admin_alb_listener_https.alb_https_listener_arn
}

output "api_asg_name" {
  value = module.api_autoscaling_group.aws_asg_name
}

output "portal_asg_name" {
  value = module.portal_autoscaling_group.aws_asg_name
}

output "kong_main_asg_name" {
  value = module.kong_main_autoscaling_group.aws_asg_name
}

output "api_cloudwatch_loggroups" {
  value = module.api_aws_monitoring_log_group.loggroup_names
}

output "portal_cloudwatch_loggroups" {
  value = module.portal_aws_monitoring_log_group.loggroup_names
}

output "kong_main_cloudwatch_loggroups" {
  value = module.kong_main_aws_monitoring_log_group.loggroup_names
}

output "kong_admin_cloudwatch_loggroups" {
  value = module.kong_admin_aws_monitoring_log_group.loggroup_names
}

output "script_runner_cloudwatch_loggroups" {
  value = module.script_runner_aws_monitoring_log_group.loggroup_names
}

output "api_cloudwatch_ec2_scaleup_cpuutilization_alarm" {
  value = module.api_cloudwatch_alarm_ec2_scaleup.cloudwatch_alarm_arn
}

output "portal_cloudwatch_ec2_scaleup_cpuutilization_alarm" {
  value = module.portal_cloudwatch_alarm_ec2_scaleup.cloudwatch_alarm_arn
}

output "kong_main_cloudwatch_ec2_scaleup_cpuutilization_alarm" {
  value = module.kong_main_cloudwatch_alarm_ec2_scaleup.cloudwatch_alarm_arn
}

output "kong_admin_cloudwatch_ec2_scaleup_cpuutilization_alarm" {
  value = module.kong_admin_cloudwatch_alarm_ec2_scaleup.cloudwatch_alarm_arn
}

output "api_cloudwatch_ec2_scaledown_cpuutilization_alarm" {
  value = module.api_cloudwatch_alarm_ec2_scaledown.cloudwatch_alarm_arn
}

output "portal_cloudwatch_ec2_scaledown_cpuutilization_alarm" {
  value = module.portal_cloudwatch_alarm_ec2_scaledown.cloudwatch_alarm_arn
}

output "kong_main_cloudwatch_ec2_scaledown_cpuutilization_alarm" {
  value = module.kong_main_cloudwatch_alarm_ec2_scaledown.cloudwatch_alarm_arn
}

output "kong_admin_cloudwatch_ec2_scaledown_cpuutilization_alarm" {
  value = module.kong_admin_cloudwatch_alarm_ec2_scaledown.cloudwatch_alarm_arn
}
