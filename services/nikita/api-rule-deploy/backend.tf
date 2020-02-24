terraform {

  required_version = "~> 0.12"

  backend "remote" {
    hostname     = "app.terraform.io"
    organization = "nikita"
    token        = "SED_TF_TOKEN_NIKITA"
    workspaces {
      prefix = "nikita-kong-api-rule-"
    }
  }
}
