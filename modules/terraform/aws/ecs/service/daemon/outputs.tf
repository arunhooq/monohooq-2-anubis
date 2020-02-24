output "ecs_container_definition" {
  value = aws_ecs_task_definition.main.container_definitions
}

output "ecs_task_definition_arn" {
  value = aws_ecs_task_definition.main.arn
}

output "ecs_service" {
  value = aws_ecs_service.main.name
}
