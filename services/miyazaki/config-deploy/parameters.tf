######################################################  REMOTE STATE  ##################################################
data "terraform_remote_state" "miyazaki_infra_state" {
  backend = "remote"
  config = {
    organization = "miyazaki"
    token        = "TF_TOKEN_MIYAZAKI"
    workspaces = {
      name = "miyazaki-infra-${terraform.workspace}"
    }
  }
}

######################################################  RESOURCES  #####################################################

module "miyazaki_parameters" {
  source    = "../../../modules/terraform/aws/parameterstore"
  tags      = {
                ManagedBy = "${local.name}"
            }

  parameter_write = [
    {
        description = "environment for REDIS_HOST",
        name        = "/app/miyazaki/REDIS_HOST",
        overwrite   = "true",
        type        = "String",
        value       = "${local.postgres_db_endpoint}"
    },
    {
        description = "environment for REDIS_PORT",
        name        = "/app/miyazaki/REDIS_PORT",
        overwrite   = "true",
        type        = "String",
        value       = "${local.postgres_db_port}"
    },
    {
        description= "environment for API_GATEWAY_ENV",
        name= "/app/miyazaki/API_GATEWAY_ENV",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].API_GATEWAY_ENV}"
    },
    {
        description= "environment for API_GATEWAY_VERSION_2_0",
        name= "/app/miyazaki/API_GATEWAY_VERSION_2_0",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].API_GATEWAY_VERSION_2_0}"
    },
    {
        description= "environment for ATTEMPT_TO_SHOW",
        name= "/app/miyazaki/ATTEMPT_TO_SHOW",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].ATTEMPT_TO_SHOW}"
    },
    {
        description= "environment for CHANNEL_BASE_URL",
        name= "/app/miyazaki/CHANNEL_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].CHANNEL_BASE_URL}"
    },
    {
        description= "environment for CONVIVA_CORE_SDK_PATH",
        name= "/app/miyazaki/CONVIVA_CORE_SDK_PATH",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].CONVIVA_CORE_SDK_PATH}"
    },
    {
        description= "environment for CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH",
        name= "/app/miyazaki/CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH}"
    },
    {
        description= "environment for DEFAULT_TEST_IP",
        name= "/app/miyazaki/DEFAULT_TEST_IP",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].DEFAULT_TEST_IP}"
    },
    {
        description= "environment for DEFAULT_TEST_REGION",
        name= "/app/miyazaki/DEFAULT_TEST_REGION",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].DEFAULT_TEST_REGION}"
    },
    {
        description= "environment for DISCOVER_BASE_URL",
        name= "/app/miyazaki/DISCOVER_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].DISCOVER_BASE_URL}"
    },
    {
        description= "environment for ENABLE_NEWRELIC",
        name= "/app/miyazaki/ENABLE_NEWRELIC",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].ENABLE_NEWRELIC}"
    },
    {
        description= "environment for ENV",
        name= "/app/miyazaki/ENV",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].ENV}"
    },
    {
        description= "environment for EVE_BASE_URL",
        name= "/app/miyazaki/EVE_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].EVE_BASE_URL}"
    },
    {
        description= "environment for EV_WEBVIEW_BASE_URL",
        name= "/app/miyazaki/EV_WEBVIEW_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].EV_WEBVIEW_BASE_URL}"
    },
    {
        description= "environment for INTERVAL_TO_SHOW",
        name= "/app/miyazaki/INTERVAL_TO_SHOW",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].INTERVAL_TO_SHOW}"
    },
    {
        description= "environment for KASHMIR_BASE_URL",
        name= "/app/miyazaki/KASHMIR_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].KASHMIR_BASE_URL}"
    },
    {
        description= "environment for KASHMIR_CACHE_EXPIRY",
        name= "/app/miyazaki/KASHMIR_CACHE_EXPIRY",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].KASHMIR_CACHE_EXPIRY}"
    },
    {
        description= "environment for KASHMIR_ENABLE_CACHE",
        name= "/app/miyazaki/KASHMIR_ENABLE_CACHE",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].KASHMIR_ENABLE_CACHE}"
    },
    {
        description= "environment for MMDB_VERSION",
        name= "/app/miyazaki/MMDB_VERSION",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].MMDB_VERSION}"
    },
    {
        description= "environment for NEWRELIC_APP",
        name= "/app/miyazaki/NEWRELIC_APP",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].NEWRELIC_APP}"
    },
    {
        description= "environment for NEWRELIC_BROWSER_APPLICATION_ID",
        name= "/app/miyazaki/NEWRELIC_BROWSER_APPLICATION_ID",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].NEWRELIC_BROWSER_APPLICATION_ID}"
    },
    {
        description= "environment for NEWRELIC_BROWSER_FILE_PATH",
        name= "/app/miyazaki/NEWRELIC_BROWSER_FILE_PATH",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].NEWRELIC_BROWSER_FILE_PATH}"
    },
    {
        description= "environment for NEWRELIC_LEVEL",
        name= "/app/miyazaki/NEWRELIC_LEVEL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].NEWRELIC_LEVEL}"
    },
    {
        description= "environment for NODE_DEBUG",
        name= "/app/miyazaki/NODE_DEBUG",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].NODE_DEBUG}"
    },
    {
        description= "environment for NODE_ENV",
        name= "/app/miyazaki/NODE_ENV",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].NODE_ENV}"
    },
    {
        description= "environment for NODE_PORT",
        name= "/app/miyazaki/NODE_PORT",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].NODE_PORT}"
    },
    {
        description= "environment for PLAY_BASE_URL",
        name= "/app/miyazaki/PLAY_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].PLAY_BASE_URL}"
    },
    {
        description= "environment for R21_ENABLED",
        name= "/app/miyazaki/R21_ENABLED",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].R21_ENABLED}"
    },
    {
        description= "environment for SANCTUARY_API_ENV",
        name= "/app/miyazaki/SANCTUARY_API_ENV",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].SANCTUARY_API_ENV}"
    },
    {
        description= "environment for SANCTUARY_API_VERSION",
        name= "/app/miyazaki/SANCTUARY_API_VERSION",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].SANCTUARY_API_VERSION}"
    },
    {
        description= "environment for SEARCH_BASE_URL",
        name= "/app/miyazaki/SEARCH_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].SEARCH_BASE_URL}"
    },
    {
        description= "environment for STAGING",
        name= "/app/miyazaki/STAGING",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].STAGING}"
    },
    {
        description= "environment for STAGING_DISCOVER_BASE_URL",
        name= "/app/miyazaki/STAGING_DISCOVER_BASE_URL",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].STAGING_DISCOVER_BASE_URL}"
    },
    {
        description= "environment for STAGING_TITLE_ID",
        name= "/app/miyazaki/STAGING_TITLE_ID",
        overwrite= "true",
        type= "String",
        value= "${var.env[terraform.workspace].STAGING_TITLE_ID}"
    }
]
}


######################################################  OUTPUTS  #####################################################

output "key_value_maps" {
    value = module.miyazaki_parameters.map
}