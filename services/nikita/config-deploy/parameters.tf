######################################################  REMOTE STATE  #####################################################
data "terraform_remote_state" "nikita_infra_state" {
  backend = "remote"
  config = {
    organization = "nikita"
    token        = "SED_TF_TOKEN_NIKITA"
    workspaces = {
      name = "nikita-infra-${terraform.workspace}"
    }
  }
}

######################################################  RESOURCES  #####################################################

module "nikita_parameters" {
  source = "../../../modules/terraform/aws/parameterstore"
  tags   = local.tags

  parameter_write = [
    // General parameters
    {
      description = "environment key",
      name        = "/app/nikita/ENV",
      overwrite   = "true",
      type        = "String",
      value       = terraform.workspace
    },
    {
      description = "environment for MIX_ENV",
      name        = "/app/nikita/MIX_ENV",
      overwrite   = "true",
      type        = "String",
      value       = terraform.workspace
    },

    // Nikita API parameters
    {
      description = "environment for API_HOST",
      name        = "/app/nikita/API_HOST",
      overwrite   = "true",
      type        = "String",
      value       = local.api_domain
    },

    // Nikita DB parameters
    {
      description = "environment for PG_DB_HOST",
      name        = "/app/nikita/PG_DB_HOST",
      overwrite   = "true",
      type        = "String",
      value       = local.postgres_db_endpoint
    },
    {
      description = "environment for PG_DB_PORT",
      name        = "/app/nikita/PG_DB_PORT",
      overwrite   = "true",
      type        = "String",
      value       = local.postgres_db_port
    },
    {
      description = "environment for PG_DB_NAME",
      name        = "/app/nikita/PG_DB_NAME",
      overwrite   = "true",
      type        = "String",
      value       = local.postgres_db_name
    },
    {
      description = "environment for PG_DB_USER",
      name        = "/app/nikita/PG_DB_USER",
      overwrite   = "true",
      type        = "String",
      value       = local.postgres_db_username
    },

    // Kong DB parameters
    {
      description = "environment for KONG_DB_HOST",
      name        = "/app/nikita/KONG_DB_HOST",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_db_endpoint
    },
    {
      description = "environment for KONG_DB_PORT",
      name        = "/app/nikita/KONG_DB_PORT",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_db_port
    },
    {
      description = "environment for KONG_DB_NAME",
      name        = "/app/nikita/KONG_DB_NAME",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_db_name
    },
    {
      description = "environment for KONG_DB_USER",
      name        = "/app/nikita/KONG_DB_USER",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_db_username
    },

    // Kong parameters
    {
      description = "Base URL for kong",
      name        = "/app/nikita/KONG_BASEURL",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_baseurl
    },
    {
      description = "environment for KONG_DATABASE",
      name        = "/app/nikita/KONG_DATABASE",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_database
    },
    {
      description = "environment for KONG_PROXY_ACCESS_LOG",
      name        = "/app/nikita/KONG_PROXY_ACCESS_LOG",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_proxy_access_log
    },
    {
      description = "environment for KONG_ADMIN_ACCESS_LOG",
      name        = "/app/nikita/KONG_ADMIN_ACCESS_LOG",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_admin_access_log
    },
    {
      description = "environment for KONG_PROXY_ERROR_LOG",
      name        = "/app/nikita/KONG_PROXY_ERROR_LOG",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_proxy_error_log
    },
    {
      description = "environment for KONG_ADMIN_ERROR_LOG",
      name        = "/app/nikita/KONG_ADMIN_ERROR_LOG",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_admin_error_log
    },
    {
      description = "environment for KONG_ADMIN_LISTEN",
      name        = "/app/nikita/KONG_ADMIN_LISTEN",
      overwrite   = "true",
      type        = "String",
      value       = local.kong_admin_listen
    },

    // evergent parameters
    {
      description = "Evergent API Base URL",
      name        = "/app/nikita/EVERGENT_API_BASEURL",
      overwrite   = "true",
      type        = "String",
      value       = local.evergent_api_baseurl
    },
    {
      description = "Evergent API Username",
      name        = "/app/nikita/EVERGENT_API_USERNAME",
      overwrite   = "true",
      type        = "String",
      value       = local.evergent_api_username
    }
  ]
}


######################################################  OUTPUTS  #####################################################

output "key_value_maps" {
  value = module.nikita_parameters.map
}
