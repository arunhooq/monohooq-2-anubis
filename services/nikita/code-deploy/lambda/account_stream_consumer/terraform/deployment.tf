########################################################################################################################
# Remote States
########################################################################################################################
data "terraform_remote_state" "nikita_infra_state" {
  backend = "remote"
  config = {
    hostname     = "app.terraform.io"
    organization = "nikita"
    token        = "SED_TF_TOKEN_NIKITA"
    workspaces = {
      name = "nikita-infra-${terraform.workspace}"
    }
  }
}

data "aws_caller_identity" "current" {}

########################################################################################################################
# Lambda
########################################################################################################################
module "lambda_function" {
  source = "../../../../../../modules/terraform/aws/lambda/function"

  vpc_subnet_ids         = local.private_subnet_ids
  vpc_security_group_ids = [local.lambda_security_group_id]
  iam_role_arn           = local.lambda_role_arn
  filename               = local.lambda_file_name
  runtime                = var.lambda_runtime
  timeout                = 15
  func_name              = local.lambda_function_name
  func_handler           = local.lambda_handler_name
  description            = local.lambda_name
  source_code_hash       = filebase64sha256(local.lambda_file_name)
  tags                   = local.tags
  environment_variables = {
    ENVIRONMENT         = terraform.workspace
    KINESIS_STREAM_NAME = local.kinesis_stream_name
  }
}

module "lambda_trigger" {
  source           = "../../../../../../modules/terraform/aws/lambda/trigger"
  event_source_arn = local.kinesis_stream_arn
  lambda_arn       = module.lambda_function.arn
}
