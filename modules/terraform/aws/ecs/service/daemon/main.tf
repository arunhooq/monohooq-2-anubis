data "template_file" "container_definitions" {
  template = "${file("files/task-definitions/${var.container_definition_file}")}"
  vars = {
    cluster_name                   = var.cluster_name
    awslogs_group                  = var.awslogs_group
    awslogs_stream                 = var.awslogs_stream
    docker_tag                     = var.docker_tag
    docker_image                   = var.docker_image
    region                         = var.region
    container_name                 = var.name
    github_package_credentials_arn = var.github_package_credentials_arn
    environment                    = var.environment
    aws_account_id                 = var.aws_account_id

  }
}

resource "aws_ecs_task_definition" "main" {
  family                = "${var.name}-td"
  container_definitions = data.template_file.container_definitions.rendered
  task_role_arn         = var.ecs_task_role
  execution_role_arn    = var.ecs_task_execution_role
  network_mode = "host"
  volume {
    name = "root"
    host_path = "/"
    }
  volume {
    name = "proc"
    host_path = "/proc"
  }
  volume {
    name = "cgroup"
    host_path = "/sys/fs/cgroup"
  }
  volume {
    name = "docker_sock"
    host_path = "/var/run/docker.sock"
  }
  lifecycle {
    create_before_destroy = true
  }
}

### ECS Service
resource "aws_ecs_service" "main" {
  name                               = "${var.name}-service"
  cluster                            = var.cluster_name
  scheduling_strategy                = var.scheduling_strategy
  # Track the latest ACTIVE revision
  task_definition = "${aws_ecs_task_definition.main.family}:${max("${aws_ecs_task_definition.main.revision}")}"
  }
