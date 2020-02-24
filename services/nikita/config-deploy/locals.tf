######################################################  VARIABLES  #####################################################
locals {
  name = "${var.service_name}-${terraform.workspace}"

  tags = {
    service-name = var.service_name
    owner        = var.owner
    environment  = terraform.workspace
    version      = var.tag
  }

  // Database variables
  postgres_db_endpoint = data.terraform_remote_state.nikita_infra_state.outputs.postgres_db_endpoint
  postgres_db_port     = data.terraform_remote_state.nikita_infra_state.outputs.postgres_db_port
  postgres_db_name     = data.terraform_remote_state.nikita_infra_state.outputs.postgres_db_name
  postgres_db_username = data.terraform_remote_state.nikita_infra_state.outputs.postgres_db_username

  // Kong Database variables
  kong_db_endpoint = data.terraform_remote_state.nikita_infra_state.outputs.kong_db_endpoint
  kong_db_port     = data.terraform_remote_state.nikita_infra_state.outputs.kong_db_port
  kong_db_name     = data.terraform_remote_state.nikita_infra_state.outputs.kong_db_name
  kong_db_username = data.terraform_remote_state.nikita_infra_state.outputs.kong_db_username

  // API variables
  api_domain = data.terraform_remote_state.nikita_infra_state.outputs.api_domain

  // Kong variables
  kong_baseurl          = var.env[terraform.workspace].kong_baseurl
  kong_database         = "postgres"
  kong_proxy_access_log = "/dev/stdout"
  kong_admin_access_log = "/dev/stdout"
  kong_proxy_error_log  = "/dev/stderr"
  kong_admin_error_log  = "/dev/stderr"
  kong_admin_listen     = "0.0.0.0:8001, 0.0.0.0:8444 ssl"

  // Evergent variables
  evergent_api_baseurl = var.env[terraform.workspace].evergent_api_baseurl
  evergent_api_username = var.env[terraform.workspace].evergent_api_username
}
