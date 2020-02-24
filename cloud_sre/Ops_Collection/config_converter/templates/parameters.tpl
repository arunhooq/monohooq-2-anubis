######################################################  RESOURCES  #####################################################

module "miyazaki_parameters" {
  source                   = "../../../modules/terraform/aws/parameterstore"
  tags = {
    ManagedBy = "${local.name}"
  }

  parameter_write = {{ TAGS }}
}


######################################################  OUTPUTS  #####################################################

output "key_value_maps" {
    value = module.miyazaki_parameters.map
}
