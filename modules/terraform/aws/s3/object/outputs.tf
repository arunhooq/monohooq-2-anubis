output "ids" {
  value = aws_s3_bucket_object.this.*.id
}
