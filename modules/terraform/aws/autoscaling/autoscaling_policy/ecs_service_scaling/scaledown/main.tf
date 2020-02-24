resource "aws_appautoscaling_policy" "ecs_service_scale_down" {
  name               = var.name
  resource_id        = "service/${var.ecs_cluster_name}/${var.ecs_service_name}"
  scalable_dimension = var.scalable_dimension
  service_namespace  = var.service_namespace

  step_scaling_policy_configuration {
    cooldown                = var.cooldown
    metric_aggregation_type = var.metric_aggregation_type
    adjustment_type         = var.adjustment_type

    # Step 1 (Normal)
    step_adjustment {
      scaling_adjustment          = var.step_down_1_scaling_adjustment
      metric_interval_lower_bound = var.step_down_1_lower_bound
      metric_interval_upper_bound = var.step_down_1_upper_bound
    }

    # Step 2 (Spike)
    step_adjustment {
      scaling_adjustment          = var.step_down_2_scaling_adjustment
      metric_interval_lower_bound = var.step_down_2_lower_bound
      metric_interval_upper_bound = var.step_down_2_upper_bound
    }

    # Step 2 (Spike)
    step_adjustment {
      scaling_adjustment          = var.step_down_3_scaling_adjustment
      metric_interval_upper_bound = var.step_down_3_upper_bound
    }
  }
}
