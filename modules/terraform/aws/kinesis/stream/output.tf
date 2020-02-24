output "stream_id" {
  value = aws_kinesis_stream.stream.id
}

output "stream_arn" {
  value = aws_kinesis_stream.stream.arn
}

output "stream_name" {
  value = aws_kinesis_stream.stream.name
}

output "stream_shard_count" {
  value = aws_kinesis_stream.stream.shard_count
}
