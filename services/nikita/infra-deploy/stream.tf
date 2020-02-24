########################################################################################################################
# Kinesis Streams
########################################################################################################################
module "account_stream" {
  source      = "../../../modules/terraform/aws/kinesis/stream"
  stream_name = local.account_stream_name
  shard_count = local.account_stream_shard_count
  tags        = local.tags
}

module "transaction_stream" {
  source      = "../../../modules/terraform/aws/kinesis/stream"
  stream_name = local.transaction_stream_name
  shard_count = local.transaction_stream_shard_count
  tags        = local.tags
}

########################################################################################################################
# Outputs
########################################################################################################################
output "account_stream_arn" {
  value       = module.account_stream.stream_arn
  description = "AWS kinesis account stream ARN"
}

output "transaction_stream_arn" {
  value       = module.transaction_stream.stream_arn
  description = "AWS kinesis transaction stream ARN"
}

output "account_stream_name" {
  value       = module.account_stream.stream_name
  description = "AWS kinesis account stream name"
}

output "transaction_stream_name" {
  value       = module.transaction_stream.stream_name
  description = "AWS kinesis account stream name"
}
