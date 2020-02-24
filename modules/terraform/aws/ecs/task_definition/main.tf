data "template_file" "container_definitions" {
  template = file("files/task-definitions/${var.container_definition_file}")
  vars = {
    cluster_name                   = var.cluster_name
    mount_path                     = var.container_mount_path
    mount_path_name                = var.volume_name
    container_port                 = var.container_port
    awslogs_group                  = var.awslogs_group
    docker_tag                     = var.docker_tag
    docker_image                   = var.docker_image
    region                         = var.region
    host_port                      = var.host_port
    container_name                 = var.container_name
    instance_volume_path           = var.instance_volume_path
    volume_name                    = var.volume_name
    environment                    = var.environment
    github_package_credentials_arn = var.github_package_credentials_arn
    aws_account_id                 = var.aws_account_id
    command                        = var.command
  }
}

resource "aws_ecs_task_definition" "main" {
  family                   = "${var.name}-td"
  container_definitions    = data.template_file.container_definitions.rendered
  task_role_arn            = var.ecs_task_role
  execution_role_arn       = var.ecs_task_execution_role
  requires_compatibilities = var.task_requires_compatibilities
  cpu                      = var.task_cpu
  memory                   = var.task_memory
  network_mode             = var.task_network_mode
  tags = merge(
    { "Name" = format("%s", "${var.name}-td") },
    var.tags,
  )

  lifecycle {
    create_before_destroy = true
  }
}
