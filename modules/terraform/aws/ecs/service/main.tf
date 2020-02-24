data "template_file" "container_definitions" {
  template = "${file("files/task-definitions/${var.container_definition_file}")}"
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
  }
}

resource "aws_ecs_task_definition" "main" {
  family                = "${var.name}-td"
  container_definitions = data.template_file.container_definitions.rendered
  task_role_arn         = var.ecs_task_role
  execution_role_arn    = var.ecs_task_execution_role
  lifecycle {
    create_before_destroy = true
  }
}

### ECS Service
resource "aws_ecs_service" "main" {
  deployment_minimum_healthy_percent = var.deployment_minimum_healthy_percent
  deployment_maximum_percent         = var.deployment_maximum_percent
  iam_role                           = var.ecs_service_role
  name                               = "${var.name}-service"
  desired_count                      = var.desired_count
  cluster                            = var.cluster_name

  load_balancer {
    target_group_arn = element(concat(var.target_group_arn, [""]), 0)
    container_name   = var.container_name
    container_port   = var.container_port
  }

  # Track the latest ACTIVE revision
  task_definition = "${aws_ecs_task_definition.main.family}:${max("${aws_ecs_task_definition.main.revision}")}"

  # Allow external changes without Terraform plan difference
  lifecycle {
    ignore_changes = ["desired_count"]
  }
}