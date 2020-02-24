######################################################  RESOURCES  #####################################################

module "anubis_android_parameters" {
  source    = "../../modules/terraform/aws/parameterstore"
  tags      = {
    ManagedBy = "${local.name}"
  }

  parameter_write = [
    {
      description= "environment for android",
      name= "/app/anubis/android/environment",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].android.environment}"
    },
    {
      description= "Project for android",
      name= "/app/anubis/android/Project",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].android.Project}"
    },
    {
      description= "suiteType for android",
      name= "/app/anubis/android/suiteType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].android.suiteType}"
    },
    {
      description= "userTypeList for android",
      name= "/app/anubis/android/userTypeList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].android.userTypeList}"
    },
    {
      description= "countries for android",
      name= "/app/anubis/android/countries",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].android.countries}"
    },
    {
      description= "strPlatformName for android",
      name= "/app/anubis/android/strPlatformName",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].android.strPlatformName}"
    },
    {
      description= "targetExecution for android",
      name= "/app/anubis/android/targetExecution",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].android.targetExecution}"
    },
    {
      description= "strGlobalTimeout for android",
      name= "/app/anubis/android/strGlobalTimeout",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].android.strGlobalTimeout}"
    },
    {
      description= "EmailList for android",
      name= "/app/anubis/android/EmailList",
      overwrite= "true",
      type= "StringList",
      value= "${var.env[terraform.workspace].android.EmailList}"
    },
    {
      description= "RunType for android",
      name= "/app/anubis/android/RunType",
      overwrite= "true",
      type= "String",
      value= "${var.env[terraform.workspace].android.RunType}"
    }

  ]
}

######################################################  OUTPUTS  #####################################################

output "key_value_android_maps" {
  value = module.anubis_android_parameters.map
}