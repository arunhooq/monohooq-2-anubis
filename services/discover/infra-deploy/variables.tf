variable "env" {
  default = {

    play = {
      cidr                       = "192.168.4.0/24"
      cidrs_public               = "192.168.4.0/28,192.168.4.16/28,192.168.4.32/28"
      cidrs_private              = "192.168.4.48/28,192.168.4.64/28,192.168.4.80/28"
      cidrs_elasticache          = "192.168.4.96/28,192.168.4.112/28,192.168.4.128/28"
      domain_name                = "play-hooq.tv"
      elasticache_instance_type  = "cache.t2.small"

      //ecs
      ec2_root_volume_size = "20"
      ec2_instance_type    = "t3.medium"
      vpc_peer_accepter_id = "vpc-c9eb86ae"

      domain_name         = "play-hooq.tv"

      autoscaling_ec2_max_size         = "3"
      autoscaling_ec2_min_size         = "1"
      autoscaling_ec2_desired_capacity = "2"

      scaleup_cloudwatch_alarm_evaluation_periods = "1"
      scaleup_cloudwatch_datapoints_to_alarm = "1"
      scaledown_cloudwatch_alarm_evaluation_periods = "1"
      scaledown_cloudwatch_datapoints_to_alarm = "1"
    }

    dev = {
    }

    stag = {
    }

    prod = {
    }
  }
}

variable "assume_role_principle" {
  type = map

  default = {
      "ecs_assume_resources" = ["ecs-tasks.amazonaws.com","ecs.amazonaws.com"]
      "ec2_assume_resources" = ["ec2.amazonaws.com","ecs.amazonaws.com", "ecs-tasks.amazonaws.com"]
  }
}

variable "aws_iam_managed_policy_arns" {
  type = list
  default = ["arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceRole"]
}

variable "custom_policy_actions" {
  type = map
  default = {
    "elasticache" = [
      "ec2:AuthorizeSecurityGroupIngress",
      "ec2:CreateNetworkInterface",
      "ec2:CreateSecurityGroup",
      "ec2:DeleteNetworkInterface",
      "ec2:DeleteSecurityGroup",
      "ec2:DescribeAvailabilityZones",
      "ec2:DescribeNetworkInterfaces",
      "ec2:DescribeSecurityGroups",
      "ec2:DescribeSubnets",
      "ec2:DescribeVpcs",
      "ec2:ModifyNetworkInterfaceAttribute",
      "ec2:RevokeSecurityGroupIngress",
      "cloudwatch:PutMetricData"
    ]

    "cloudwatch" = [  "logs:*" ]
    "ecstaskexecution" = [
      "ecr:GetAuthorizationToken",
      "ecr:BatchCheckLayerAvailability",
      "ecr:GetDownloadUrlForLayer",
      "ecr:BatchGetImage",
      "logs:CreateLogStream",
      "logs:PutLogEvents"
    ]
    "ecs" = [
      "ec2:DescribeTags",
      "ecs:CreateCluster",
      "ecs:DeregisterContainerInstance",
      "ecs:DiscoverPollEndpoint",
      "ecs:Poll",
      "ecs:RegisterContainerInstance",
      "ecs:StartTelemetrySession",
      "ecs:UpdateContainerInstancesState",
      "ecs:Submit*",
      "ecr:GetAuthorizationToken",
      "ecr:BatchCheckLayerAvailability",
      "ecr:GetDownloadUrlForLayer",
      "ecr:BatchGetImage",
      "logs:CreateLogStream",
      "logs:PutLogEvents"
    ]
    "s3" = ["s3:GetObject"]
    "secretmanager" = [
      "secretsmanager:*",
      "cloudformation:CreateChangeSet",
      "cloudformation:DescribeChangeSet",
      "cloudformation:DescribeStackResource",
      "cloudformation:DescribeStacks",
      "cloudformation:ExecuteChangeSet",
      "ec2:DescribeSecurityGroups",
      "ec2:DescribeSubnets",
      "ec2:DescribeVpcs",
      "kms:DescribeKey",
      "kms:ListAliases",
      "kms:ListKeys",
      "lambda:ListFunctions",
      "rds:DescribeDBClusters",
      "rds:DescribeDBInstances",
      "tag:GetResources"
    ]
  }
}

variable "custom_policy_resources" {
type = "map"
default = {
    "ssm" = ["*"]
    "cloudwatch" = ["*"]
    "elasticache" = ["*"]
    "ecstaskexecution" = ["*"]
    "secretmanager" = ["*"]
    "lambda" = ["arn:aws:lambda:*:*:function:SecretsManager*"]
    "s3" = ["arn:aws:s3:::awsserverlessrepo-changesets*"]
    "cloudformation" = ["arn:aws:serverlessrepo:*:*:applications/SecretsManager*"]
    "ecs" = ["*"]
  }
}

variable "TF_TOKEN_CLOUD_SRE" {
  type = string
}

variable "azs" {
  description = "Availability Zones in AWS to be use"
  default     = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "discover"
}

variable "tag" {
  default = "APP_TAG"
}

variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "discover"
}

variable "any_port" {
  default = "0"
}

variable "from_port" {
  default = "32768"
}

variable "to_port" {
  default = "65535"
}

variable "any_protocol" {
  default = "-1"
}

variable "tcp_protocol" {
  default = "tcp"
}

variable "all_ips" {
  description = "CIDR block to accept traffic from any ip"
  default     = ["0.0.0.0/0"]  
}

variable "elasticache_engine_version" {
  default = "5.0.6"
}

variable "elasticache_port" {
  default = "6379"
}

variable "cloudwatch_alarm_period" {
  default = "60"
}

variable "cloudwatch_alarm_metric_namespace" {
  default = "AWS/EC2"
}

variable "cloudwatch_alarm_cpu_statistic" {
  default = "Average"
}

variable "cpu_metric_name" {
  default = "CPUUtilization"
}

variable "cpu_scaleup_operator" {
  default = "GreaterThanOrEqualToThreshold"
}

variable "cpu_scaleup_threshold" {
  default = "60"
}

variable "autoscaling_scaling_adjust_scaleup" {
  default = "1"
}

variable "autoscaling_scaling_adjust_cooldown" {
  default = "300"
}

variable "autoscaling_policy_adjustment_type" {
  default = "ChangeInCapacity"
}

variable "health_check_type" {
  default = "ELB"
}

variable "termination_policy" {
  default = "OldestLaunchConfiguration"
}

variable "health_check_grace_period" {
  default = "300"
}

variable "cooldown_period" {
  default = "300"
}

variable "cpu_scaledown_operator" {
  default = "LessThanOrEqualToThreshold"
}

variable "cpu_scaledown_threshold" {
  default = "30"
}

variable "default_alb_ssl_policy" {
  default = "ELBSecurityPolicy-2016-08"
}
