######################################################  VARIABLES  #####################################################
locals {
  name = "${var.service-name}-${terraform.workspace}"

  #redis variables
  postgres_db_endpoint = data.terraform_remote_state.miyazaki_infra_state.outputs.elasticache_endpoint
  postgres_db_port     = data.terraform_remote_state.miyazaki_infra_state.outputs.elasticache_port
}

