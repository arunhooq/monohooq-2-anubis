output "identity_provider_arn" {
  value = aws_iam_saml_provider.identity_provider.*.arn
}