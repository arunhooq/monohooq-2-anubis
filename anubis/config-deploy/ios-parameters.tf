######################################################  RESOURCES  #####################################################

module "anubis_ios_parameters" {
  source    = "../../modules/terraform/aws/parameterstore"
  tags      = {
    ManagedBy = "${local.name}"
  }

  parameter_write = [
    {
      description= "environment for ios",
      name= "/app/anubis/ios/environment",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.environment}"
    },
    {
      description= "Project for ios",
      name= "/app/anubis/ios/Project",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.Project}"
    },
    {
      description= "suiteType for ios",
      name= "/app/anubis/ios/suiteType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.suiteType}"
    },
    {
      description= "userTypeList for ios",
      name= "/app/anubis/ios/userTypeList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].ios.userTypeList}"
    },
    {
      description= "countries for ios",
      name= "/app/anubis/ios/countries",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].ios.countries}"
    },
    {
      description= "strPlatformName for ios",
      name= "/app/anubis/ios/strPlatformName",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.strPlatformName}"
    },
    {
      description= "targetExecution for ios",
      name= "/app/anubis/ios/targetExecution",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.targetExecution}"
    },
    {
      description= "strGlobalTimeout for ios",
      name= "/app/anubis/ios/strGlobalTimeout",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.strGlobalTimeout}"
    },
    {
      description= "EmailList for ios",
      name= "/app/anubis/ios/EmailList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].ios.EmailList}"
    },
    {
      description= "strTestRailSuiteName for ios",
      name= "/app/anubis/ios/strTestRailSuiteName",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.strTestRailSuiteName}"
    },
    {
      description= "strTestRail for ios",
      name= "/app/anubis/ios/strTestRail",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.strTestRail}"
    },
    {
      description= "strMaxRetryCount for ios",
      name= "/app/anubis/ios/strMaxRetryCount",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.strMaxRetryCount}"
    },
    {
      description= "isVideoRecord for ios",
      name= "/app/anubis/ios/isVideoRecord",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.isVideoRecord}"
    },
    {
      description= "RunType for ios",
      name= "/app/anubis/ios/RunType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].ios.RunType}"
    }


  ]
}

######################################################  OUTPUTS  #####################################################

output "key_value_ios_maps" {
  value = module.anubis_ios_parameters.map
}