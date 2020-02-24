variable "evaluation_periods" {
  description = "The number of periods over which data is compared to the specified threshold"
  default = "1"
}

variable "metric_namespace" {
  description = "The destination namespace of the CloudWatch metric"
}

variable "period" {
  description = "The period in seconds over which the specified statistic is applied"
  default = "60"
}

variable "statistic" {
  description = "The statistic to apply to the alarm's associated metric. Either of the following is supported: SampleCount, Average, Sum, Minimum, Maximum"
  default = "Sum"
}

variable "alarm_description" {
  default = ""
}

variable "alarm_actions" {
}

variable "alarm_name" {
  default = ""
}

variable "comparison_operator" {
  default = ""
}

variable "threshold" {
  default = ""
}

variable "metric_name" {
  default = ""
}

variable "cluster_name" {
  default = ""
}

variable "service_name" {
  default = ""
}

variable "datapoints_to_alarm" {
  default = ""
}
