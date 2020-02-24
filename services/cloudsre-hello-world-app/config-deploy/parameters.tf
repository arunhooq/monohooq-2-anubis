######################################################  REMOTE STATE  ##################################################
data "terraform_remote_state" "cloudsrehelloworld_infra_state" {
  backend = "remote"
  config = {
    organization = "Cloud_SRE"
    token = "TF_TOKEN_CLOUD_SRE"
    workspaces = {
      name = "cloudsre-hello-world-app-infra-${terraform.workspace}"
    }
  }
}

######################################################  RESOURCES  #####################################################

module "cloudsre-hello-world-app_parameters" {
  source    = "../../../modules/terraform/aws/parameterstore"
  tags      = {
                ManagedBy = "${local.name}"
            }

  parameter_write = [
    {
      description = "CloudSRE hello-world APP NAME",
      name        = "/app/cloudsre-helloworld/NAME",
      overwrite   = "true",
      type        = "String",
      value       =  "${var.env[terraform.workspace].APP_NAME}"
    },
    {
      description = "Cloudsre helloworld app VPC-ID",
      name        = "/app/cloudsre-helloworld/VPCID",
      overwrite   = "true",
      type        = "String",
      value       = "${local.vpc_id}"
    }
]
}


######################################################  OUTPUTS  #####################################################

output "cloudsre-hello-world-app_key_value_maps" {
  value = module.cloudsre-hello-world-app_parameters.map
}
