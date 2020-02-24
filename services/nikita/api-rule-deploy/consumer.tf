########################################################################################################################
# Consumer hooqinternal:nikita_api
########################################################################################################################
resource "kong_consumer" "hooqinternal_nikita_api" {
  username  = "hooqinternal:nikita_api"
  custom_id = "ca9b7f3d-80a9-4d96-bfcd-0a2ac13c8e61"
}

resource "kong_consumer_plugin_config" "hooqinternal_nikita_api_key" {
  consumer_id = kong_consumer.hooqinternal_nikita_api.id
  plugin_name = "key-auth"
  config_json = <<EOT
    {
      "key": "SED_KONG_APIKEY_HOOQINTERNAL_NIKITA_API"
    }
EOT
}

resource "kong_consumer_plugin_config" "hooqinternal_nikita_acl" {
  consumer_id = kong_consumer.hooqinternal_nikita_api.id
  plugin_name = "acls"
  config_json = <<EOT
    {
      "group": "nikita_api_admin"
    }
EOT
}

########################################################################################################################
# Consumer evergent:default
########################################################################################################################
resource "kong_consumer" "evergent_default" {
  username  = "evergent:default"
  custom_id = "cf1b10d5-1b41-4055-be3a-139af4a8a9e1"
}

resource "kong_consumer_plugin_config" "evergent_default_auth" {
  consumer_id = kong_consumer.evergent_default.id
  plugin_name = "key-auth"
  config_json = <<EOT
    {
      "key": "SED_KONG_APIKEY_EVERGENT_DEFAULT"
    }
EOT
}

resource "kong_consumer_plugin_config" "evergent_default_acl_account" {
  consumer_id = kong_consumer.evergent_default.id
  plugin_name = "acls"
  config_json = <<EOT
    {
      "group": "nikita_lambda_account_stream_producer"
    }
EOT
}

resource "kong_consumer_plugin_config" "evergent_default_acl_transaction" {
  consumer_id = kong_consumer.evergent_default.id
  plugin_name = "acls"
  config_json = <<EOT
    {
      "group": "nikita_lambda_transaction_stream_producer"
    }
EOT
}
