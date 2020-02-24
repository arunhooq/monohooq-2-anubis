######################################################  RESOURCES  #####################################################

module "anubis_api_parameters" {
  source    = "../../modules/terraform/aws/parameterstore"
  tags      = {
    ManagedBy = "${local.name}"
  }

  parameter_write = [
    {
      description= "environment for api",
      name= "/app/anubis/api/environment",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].api.environment}"
    },
    {
      description= "Project for api",
      name= "/app/anubis/api/Project",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].api.Project}"
    },
    {
      description= "EmailList for api",
      name= "/app/anubis/api/EmailList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].api.EmailList}"
    },
    {
      description= "RunType for api",
      name= "/app/anubis/api/RunType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].api.RunType}"
    }
  ]
}

######################################################  OUTPUTS  #####################################################

output "key_value_sanctuary_maps" {
  value = module.anubis_api_parameters.map
}
