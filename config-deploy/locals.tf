######################################################  VARIABLES  ####################################################
locals {
  name = "${var.service-name}-${terraform.workspace}"
}

