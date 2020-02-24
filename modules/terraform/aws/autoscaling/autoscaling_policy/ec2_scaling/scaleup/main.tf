resource "aws_autoscaling_policy" "scale_up" {
  name                   = "${var.name}"
  scaling_adjustment     = var.scaling_adjust
  cooldown               = var.cooldown
  adjustment_type        = "${var.adjustment_type}"
  autoscaling_group_name = var.asg_name
}
