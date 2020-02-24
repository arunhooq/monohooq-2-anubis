variable "env" {
  default = {

    play = {
      cidr           = "192.168.2.0/24"
      cidrs_public   = "192.168.2.0/28,192.168.2.16/28,192.168.2.48/28"
      cidrs_private  = "192.168.2.64/28,192.168.2.80/28,192.168.2.112/28"
      cidrs_database = "192.168.2.128/28,192.168.2.144/28,192.168.2.176/28"
      domain_name    = "play-hooq.tv"

      // nat
      multi_az_nat_gateway_enabled  = "false"
      single_az_nat_gateway_enabled = "true"

      // kinesis
      account_stream_shard_count     = 1
      transaction_stream_shard_count = 1

      // postgres db
      postgres_db_skip_final_snapshot       = "true"
      postgres_db_final_snapshot_identifier = "nikita-db-play-final-snapshot"

      // ecs
      ec2_root_volume_size = "20"
      ec2_instance_type    = "t3.medium"
    }

    dev = {
      cidr           = "172.16.4.0/23"
      cidrs_public   = "172.16.4.0/27,172.16.4.32/27,172.16.4.96/27"
      cidrs_private  = "172.16.4.128/27,172.16.4.160/27,172.16.4.224/27"
      cidrs_database = "172.16.5.0/27,172.16.5.32/27,172.16.5.96/27"
      domain_name    = "dev-hooq.tv"

      // nat
      multi_az_nat_gateway_enabled  = "false"
      single_az_nat_gateway_enabled = "true"

      // kinesis
      account_stream_shard_count     = 1
      transaction_stream_shard_count = 1

      // postgres db
      postgres_db_skip_final_snapshot       = "true"
      postgres_db_final_snapshot_identifier = "nikita-db-dev-final-snapshot"

      // ecs
      ec2_root_volume_size = "20"
      ec2_instance_type    = "t3.medium"
    }

    stag = {
      cidr           = "10.5.0.0/20"
      cidrs_public   = "10.5.0.0/24,10.5.1.0/24,10.5.3.0/24"
      cidrs_private  = "10.5.4.0/24,10.5.5.0/24,10.5.7.0/24"
      cidrs_database = "10.5.8.0/24,10.5.9.0/24,10.5.11.0/24"
      domain_name    = "stag-hooq.tv"

      // nat
      multi_az_nat_gateway_enabled  = "false"
      single_az_nat_gateway_enabled = "true"

      // kinesis
      account_stream_shard_count     = 1
      transaction_stream_shard_count = 1

      // postgres db
      postgres_db_skip_final_snapshot       = "false"
      postgres_db_final_snapshot_identifier = "nikita-db-stag-final-snapshot"

      // ecs
      ec2_root_volume_size = "20"
      ec2_instance_type    = "m4.large"
    }

    prod = {
      cidr           = "10.4.0.0/19"
      cidrs_public   = "10.4.0.0/24,10.4.1.0/24,10.4.3.0/24"
      cidrs_private  = "10.4.4.0/24,10.4.5.0/24,10.4.7.0/24"
      cidrs_database = "10.4.8.0/24,10.4.9.0/24,10.4.11.0/24"
      domain_name    = "prod-hooq.tv"

      // nat
      multi_az_nat_gateway_enabled  = "true"
      single_az_nat_gateway_enabled = "false"

      // kinesis
      account_stream_shard_count     = 1
      transaction_stream_shard_count = 1

      // postgres db
      postgres_db_skip_final_snapshot       = "false"
      postgres_db_final_snapshot_identifier = "nikita-db-prod-final-snapshot"

      // ecs
      ec2_root_volume_size = "20"
      ec2_instance_type    = "m4.large"
      alb_s3_bucket_name   = "hooq-alb-logs"
    }
  }
}

variable "azs" {
  description = "Availability Zones in AWS to be use"
  default     = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "nikita"
}

variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "nikita"
}

variable "tag" {
  default = "SED_APP_TAG"
}

variable "postgres_instance_type" {
  description = "Instance type of the database"
  default     = "db.r4.large"
}

variable "postgres_engine" {
  description = "Postgres database engine"
  default     = "aurora-postgresql"
}

variable "postgres_engine_version" {
  description = "Postgres database engine version"
  default     = "10.7"
}

variable "postgres_db_port" {
  description = "Database port (_e.g._ `5432` for `Postgres`). Used in the DB Security Group to allow access to the DB instance from the provided `security_group_ids`"
  default     = 5432
}

variable "postgres_db_replica_count" {
  description = "Postgres database replica count"
  default     = 1
}

variable "postgres_db_parameter_group" {
  description = "Postgres database parameter group name"
  default     = "default.aurora-postgresql10"
}

variable "postgres_db_cluster_group" {
  description = "Postgres database cluster group name"
  default     = "default.aurora-postgresql10"
}
variable "postgres_db_log_exports" {
  description = "List of log types to export to cloudwatch"
  default     = ["postgresql"]
}

variable "postgres_db_monitoring_interval" {
  description = "Postgres database monitoring interval"
  default     = 10
}

variable "postgres_db_storage_encrypted" {
  description = "Specifies whether the underlying storage layer should be encrypted"
  default     = true
}

variable "postgres_db_apply_immediately" {
  description = "Determines whether or not any DB modifications are applied immediately, or during the maintenance window"
  default     = true
}

variable "postgres_db_security_group_protocol" {
  description = "Postgres db security group protocol"
  default     = "TCP"
}

//ECS Related Variables
variable "assume_role_principle" {
  type = "map"

  default = {
    "ecs_assume_resources" = ["ecs-tasks.amazonaws.com", "ecs.amazonaws.com"]
    "ec2_assume_resources" = ["ec2.amazonaws.com", "ecs.amazonaws.com", "ecs-tasks.amazonaws.com"]
    "rds_assume_resources" = ["monitoring.rds.amazonaws.com"]
  }
}

variable "custom_policy_actions" {
  type = "map"
  default = {

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
      "ssm:DescribeAssociation",
      "ssm:GetDeployablePatchSnapshotForInstance",
      "ssm:GetDocument",
      "ssm:DescribeDocument",
      "ssm:GetManifest",
      "ssm:GetParameter",
      "ssm:GetParameters",
      "ssm:ListAssociations",
      "ssm:ListInstanceAssociations",
      "ssm:PutInventory",
      "ssm:PutComplianceItems",
      "ssm:PutConfigurePackageResult",
      "ssm:UpdateAssociationStatus",
      "ssm:UpdateInstanceAssociationStatus",
      "ssm:UpdateInstanceInformation",
      "ssmmessages:CreateControlChannel",
      "ssmmessages:CreateDataChannel",
      "ssmmessages:OpenControlChannel",
      "ssmmessages:OpenDataChannel",
      "ec2messages:AcknowledgeMessage",
      "ec2messages:DeleteMessage",
      "ec2messages:FailMessage",
      "ec2messages:GetEndpoint",
      "ec2messages:GetMessages",
      "ec2messages:SendReply"
    ]
    "lambda" = [
      "lambda:AddPermission",
      "lambda:CreateFunction",
      "lambda:GetFunction",
      "lambda:InvokeFunction",
      "lambda:UpdateFunctionConfiguration"
    ]
    "lambda_executor" = [
      "lambda:InvokeFunction"
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
  type = "map"
  default = {
    "ssm"              = ["*"]
    "cloudwatch"       = ["*"]
    "elasticache"      = ["*"]
    "ecstaskexecution" = ["*"]
    "secretmanager"    = ["*"]
    "lambda_executor"  = ["arn:aws:lambda:*:*:function:nikita*"]
    "lambda"           = ["arn:aws:lambda:*:*:function:SecretsManager*", "arn:aws:lambda:*:*:function:nikita*"]
    "s3"               = ["arn:aws:s3:::awsserverlessrepo-changesets*"]
    "cloudformation"   = ["arn:aws:serverlessrepo:*:*:applications/SecretsManager*"]
    "ecs"              = ["*"]
  }
}

variable "aws_iam_managed_policy_arns" {
  type    = "list"
  default = ["arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceRole", "arn:aws:iam::aws:policy/service-role/AmazonRDSEnhancedMonitoringRole"]
}

variable "any_port" {
  default = "0"
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
  default     = ["0.0.0.0/0"]
  description = "CIDR block to accept traffic from any ip"
}

