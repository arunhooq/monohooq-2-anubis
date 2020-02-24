resource "aws_iam_saml_provider" "identity_provider" {
  name                   = var.identity_provider_name
  saml_metadata_document = file(var.metadata_file_path)

  ### Count
  count = var.create_identity_saml_provider ? 1 : 0

  ### Tags
  # Tags are not supported in this resources
}