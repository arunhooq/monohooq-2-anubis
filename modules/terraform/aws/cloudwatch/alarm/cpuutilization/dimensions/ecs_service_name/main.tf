#######################################
# CLoudwatch Alarm for CPUUtilization
#######################################
resource "aws_cloudwatch_metric_alarm" "main" {
  alarm_name            = var.alarm_name
  alarm_description     = var.alarm_description
  comparison_operator   = var.comparison_operator
  evaluation_periods    = var.evaluation_periods
  metric_name           = var.metric_name
  namespace             = var.metric_namespace
  period                = var.period
  threshold             = var.threshold
  statistic             = var.statistic
  alarm_actions         = [var.alarm_actions]
  datapoints_to_alarm = var.datapoints_to_alarm
  tags = {
      "Name" = format("%s", var.alarm_name)
    }

  dimensions  = {
    ClusterName = var.cluster_name
    ServiceName = var.service_name
   }
}
