resource "aws_appautoscaling_policy" "ecs_service_scale_up" {
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
      scaling_adjustment          = var.step_up_1_scaling_adjustment
      metric_interval_lower_bound = var.step_up_1_lower_bound
      metric_interval_upper_bound = var.step_up_1_upper_bound
    }

    # Step 2 (Spike)
    step_adjustment {
      scaling_adjustment          = var.step_up_2_scaling_adjustment
      metric_interval_lower_bound = var.step_up_2_lower_bound
      metric_interval_upper_bound = var.step_up_2_upper_bound
    }

    # Step 3 (Spike)
    step_adjustment {
      scaling_adjustment          = var.step_up_3_scaling_adjustment
      metric_interval_lower_bound = var.step_up_3_lower_bound
    }
  }
}
