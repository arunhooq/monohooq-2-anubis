resource "aws_lambda_function" "this" {
  description                    = var.description
  filename                       = var.filename
  s3_bucket                      = var.bucket_name
  s3_key                         = var.bucket_key
  function_name                  = var.func_name
  handler                        = var.func_handler
  runtime                        = var.runtime
  memory_size                    = var.memory_size
  timeout                        = var.timeout
  role                           = var.iam_role_arn
  reserved_concurrent_executions = var.reserved_concurrent_executions
  tags                           = var.tags
  source_code_hash               = var.source_code_hash

  environment {
    variables = var.environment_variables
  }

  vpc_config {
    security_group_ids = var.vpc_security_group_ids
    subnet_ids         = var.vpc_subnet_ids
  }
}
