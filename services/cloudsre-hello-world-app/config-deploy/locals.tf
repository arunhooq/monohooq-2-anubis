######################################################  VARIABLES  #####################################################
locals {
  name = "${var.service-name}-${terraform.workspace}"

  #vpcid
  vpc_id = data.terraform_remote_state.cloudsrehelloworld_infra_state.outputs.vpc_id
}
