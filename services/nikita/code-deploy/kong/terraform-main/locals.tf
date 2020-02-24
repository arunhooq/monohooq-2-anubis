locals {
  // General
  name        = "${var.code_deploy_name}-${terraform.workspace}"
  account_id  = data.aws_caller_identity.current.account_id
  owner       = var.owner
  environment = terraform.workspace
  tags = {
    service_name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }
}

locals {
  // Network
  azs                = var.availability-zones
  nikita_vpc         = data.terraform_remote_state.nikita_infra_state.outputs.vpc_id
  public_subnet_ids  = data.terraform_remote_state.nikita_infra_state.outputs.public_subnet_ids
  private_subnet_ids = data.terraform_remote_state.nikita_infra_state.outputs.private_subnet_ids
}

locals {
  // Load balancer, Autoscaling group
  lb_name               = "${var.service_name}-${terraform.workspace}"
  security_groups_asg   = data.terraform_remote_state.nikita_infra_state.outputs.autoscalinggroup_sg_id
  security_groups_alb   = data.terraform_remote_state.nikita_infra_state.outputs.alb_sg_id
  alb_targetgroups_arns = data.terraform_remote_state.nikita_infra_state.outputs.kong_main_alb_targetgroups_arns
  alb_targetgroup_names = data.terraform_remote_state.nikita_infra_state.outputs.kong_main_alb_targetgroup_names

}

locals {
  // ECS
  cluster_name              = data.terraform_remote_state.nikita_infra_state.outputs.kong_main_ecs_cluster_name
  container_name            = var.code_deploy_name
  container_definition_file = "hooq-${local.container_name}.json"
  docker_image              = "kong"
  docker_tag                = "2.0"
  volume_name               = "${local.container_name}-log"
  instance_volume_path      = "/var/log/${local.container_name}"
  container_mount_path      = "/usr/local/${local.container_name}/logs"
  ecs_task_role             = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role   = data.terraform_remote_state.nikita_infra_state.outputs.ecs_task_execution_role_arn
  ecs_service_role          = data.terraform_remote_state.nikita_infra_state.outputs.ecs_service_role_name
  ecs_service_role_arn      = data.terraform_remote_state.nikita_infra_state.outputs.ecs_service_role_arn
  ecs_service_name          = module.nikita-kong-main.ecs_service

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

locals {
  // Logs
  awslogs_group_name = "${local.name}-logs"
}

locals {
  // Monitoring
  ecs_service_alarm_actions_scaledown = module.ecs_service_autoscaling_policy_scale_down.scale_down_arn
  ecs_service_alarm_actions_scaleup   = module.ecs_service_autoscaling_policy_scale_up.scale_up_arn
  load_balancer                       = data.terraform_remote_state.nikita_infra_state.outputs.kong_main_alb_dnsname
}
