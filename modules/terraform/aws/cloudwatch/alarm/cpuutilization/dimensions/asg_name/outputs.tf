output "cloudwatch_alarm_arn" {
  description = "The Amazon Resource Name (ARN) for cloudwatch alarm"
  value       = aws_cloudwatch_metric_alarm.main.arn
}