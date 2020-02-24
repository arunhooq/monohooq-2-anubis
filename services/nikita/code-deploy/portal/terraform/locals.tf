locals {
  public_subnet_ids        = data.terraform_remote_state.nikita_infra_state.outputs.public_subnet_ids
  azs                      = var.availability-zones
  service                  = "${var.service-name}-portal"
  name                     = "${local.service}-${terraform.workspace}"
  lb_name                  = "${var.service-name}-${terraform.workspace}"
  private_subnet_ids       = data.terraform_remote_state.nikita_infra_state.outputs.private_subnet_ids
  security_groups_asg      = data.terraform_remote_state.nikita_infra_state.outputs.autoscalinggroup_sg_id
  security_groups_alb      = data.terraform_remote_state.nikita_infra_state.outputs.alb_sg_id
  alb_targetgroups_arns    = data.terraform_remote_state.nikita_infra_state.outputs.portal_alb_targetgroups_arns
  alb_targetgroup_names    = data.terraform_remote_state.nikita_infra_state.outputs.portal_alb_targetgroup_names
  awslogs_group_name       = "${var.service-name}-portal-${terraform.workspace}-logs"
  asg_name-instances-scale = "asg_policy${terraform.workspace}"
  service_name             = "${var.service-name}-portal-${terraform.workspace}"
  private_registry_token   = data.terraform_remote_state.cloud_sre_state.outputs.private_registry_arn
  owner                    = var.owner
  Environment              = terraform.workspace
  tags = {
    service-name = var.service-name
    owner        = var.owner
    Environment  = terraform.workspace
    Version      = var.tag
  }
  task_role_policies = [
    "arn:aws:iam::aws:policy/AmazonRDSFullAccess",
  ]
  task_execution_role_policies = [
    "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy",
    "arn:aws:iam::aws:policy/AmazonSSMReadOnlyAccess",
    "arn:aws:iam::aws:policy/CloudWatchLogsFullAccess",
    "arn:aws:iam::aws:policy/SecretsManagerReadWrite",
    "arn:aws:iam::aws:policy/AmazonSSMManagedInstanceCore",
  ]

  nikita_vpc = data.terraform_remote_state.nikita_infra_state.outputs.vpc_id

  //monitoring
  asg_name                            = data.terraform_remote_state.nikita_infra_state.outputs.portal_asg_name
  ecs_service_alarm_actions_scaledown = module.ecs_service_autoscaling_policy_scale_down.scale_down_arn
  ecs_service_alarm_actions_scaleup   = module.ecs_service_autoscaling_policy_scale_up.scale_up_arn
  load_balancer                       = data.terraform_remote_state.nikita_infra_state.outputs.portal_alb_dnsname
}

locals {
  // ECS
  cluster_name     = data.terraform_remote_state.nikita_infra_state.outputs.portal_ecs_cluster_name
  ecs_service_name = module.nikita-portal.ecs_service

  ecs_task_role           = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_execution_role_arn
  ecs_service_role        = data.terraform_remote_state.nikita_infra_state.outputs.ecs_service_role_name
  ecs_service_role_arn    = data.terraform_remote_state.nikita_infra_state.outputs.ecs_service_role_arn

  // ECS Taks autoscaling policy step scaling --> ScaleDown
  step_down_1_scaling_adjustment = var.env[terraform.workspace].step_down_1_scaling_adjustment
  step_down_1_lower_bound        = var.env[terraform.workspace].step_down_1_lower_bound
  step_down_1_upper_bound        = var.env[terraform.workspace].step_down_1_upper_bound
  step_down_2_scaling_adjustment = var.env[terraform.workspace].step_down_2_scaling_adjustment
  step_down_2_lower_bound        = var.env[terraform.workspace].step_down_2_lower_bound
  step_down_2_upper_bound        = var.env[terraform.workspace].step_down_2_upper_bound
  step_down_3_scaling_adjustment = var.env[terraform.workspace].step_down_3_scaling_adjustment
  step_down_3_upper_bound        = var.env[terraform.workspace].step_down_3_upper_bound

  // ECS Taks autoscaling policy step scaling --> ScaleUP
  step_up_1_scaling_adjustment = var.env[terraform.workspace].step_up_1_scaling_adjustment
  step_up_1_lower_bound        = var.env[terraform.workspace].step_up_1_lower_bound
  step_up_1_upper_bound        = var.env[terraform.workspace].step_up_1_upper_bound
  step_up_2_scaling_adjustment = var.env[terraform.workspace].step_up_2_scaling_adjustment
  step_up_2_lower_bound        = var.env[terraform.workspace].step_up_2_lower_bound
  step_up_2_upper_bound        = var.env[terraform.workspace].step_up_2_upper_bound
  step_up_3_scaling_adjustment = var.env[terraform.workspace].step_up_3_scaling_adjustment
  step_up_3_lower_bound        = var.env[terraform.workspace].step_up_3_lower_bound

  // Deployment Health
  deployment_minimum_healthy_percent = var.env[terraform.workspace].deployment_minimum_healthy_percent
  deployment_maximum_percent         = var.env[terraform.workspace].deployment_maximum_percent
}
