// general
locals {
  name                     = "${var.service_name}-${terraform.workspace}"
  lambda_name              = "lambda_${var.stream_name}_producer"
  lambda_function_name     = "${var.service_name}_${local.lambda_name}"
  lambda_handler_name      = local.lambda_name
  lambda_file_name         = "../build/${local.lambda_name}.zip"
  private_subnet_ids       = data.terraform_remote_state.nikita_infra_state.outputs.private_subnet_ids
  lambda_security_group_id = data.terraform_remote_state.nikita_infra_state.outputs.lambda_security_group_id
  lambda_role_arn          = data.terraform_remote_state.nikita_infra_state.outputs.lambda_role_arn
  kinesis_stream_arn       = data.terraform_remote_state.nikita_infra_state.outputs.account_stream_arn
  kinesis_stream_name      = data.terraform_remote_state.nikita_infra_state.outputs.account_stream_name

  tags = {
    service-name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }
}
