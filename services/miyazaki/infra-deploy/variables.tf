######################################################  VARIABLES  #####################################################

variable "env" {

  default = {

    play = {
      cidr                             = "192.168.1.0/24"
      cidrs_public                     = "192.168.1.0/28,192.168.1.16/28,192.168.1.32/28"
      cidrs_private                    = "192.168.1.48/28,192.168.1.64/28,192.168.1.80/28"
      cidrs_elasticache                = "192.168.1.96/28,192.168.1.112/28,192.168.1.128/28"
      domain_name                      = "play-hooq.tv"
      ec2_root_volume_size             = "20"
      ec2_instance_type                = "t3.medium"
      elasticache_intance_type         = "cache.t3.medium"
      external_integration_name        = ["grab.play-hooq.tv", "sg-grab.play-hooq.tv"]
      vpc_peer_accepter_id             = ""
      autoscaling_ec2_max_size         = "5"
      autoscaling_ec2_min_size         = "1"
      autoscaling_ec2_desired_capacity = "1"
      scaleup_cloudwatch_alarm_evaluation_periods = "1"
      scaleup_cloudwatch_datapoints_to_alarm = "1"
      scaledown_cloudwatch_alarm_evaluation_periods = "1"
      scaledown_cloudwatch_datapoints_to_alarm = "1"
    }

    dev = {
      cidr                             = "172.16.2.0/23"
      cidrs_public                     = "172.16.2.0/27,172.16.2.32/27,172.16.2.64/27"
      cidrs_private                    = "172.16.2.96/27,172.16.2.128/27,172.16.2.160/27"
      cidrs_elasticache                = "172.16.2.192/27,172.16.2.224/27,172.16.3.0/27"
      domain_name                      = "dev-hooq.tv"
      ec2_root_volume_size             = "20"
      ec2_instance_type                = "t3.medium"
      elasticache_intance_type         = "cache.t3.medium"
      external_integration_name        = ["grab.dev-hooq.tv", "sg-grab.dev-hooq.tv"]
      vpc_peer_accepter_id             = ""
      autoscaling_ec2_max_size         = "5"
      autoscaling_ec2_min_size         = "1"
      autoscaling_ec2_desired_capacity = "2"
      scaleup_cloudwatch_alarm_evaluation_periods = "1"
      scaleup_cloudwatch_datapoints_to_alarm = "1"
      scaledown_cloudwatch_alarm_evaluation_periods = "1"
      scaledown_cloudwatch_datapoints_to_alarm = "1"
    }

    stag = {
      cidr                             = "10.3.0.0/20"
      cidrs_public                     = "10.3.0.0/24,10.3.1.0/24,10.3.2.0/24"
      cidrs_private                    = "10.3.3.0/24,10.3.4.0/24,10.3.5.0/24"
      cidrs_elasticache                = "10.3.6.0/24,10.3.7.0/24,10.3.8.0/24"
      domain_name                      = "stag-hooq.tv"
      ec2_root_volume_size             = "20"
      ec2_instance_type                = "t3.medium"
      elasticache_intance_type         = "cache.t3.medium"
      external_integration_name        = ["grab.stag-hooq.tv", "sg-grab.stag-hooq.tv"]
      vpc_peer_accepter_id             = "vpc-c9eb86ae"
      autoscaling_ec2_max_size         = "5"
      autoscaling_ec2_min_size         = "1"
      autoscaling_ec2_desired_capacity = "2"
      scaleup_cloudwatch_alarm_evaluation_periods = "1"
      scaleup_cloudwatch_datapoints_to_alarm = "1"
      scaledown_cloudwatch_alarm_evaluation_periods = "1"
      scaledown_cloudwatch_datapoints_to_alarm = "1"
    }

    prod = {
      cidr                             = "10.2.0.0/19"
      cidrs_public                     = "10.2.0.0/24,10.2.1.0/24,10.2.2.0/24"
      cidrs_private                    = "10.2.3.0/24,10.2.4.0/24,10.2.5.0/24"
      cidrs_elasticache                = "10.2.6.0/24,10.2.7.0/24,10.2.8.0/24"
      domain_name                      = "prod-hooq.tv"
      ec2_root_volume_size             = "30"
      ec2_instance_type                = "m5.large"
      elasticache_intance_type         = "cache.m4.large"
      external_integration_name        = ["grab.prod-hooq.tv", "sg-grab.prod-hooq.tv","grab.hooq.tv", "sg-grab.hooq.tv",]
      alb_s3_bucket_name               = "hooq-alb-logs"
      vpc_peer_accepter_id             = "vpc-11903f74"
      autoscaling_ec2_max_size         = "10"
      autoscaling_ec2_min_size         = "3"
      autoscaling_ec2_desired_capacity = "3"
      scaleup_cloudwatch_alarm_evaluation_periods = "2"
      scaleup_cloudwatch_datapoints_to_alarm = "2"
      scaledown_cloudwatch_alarm_evaluation_periods = "1"
      scaledown_cloudwatch_datapoints_to_alarm = "1"
      hooq_domain_name = ["hooq.tv"]
      hooq_subject_alternative_names = ["grab.hooq.tv","sg-grab.hooq.tv"]

    }
  }
}

variable "assume_role_principle" {
  type = map

  default = {
    "ecs_assume_resources" = ["ecs-tasks.amazonaws.com", "ecs.amazonaws.com"]
    "ec2_assume_resources" = ["ec2.amazonaws.com", "ecs.amazonaws.com", "ecs-tasks.amazonaws.com"]
  }
}

variable "aws_iam_managed_policy_arns" {
  type    = list
  default = ["arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceRole"]
}

###################custom_policy_actions########################

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

    "cloudwatch" = ["logs:*"]
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
    "ssm" = [
      "ssm:GetParameter",
      "ssm:GetParameters",
      "ssm:UpdateInstanceInformation",
      "ssmmessages:*",
      "ec2messages:*"
    ]
    "lambda" = [
      "lambda:AddPermission",
      "lambda:CreateFunction",
      "lambda:GetFunction",
      "lambda:InvokeFunction",
      "lambda:UpdateFunctionConfiguration"
    ]
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
    "cloudformation" = [
      "serverlessrepo:CreateCloudFormationChangeSet"
    ]

  }
}

variable "custom_policy_resources" {
  type = map
  default = {
    "ssm"              = ["*"]
    "cloudwatch"       = ["*"]
    "elasticache"      = ["*"]
    "ecstaskexecution" = ["*"]
    "secretmanager"    = ["*"]
    "lambda"           = ["arn:aws:lambda:*:*:function:SecretsManager*"]
    "s3"               = ["arn:aws:s3:::awsserverlessrepo-changesets*"]
    "cloudformation"   = ["arn:aws:serverlessrepo:*:*:applications/SecretsManager*"]
    "ecs"              = ["*"]
  }
}

variable "http_tcp_listeners" {
  type = list
  default = [
    {
      "port"     = 80
      "protocol" = "HTTP"
    }
  ]
}

variable "https_tcp_listeners" {
  type = list
  default = [
    {
      "port"     = 443
      "protocol" = "HTTPS"
    }
  ]
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
  default = ["0.0.0.0/0"]
}

variable "http_port" {
  default = "80"
}

variable "https_port" {
  default = "443"
}

variable "azs" {
  description = "Availability Zones in AWS to be use"
  default     = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "miyazaki"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "miyazaki"
}

variable "tag" {
  default = "GITHUBSHA"
}

variable "elasticache_engine_version" {
  default = "5.0.5"
}

variable "elasticache_port" {
  default = "6379"
}


variable "volume_type" {
  default = "gp2"
}

variable "health_check_grace_period" {
  default = "300"
}

variable "health_check_type" {
  default = "ELB"
}


variable "termination_policy" {
  default = "OldestLaunchConfiguration"
}

variable "cooldown_period" {
  default = "300"
}

variable "region" {
  default = "ap-southeast-1"
}

variable "cloudwatch_alarm_period" {
  default = "60"
}

variable "cpu_metric_name" {
  default = "CPUUtilization"
}

variable "cpu_scaleup_operator" {
  default = "GreaterThanOrEqualToThreshold"
}

variable "cpu_scaledown_operator" {
  default = "LessThanOrEqualToThreshold"
}

variable "cloudwatch_alarm_metric_namespace" {
  default = "AWS/EC2"
}

variable "cloudwatch_alarm_cpu_statistic" {
  default = "Average"
}

variable "cpu_scaleup_threshold" {
  default = "60"
}

variable "cpu_scaledown_threshold" {
  default = "30"
}

variable "autoscaling_scaling_adjust_scaleup" {
  default = "1"
}

variable "autoscaling_scaling_adjust_scaledown" {
  default = "-1"
}

variable "autoscaling_scaling_adjust_cooldown" {
  default = "300"
}

variable "autoscaling_policy_adjustment_type" {
  default = "ChangeInCapacity"
}

variable "loggroup_retention_in_days" {
  default = "180"
}

variable "default_alb_ssl_policy" {
  default = "ELBSecurityPolicy-2016-08"
}
