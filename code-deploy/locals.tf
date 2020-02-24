locals {
  public-subnet-ids        = data.terraform_remote_state.anubis_infra_state.outputs.public-subnet-ids
  azs                      = var.availability-zones
  name                     = var.env[terraform.workspace].name
  api_service              = "${var.service-name}-api"
  service                  = var.service-name
  android_service          = "${var.service-name}-android"
  web_service              = "${var.service-name}-web"
  ios_service              = "${var.service-name}-ios"
  partnerapi_service       = "${var.service-name}-partnerapi"
  lb_name                  = "${var.service-name}-${terraform.workspace}"
  private-subnet-ids       = data.terraform_remote_state.anubis_infra_state.outputs.private-subnet-ids
  security_groups_asg      = data.terraform_remote_state.anubis_infra_state.outputs.autoscalinggroup_sg_id
  cluster_name             = data.terraform_remote_state.anubis_infra_state.outputs.ecs-cluster-name
  iam_instance_profile     = data.terraform_remote_state.anubis_infra_state.outputs.ecs_instance_profile_name
  awslogs_group_name       = "${var.service-name}-${terraform.workspace}-logs"
  asg_name-instances-scale = "asg_policy${terraform.workspace}"
  service-name             = "${var.service-name}${terraform.workspace}"
  private_registry_token   = data.terraform_remote_state.cloud_sre_state.outputs.private_registry_arn


  ecs_task_role            = data.terraform_remote_state.anubis_infra_state.outputs.ecs_task_role_arn
  ecs_task_execution_role  = data.terraform_remote_state.anubis_infra_state.outputs.ecs_task_execution_role_arn
  ecs_service_role         = data.terraform_remote_state.anubis_infra_state.outputs.ecs_service_role_name
  ecs_service_role_arn     = data.terraform_remote_state.anubis_infra_state.outputs.ecs_service_role_arn

  owner                    = var.owner
  Environment              = terraform.workspace
  tags = {
    service-name = var.service-name
    owner        = var.owner
    Environment  = terraform.workspace
    Version      = var.tag
  }
  task_role_policies = [
    "arn:aws:iam::aws:policy/AmazonElastiCacheFullAccess",
    "arn:aws:iam::aws:policy/AmazonRDSFullAccess",

  ]
  task_execution_role_policies = [
    "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy",
    "arn:aws:iam::aws:policy/AmazonSSMReadOnlyAccess",
    "arn:aws:iam::aws:policy/CloudWatchLogsFullAccess",
    "arn:aws:iam::aws:policy/SecretsManagerReadWrite",
    "arn:aws:iam::aws:policy/AmazonSSMManagedInstanceCore",
  ]

  anubis-vpc = data.terraform_remote_state.anubis_infra_state.outputs.vpc-id

  //monitoring

  asg_name                            = data.terraform_remote_state.anubis_infra_state.outputs.asg_name
  ClusterName                         = data.terraform_remote_state.anubis_infra_state.outputs.ecs-cluster-name
  ServiceName                         = var.service-name

}