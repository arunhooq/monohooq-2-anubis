terraform {
  required_version = "~> 0.12"

  required_providers {
    aws = "~> 2"
  }
  backend "remote" {
    hostname     = "app.terraform.io"
    organization = "Nikita"
    token        = "SED_TF_TOKEN_NIKITA"
    workspaces {
      prefix = "nikita-infra-"
    }
  }
}
