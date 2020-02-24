######################################################  RESOURCES  #####################################################

module "anubis_web_parameters" {
  source    = "../../modules/terraform/aws/parameterstore"
  tags      = {
    ManagedBy = "${local.name}"
  }

  parameter_write = [
    {
      description= "environment for web",
      name= "/app/anubis/web/environment",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.environment}"
    },
    {
      description= "Project for web",
      name= "/app/anubis/web/Project",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.Project}"
    },
    {
      description= "suiteType for web",
      name= "/app/anubis/web/suiteType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.suiteType}"
    },
    {
      description= "userTypeList for web",
      name= "/app/anubis/web/userTypeList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].web.userTypeList}"
    },
    {
      description= "countries for web",
      name= "/app/anubis/web/countries",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].web.countries}"
    },
    {
      description= "strPlatformName for web",
      name= "/app/anubis/web/strPlatformName",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.strPlatformName}"
    },
    {
      description= "targetExecution for web",
      name= "/app/anubis/web/targetExecution",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.targetExecution}"
    },
    {
      description= "strGlobalTimeout for web",
      name= "/app/anubis/web/strGlobalTimeout",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.strGlobalTimeout}"
    },
    {
      description= "EmailList for web",
      name= "/app/anubis/web/EmailList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].web.EmailList}"
    },
    {
      description= "strTestRailSuiteName for web",
      name= "/app/anubis/web/strTestRailSuiteName",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.strTestRailSuiteName}"
    },
    {
      description= "strTestRail for web",
      name= "/app/anubis/web/strTestRail",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.strTestRail}"
    },
    {
      description= "strMaxRetryCount for web",
      name= "/app/anubis/web/strMaxRetryCount",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.strMaxRetryCount}"
    },
    {
      description= "isVideoRecord for web",
      name= "/app/anubis/web/isVideoRecord",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.isVideoRecord}"
    },
    {
      description= "RunType for web",
      name= "/app/anubis/web/RunType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].web.RunType}"
    }

  ]
}

######################################################  OUTPUTS  #####################################################

output "key_value_web_maps" {
  value = module.anubis_web_parameters.map
}
