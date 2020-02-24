terraform {

  required_version = "~> 0.12"

  required_providers {
    aws = "~> 2"
    template = "~> 2"
  }

  backend "remote" {
    hostname     = "app.terraform.io"
    organization = "miyazaki"
    token = "TF_TOKEN_MIYAZAKI"
    workspaces {
      prefix = "miyazaki-metricbeat-"
    }
  }
}
