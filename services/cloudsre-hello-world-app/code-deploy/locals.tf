locals {
  public_subnet_ids        = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.public_subnet_ids
  azs                      = var.availability_zones
  name                     = var.env[terraform.workspace].name
  service                  = var.service_name
  lb_name                  = "${var.service_name}-${terraform.workspace}"
  private_subnet_ids       = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.private_subnet_ids
  security_groups_asg      = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.autoscalinggroup_sg_id
  security_groups_alb      = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.alb_sg_id
  alb_targetgroups_arns    = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.alb_targetgroups_arns
  alb_targetgroup_names    = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.alb_targetgroup_names
  cluster_name             = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.ecs_cluster_name
  iam_instance_profile     = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.ecs_instance_profile_name
  ecs_task_role            = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role  = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.ecs_task_execution_role_arn
  ecs_service_role         = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.ecs_service_role_name
  ecs_service_role_arn     = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.ecs_service_role_arn
  awslogs_group_name       = "${var.service_name}-${terraform.workspace}-logs"
  asg_name-instances-scale = "asg_policy${terraform.workspace}"
  service_name             = "${var.service_name}${terraform.workspace}"
  private_registry_token   = data.terraform_remote_state.cloud_sre_state.outputs.private_registry_arn
  service-name             = "${var.service_name}${terraform.workspace}"
  tags = {
    service_name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }

  ecs_service_name = module.cloudsre-hello-world-app-ecs-service.ecs_service

  cloudsre-hello-world-app-vpc = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.vpc_id


  //monitoring
  asg_name                            = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.asg_name
  ecs_service_alarm_actions_scaledown = "${module.ecs_service_autoscaling_policy_scale_down.scale_down_arn}"
  ecs_service_alarm_actions_scaleup   = "${module.ecs_service_autoscaling_policy_scale_up.scale_up_arn}"
  ClusterName                         = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.ecs_cluster_name
  ServiceName                         = var.service_name
  LoadBalancer                        = data.terraform_remote_state.cloudsre-hello-world-app_infra_state.outputs.alb_dnsname
}
