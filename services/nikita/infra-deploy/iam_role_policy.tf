############################################################ VARIABLES  #######################################################################

## Assume role policy for the trust relationship
data "aws_iam_policy_document" "monitoring_rds_assume_role_policy" {
  statement {
    actions = ["sts:AssumeRole"]
    principals {
      type        = "Service"
      identifiers = var.assume_role_principle["rds_assume_resources"]
    }
    effect = "Allow"
  }
}

# Assume role policy for the trust relationship
data "aws_iam_policy_document" "ec2_assume_role_policy" {
  statement {
    actions = ["sts:AssumeRole"]
    principals {
      type        = "Service"
      identifiers = var.assume_role_principle["ec2_assume_resources"]
    }
    effect = "Allow"
  }
}

# Assume role policy for the trust relationship
data "aws_iam_policy_document" "ecs_service_assume_role_policy" {
  statement {
    actions = ["sts:AssumeRole"]
    principals {
      type        = "Service"
      identifiers = var.assume_role_principle["ecs_assume_resources"]
    }
    effect = "Allow"
  }
}

# Custom Policy Document for creating json
data "aws_iam_policy_document" "custom_policy" {
  statement {
    sid       = "1"
    actions   = var.custom_policy_actions["ssm"]
    effect    = "Allow"
    resources = var.custom_policy_resources["ssm"]
  }

  statement {
    sid       = "2"
    actions   = var.custom_policy_actions["cloudwatch"]
    effect    = "Allow"
    resources = var.custom_policy_resources["cloudwatch"]
  }

  statement {
    sid       = "3"
    actions   = var.custom_policy_actions["ecs"]
    effect    = "Allow"
    resources = var.custom_policy_resources["ecs"]
  }
}

data "aws_iam_policy_document" "ecs_task_custom_policy" {
  statement {
    sid       = "AllowExecuteLambda"
    actions   = var.custom_policy_actions.lambda_executor
    effect    = "Allow"
    resources = var.custom_policy_resources.lambda_executor
  }
}

# Custom Policy Document for creating policy json for ecs_task_execution_role
data "aws_iam_policy_document" "ecs_task_execution_custom_policy" {
  statement {
    sid       = "1"
    actions   = var.custom_policy_actions["ssm"]
    effect    = "Allow"
    resources = var.custom_policy_resources["ssm"]
  }

  statement {
    sid       = "2"
    actions   = var.custom_policy_actions["cloudwatch"]
    effect    = "Allow"
    resources = var.custom_policy_resources["cloudwatch"]
  }

  statement {
    sid       = "3"
    actions   = var.custom_policy_actions["secretmanager"]
    effect    = "Allow"
    resources = var.custom_policy_resources["secretmanager"]
  }

  statement {
    sid       = "4"
    actions   = var.custom_policy_actions["lambda"]
    effect    = "Allow"
    resources = var.custom_policy_resources["lambda"]
  }

  statement {
    sid       = "5"
    actions   = var.custom_policy_actions["ecstaskexecution"]
    effect    = "Allow"
    resources = var.custom_policy_resources["ecstaskexecution"]
  }

  statement {
    sid       = "6"
    actions   = var.custom_policy_actions["cloudformation"]
    effect    = "Allow"
    resources = var.custom_policy_resources["cloudformation"]
  }

  statement {
    sid       = "7"
    actions   = var.custom_policy_actions["s3"]
    effect    = "Allow"
    resources = var.custom_policy_resources["s3"]
  }
}

data "aws_iam_policy_document" "lambda_assume_role_policy_document" {
  statement {
    effect  = "Allow"
    actions = ["sts:AssumeRole"]

    principals {
      type = "Service"
      identifiers = [
        "lambda.amazonaws.com"
      ]
    }
  }
}

data "aws_iam_policy_document" "lambda_stream_consumer_policy_document" {
  // allow read from kinesis account and transaction stream
  statement {
    sid    = "AllowReadWriteKinesisStream"
    effect = "Allow"
    resources = [
      module.account_stream.stream_arn,
      module.transaction_stream.stream_arn
    ]
    actions = [
      "kinesis:DescribeStream",
      "kinesis:DescribeStreamSummary",
      "kinesis:PutRecord",
      "kinesis:PutRecords",
      "kinesis:GetRecords",
      "kinesis:GetShardIterator",
      "kinesis:ListShards",
      "kinesis:ListStreams",
      "kinesis:SubscribeToShard",
    ]
  }

  // allow VPC access
  statement {
    sid       = "AllowConnectionToVPC"
    effect    = "Allow"
    resources = ["*"]
    actions = [
      "ec2:CreateNetworkInterface",
      "ec2:DescribeNetworkInterfaces",
      "ec2:DeleteNetworkInterface",
    ]
  }

  // allow logging to cloudwatch
  statement {
    sid       = "AllowLogToCloudwatch"
    effect    = "Allow"
    resources = ["*"]
    actions = [
      "logs:CreateLogGroup",
      "logs:CreateLogStream",
      "logs:DescribeLogGroups",
      "logs:DescribeLogStreams",
      "logs:PutLogEvents",
      "logs:GetLogEvents",
      "logs:FilterLogEvents",
    ]
  }

  // allow decode error
  statement {
    sid       = "AllowDecodeAuthorizationError"
    effect    = "Allow"
    resources = ["*"]
    actions   = ["sts:DecodeAuthorizationMessage"]
  }

  // allow read parameter store
  statement {
    sid       = "AllowReadParameterStore"
    effect    = "Allow"
    resources = ["*"]
    actions   = ["ssm:GetParameter*"]
  }
}

###################################################monitoring_rds_service_role#################################################

module "monitoring_rds_service_role" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/role"
  role_name                   = "${local.name}-monitoring-rds-service-role"
  create_instance_role        = "false"
  iam_assume_role_policy_data = data.aws_iam_policy_document.monitoring_rds_assume_role_policy.json
}

module "monitoring_rds_service_managed_policy" {
  source                  = "../../../modules/terraform/aws/iam/role_policy/policy/managed"
  role_name               = module.monitoring_rds_service_role.role_name
  iam_managed_policy_arns = var.aws_iam_managed_policy_arns
}

###################################################ecs_ec2_role#################################################
module "ecs_ec2_role" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/role"
  role_name                   = "${local.name}-ecs-ec2-role"
  create_instance_role        = "true"
  iam_assume_role_policy_data = data.aws_iam_policy_document.ec2_assume_role_policy.json

}

module "ecs_ec2_custom_policy" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/policy/custom"
  role_name                   = [module.ecs_ec2_role.role_name]
  iam_custom_role_policy_data = data.aws_iam_policy_document.custom_policy.json
  iam_custom_policy_name      = "${local.name}-ecs-ec2-custom-policy"
}


###################################################ecs_task_role#################################################
module "ecs_task_role" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/role"
  role_name                   = "${local.name}-ecs-task-role"
  create_instance_role        = "false"
  iam_assume_role_policy_data = data.aws_iam_policy_document.ecs_service_assume_role_policy.json
}

module "ecs_task_custom_policy" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/policy/custom"
  role_name                   = [module.ecs_task_role.role_name]
  iam_custom_role_policy_data = data.aws_iam_policy_document.ecs_task_custom_policy.json
  iam_custom_policy_name      = "${local.name}-ecs-task-custom-policy"
}

###################################################ecs_task_execution_role#######################################
module "ecs_task_execution_role" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/role"
  role_name                   = "${local.name}-ecs-task-execution-role"
  create_instance_role        = "false"
  iam_assume_role_policy_data = data.aws_iam_policy_document.ecs_service_assume_role_policy.json
}

module "ecs_task_execution_custom_policy" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/policy/custom"
  role_name                   = [module.ecs_task_execution_role.role_name]
  iam_custom_role_policy_data = data.aws_iam_policy_document.ecs_task_execution_custom_policy.json
  iam_custom_policy_name      = "${local.name}-ecs-task-execution-custom-policy"
}

###################################################ecs_service_role#######################################

module "ecs_service_role" {
  source                      = "../../../modules/terraform/aws/iam/role_policy/role"
  role_name                   = "${local.name}-ecs-service-role"
  create_instance_role        = "false"
  iam_assume_role_policy_data = data.aws_iam_policy_document.ecs_service_assume_role_policy.json
}

module "ecs_service_managed_policy" {
  source                  = "../../../modules/terraform/aws/iam/role_policy/policy/managed"
  role_name               = module.ecs_service_role.role_name
  iam_managed_policy_arns = var.aws_iam_managed_policy_arns
}

########################################################################################################################
# Lambda
########################################################################################################################
module "lambda_role" {
  source = "../../../modules/terraform/aws/iam/role_policy/role"

  role_name                   = local.lambda_role_name
  iam_assume_role_policy_data = data.aws_iam_policy_document.lambda_assume_role_policy_document.json
}

module "lambda_policy" {
  source = "../../../modules/terraform/aws/iam/role_policy/policy/custom"

  iam_custom_policy_name      = local.lambda_policy_name
  iam_custom_role_policy_data = data.aws_iam_policy_document.lambda_stream_consumer_policy_document.json
  role_name                   = [local.lambda_role_name]
}

###########################################################   OUTPUTS   ################################################################################

output "monitoring_rds_service_role_name" {
  value = module.monitoring_rds_service_role.role_name
}

output "monitoring_rds_service_role_arn" {
  value = module.monitoring_rds_service_role.role_arn
}

output "ecs_ec2_role_name" {
  value = module.ecs_ec2_role.role_name
}
output "ecs_ec2_role_arn" {
  value = module.ecs_ec2_role.role_arn
}

output "ecs_instance_profile_name" {
  value = module.ecs_ec2_role.instance_profile_name
}

output "ecs_task_role_name" {
  value = module.ecs_task_role.role_name
}

output "ecs_task_role_arn" {
  value = module.ecs_task_role.role_arn
}

output "ecs_task_execution_role_name" {
  value = module.ecs_task_execution_role.role_name
}

output "ecs_task_execution_role_arn" {
  value = module.ecs_task_execution_role.role_arn
}

output "ecs_service_role_name" {
  value = module.ecs_service_role.role_name
}

output "ecs_service_role_arn" {
  value = module.ecs_service_role.role_arn
}

output "lambda_role_arn" {
  value = module.lambda_role.role_arn
}
