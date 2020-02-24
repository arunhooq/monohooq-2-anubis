### Create Private Repo
resource "aws_ecr_repository" "main" {
  name = var.ecrname
  lifecycle {
    create_before_destroy = true
  }

  tags = {
    Environment  = var.environment
    Versions     = var.versions
    Owner        = var.owner
    service-name = var.service-name
  }
}

