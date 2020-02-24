terraform {
  required_version = "~> 0.12"

  required_providers {
    aws = "~> 2"
  }
  backend "remote" {
    hostname     = "app.terraform.io"
    organization = "Anubis"
    token        = "TF_TOKEN_ANUBIS"
    workspaces {
      prefix = "anubis_infra_"
    }
  }
}
