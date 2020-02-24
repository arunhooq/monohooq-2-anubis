
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

######################################################  RESOURCES  #########################################################

module "ecs-cluster" {
  source = "../../modules/terraform/aws/ecs/cluster"
  name   = local.name
  vpc_id = module.vpc.vpc_id
  tags   = local.tags
}

module "alb_targetgroups" {
  source        = "../../modules/terraform/aws/loadbalancer/alb_targetgroup"
  vpc_id        = module.vpc.vpc_id
  name          = local.name
  tags          = local.tags
  target_groups = local.target_groups
}



module "aws_monitoring_log_group" {
  source            = "../../modules/terraform/aws/cloudwatch/log_group"
  log_groups        = local.log_name_map
  retention_in_days = 180
  tags              = local.tags
}

module "launch_configuration" {
  source                          = "../../modules/terraform/aws/autoscaling/launch_configuration"
  name                            = local.name
  instance_type                   = local.ec2_instance_type
  security_groups                 = module.autoscalinggroup_sg.sg_id
  ami_id                          = data.aws_ami.aws_optimized_ecs.id
  cluster_name                    = module.ecs-cluster.ecs_cluster_name
  root_volume_size                = local.ec2_root_volume_size
  iam_instance_profile            = module.ecs_ec2_role.instance_profile_name
  tags                            = local.tags
  service                         = var.service-name
  cloudwatch_prefix               = local.name
  private_registry_token          = "PRIVATE_REGISTRY_TOKEN"
  environment                     = terraform.workspace
  region                          = "ap-southeast-1"
  volume_type                     = "gp2"
  cloudwatch_custommetric_version = "1.2.2"
}


module "autoscaling_group" {
  source             = "../../modules/terraform/aws/autoscaling/autoscaling_group_alb"
  launch_config_name = module.launch_configuration.launch_configuration_name
  vpc                = "${module.internal_private_subnets.subnet_ids}"
  target_group_arn   = module.alb_targetgroups.target_group_arns
  max_size           = MAX_SIZE
  min_size           = MIN_SIZE
  desired_capacity   = DESIRED_CAPACITY
  name               = local.name
  owner              = var.owner
  environment        = terraform.workspace
  health_check_type  = "ELB"
  termination_policy = "OldestLaunchConfiguration"
  health_check_grace_period = "120"
  default_cooldown   = "60"
}

module "asg_autoscaling_policy_scale_up" {
  name                     = "${module.autoscaling_group.aws_asg_name}-instances-scale-up"
  source                   = "../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaleup"
  asg_name_instances_scale = local.asg_name_instances_scale
  scaling_adjust           = 1
  cooldown                 = 300
  asg_name                 = module.autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "asg_autoscaling_policy_scale_down" {
  name                     = "${module.autoscaling_group.aws_asg_name}-instances-scale-down"
  source                   = "../../modules/terraform/aws/autoscaling/autoscaling_policy/ec2_scaling/scaledown"
  asg_name_instances_scale = local.name
  scaling_adjust           = -1
  cooldown                 = 300
  asg_name                 = module.autoscaling_group.aws_asg_name
  adjustment_type          = "ChangeInCapacity"
}

module "cloudwatch_alarm_ec2_scaleup" {
  source              = "../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods  = "1"
  period              = "60"
  metric_namespace    = "AWS/EC2"
  statistic           = "Average"
  metric_name         = "CPUUtilization"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  threshold           = "65"
  alarm_description   = "This alarm monitors ${module.ecs-cluster.ecs_cluster_name} instances CPU utilization for scaling up"
  alarm_actions       = module.asg_autoscaling_policy_scale_up.scaleup_arn
  alarm_name          = "${module.ecs-cluster.ecs_cluster_name}-instances-CPU-Utilization-Above-65"
  autoscalinggroupname = module.autoscaling_group.aws_asg_name
  datapoints_to_alarm = "1"
}

module "cloudwatch_alarm_ec2_scaledown" {
  source              = "../../modules/terraform/aws/cloudwatch/alarm/cpuutilization/dimensions/asg_name"
  evaluation_periods  = "1"
  period              = "60"
  metric_namespace    = "AWS/EC2"
  statistic           = "Average"
  metric_name         = "CPUUtilization"
  comparison_operator = "LessThanOrEqualToThreshold"
  threshold           = "30"
  alarm_description   = "This alarm monitors ${module.ecs-cluster.ecs_cluster_name} instances CPU utilization for scaling down"
  alarm_actions       = module.asg_autoscaling_policy_scale_down.scaledown_arn
  alarm_name          = "${module.ecs-cluster.ecs_cluster_name}-instances-CPU-Utilization-Below-30"
  autoscalinggroupname = module.autoscaling_group.aws_asg_name
  datapoints_to_alarm = "1"
}

#######################################################  OUTPUTS ######################################################

output "ecs-cluster-name" {
  value = module.ecs-cluster.ecs_cluster_name
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

output "launch_configuration_name" {
  value = "${module.launch_configuration.launch_configuration_name}"
}

output "vpc" {
  value = "${module.autoscaling_group.vpc}"
}