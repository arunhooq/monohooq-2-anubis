######################################################  VARIABLES  #####################################################

variable "env" {

  default = {

    play = {
      cidr                     = "192.168.3.0/24"
      cidrs_public             = "192.168.3.0/28,192.168.3.16/28,192.168.3.32/28"
      cidrs_private            = "192.168.3.48/28,192.168.3.64/28,192.168.3.80/28"
      domain_name              = "play-hooq.tv"
      multi_az_nat_gateway     = "false"
      single_az_nat_gateway    = "true"
      ec2_root_volume_size     = "20"
      ec2_instance_type        = "t3.medium"
      elasticache_intance_type = "cache.t3.medium"
      external_integration_name = ["anubis.play-hooq.tv","grab.play-hooq.tv"]
    }

    dev = {
      cidr                     = "172.16.6.0/23"
      cidrs_public             = "172.16.6.0/27,172.16.6.32/27,172.16.6.64/27"
      cidrs_private            = "172.16.6.96/27,172.16.6.128/27,172.16.6.160/27"
      domain_name               = "dev-hooq.tv"
      multi_az_nat_gateway     = "false"
      single_az_nat_gateway    = "true"
      ec2_root_volume_size     = "20"
      ec2_instance_type        = "m4.large"
      elasticache_intance_type = "cache.m4.large"
      external_integration_name = ["anubis.dev-hooq.tv"]
      vpc_peer_accepter_id      = "vpc-c9eb86ae"
    }

    stag = {
      cidr                     = "10.7.0.0/20"
      cidrs_public             = "10.7.1.0/24,10.7.2.0/24,10.7.3.0/24"
      cidrs_private            = "10.7.4.0/24,10.7.5.0/24,10.7.6.0/24"
      domain_name               = "stag-hooq.tv"
      multi_az_nat_gateway     = "false"
      single_az_nat_gateway    = "true"
      ec2_root_volume_size     = "20"
      ec2_instance_type        = "m4.large"
      elasticache_intance_type = "cache.m4.large"
      external_integration_name = ["anubis.stag-hooq.tv"]
      vpc_peer_accepter_id      = "vpc-c9eb86ae"
    }

  }
}

variable "azs" {
  description = "Availability Zones in AWS to be use"
  default     = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "service-name" {
  description = "Tag: Service Name for all resources"
  default     = "anubis"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "anubis"
}

variable "tag" {
  default = "GITHUBSHA"
}

variable "assume_role_principle" {
  type = "map"

  default = {
    "ecs_assume_resources" = ["ecs-tasks.amazonaws.com","ecs.amazonaws.com"]
    "ec2_assume_resources" = ["ec2.amazonaws.com","ecs.amazonaws.com", "ecs-tasks.amazonaws.com"]
  }
}

variable "aws_iam_managed_policy_arns" {
  type = "list"
  default = ["arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceRole"]
}




###################custom_policy_actions#########################

variable "custom_policy_actions" {
  type = "map"
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