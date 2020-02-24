locals {
  // General
  name        = var.env[terraform.workspace].name
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
  azs                = var.availability_zones
  miyazaki_vpc       = data.terraform_remote_state.miyazaki_infra_state.outputs.vpc_id
  public_subnet_ids  = data.terraform_remote_state.miyazaki_infra_state.outputs.public_subnet_ids
  private_subnet_ids = data.terraform_remote_state.miyazaki_infra_state.outputs.private_subnet_ids
}

locals {
  // Load balancer, Autoscaling group
  lb_name                             = "${var.service_name}-${terraform.workspace}"
  security_groups_asg                 = data.terraform_remote_state.miyazaki_infra_state.outputs.autoscalinggroup_sg_id
  security_groups_alb                 = data.terraform_remote_state.miyazaki_infra_state.outputs.alb_sg_id
  alb_targetgroup_names               = data.terraform_remote_state.miyazaki_infra_state.outputs.alb_targetgroup_names
  alb_targetgroups_arns               = data.terraform_remote_state.miyazaki_infra_state.outputs.alb_targetgroups_arns
  awslogs_group_name                  = "${var.service_name}-${terraform.workspace}-logs"
  asg_name-instances-scale            = "asg_policy${terraform.workspace}"
  asg_name                            = data.terraform_remote_state.miyazaki_infra_state.outputs.asg_name
  ecs_service_alarm_actions_scaledown = "${module.ecs_service_autoscaling_policy_scale_down.scale_down_arn}"
  ecs_service_alarm_actions_scaleup   = "${module.ecs_service_autoscaling_policy_scale_up.scale_up_arn}"
  max_capacity                        = var.env[terraform.workspace].max_capacity
  min_capacity                        = var.env[terraform.workspace].min_capacity
  desired_task_count                  = var.env[terraform.workspace].desired_task_count

}

locals {
  // ECS
  cluster_name              = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_cluster_name
  container_name            = var.service_name
  container_definition_file = "hooq-${local.container_name}.json"
  docker_image              = "${var.docker_registry_path}/${local.container_name}"
  docker_tag                = var.tag
  volume_name               = "${local.container_name}-log"
  instance_volume_path      = "/var/log/${local.container_name}"
  container_mount_path      = "/usr/local/${local.container_name}/logs"
  private_registry_token    = data.terraform_remote_state.cloud_sre_state.outputs.private_registry_arn
  ecs_task_role             = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role   = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_task_execution_role_arn
  ecs_service_role          = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_service_role_name
  ecs_service_role_arn      = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_service_role_arn
  iam_instance_profile      = data.terraform_remote_state.miyazaki_infra_state.outputs.ecs_instance_profile_name
  ecs_service_name          = module.miyazaki-ecs-service.ecs_service
}

locals {
  //ECS Taks autoscaling policy step scaling --> ScaleUP
  step_up_1_scaling_adjustment = var.env[terraform.workspace].step_up_1_scaling_adjustment
  step_up_1_lower_bound        = var.env[terraform.workspace].step_up_1_lower_bound
  step_up_1_upper_bound        = var.env[terraform.workspace].step_up_1_upper_bound
  step_up_2_scaling_adjustment = var.env[terraform.workspace].step_up_2_scaling_adjustment
  step_up_2_lower_bound        = var.env[terraform.workspace].step_up_2_lower_bound
  step_up_2_upper_bound        = var.env[terraform.workspace].step_up_2_upper_bound
  step_up_3_scaling_adjustment = var.env[terraform.workspace].step_up_3_scaling_adjustment
  step_up_3_lower_bound        = var.env[terraform.workspace].step_up_3_lower_bound

  //ECS Taks autoscaling policy step scaling --> ScaleDown
  step_down_1_scaling_adjustment = var.env[terraform.workspace].step_down_1_scaling_adjustment
  step_down_1_lower_bound        = var.env[terraform.workspace].step_down_1_lower_bound
  step_down_1_upper_bound        = var.env[terraform.workspace].step_down_1_upper_bound
  step_down_2_scaling_adjustment = var.env[terraform.workspace].step_down_2_scaling_adjustment
  step_down_2_lower_bound        = var.env[terraform.workspace].step_down_2_lower_bound
  step_down_2_upper_bound        = var.env[terraform.workspace].step_down_2_upper_bound
  step_down_3_scaling_adjustment = var.env[terraform.workspace].step_down_3_scaling_adjustment
  step_down_3_upper_bound        = var.env[terraform.workspace].step_down_3_upper_bound

  // Deployment Health
  deployment_minimum_healthy_percent = var.env[terraform.workspace].deployment_minimum_healthy_percent
  deployment_maximum_percent         = var.env[terraform.workspace].deployment_maximum_percent
}
