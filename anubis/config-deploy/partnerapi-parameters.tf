######################################################  RESOURCES  #####################################################

module "anubis_partnerapi_parameters" {
  source    = "../../modules/terraform/aws/parameterstore"
  tags      = {
    ManagedBy = "${local.name}"
  }

  parameter_write = [
    {
      description= "environment for partnerapi",
      name= "/app/anubis/partnerapi/environment",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].partnerapi.environment}"
    },
    {
      description= "eligibility service for partnerapi",
      name= "/app/anubis/partnerapi/eligibilityService",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].partnerapi.eligibilityService}"
    },
    {
      description= "Project for partnerapi",
      name= "/app/anubis/partnerapi/Project",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].partnerapi.Project}"
    },
    {
      description= "RunType for partnerapi",
      name= "/app/anubis/partnerapi/RunType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].partnerapi.RunType}"
    },
    {
      description= "EmailList for partnerapi",
      name= "/app/anubis/partnerapi/EmailList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].partnerapi.EmailList}"
    }

  ]
}

######################################################  OUTPUTS  #####################################################

output "key_value_partnerapi_maps" {
  value = module.anubis_partnerapi_parameters.map
}
