########################################################################################################################
# Account Stream Producer
########################################################################################################################
resource "kong_service" "nikita_lambda_account_stream_producer" {
  name     = "nikita_lambda_account_stream_producer"
  protocol = "http"
  host     = "localhost"
  port     = 8000
  path     = "/"
}

resource "kong_route" "nikita_lambda_account_stream_producer" {
  name       = "nikita_lambda_account_stream_producer"
  protocols  = ["http", "https"]
  paths      = ["/callback/evergent/account"]
  methods    = ["POST"]
  service_id = kong_service.nikita_lambda_account_stream_producer.id
}

resource "kong_plugin" "nikita_lambda_account_stream_producer_trigger" {
  name        = "aws-lambda"
  service_id  = kong_service.nikita_lambda_account_stream_producer.id
  config_json = <<EOT
    {
      "aws_region": "${var.region}",
      "function_name": "nikita_lambda_account_stream_producer"
    }
EOT
}

resource "kong_plugin" "nikita_lambda_account_stream_producer_auth" {
  name        = "key-auth"
  service_id  = kong_service.nikita_lambda_account_stream_producer.id
  config_json = <<EOT
    {
      "key_names": ["apikey"],
      "hide_credentials": true
    }
EOT
}

resource "kong_plugin" "nikita_lambda_account_stream_producer_acl" {
  name        = "acl"
  service_id  = kong_service.nikita_lambda_account_stream_producer.id
  config_json = <<EOT
    {
      "whitelist": ["nikita_lambda_account_stream_producer"]
    }
EOT
}

########################################################################################################################
# Transaction Stream Producer
########################################################################################################################
resource "kong_service" "nikita_lambda_transaction_stream_producer" {
  name     = "nikita_lambda_transaction_stream_producer"
  protocol = "http"
  host     = "localhost"
  port     = 8000
  path     = "/"
}

resource "kong_route" "nikita_lambda_transaction_stream_producer" {
  name       = "nikita_lambda_transaction_stream_producer"
  protocols  = ["http", "https"]
  paths      = ["/callback/evergent/transaction"]
  methods    = ["POST"]
  service_id = kong_service.nikita_lambda_transaction_stream_producer.id
}

resource "kong_plugin" "nikita_lambda_transaction_stream_producer_trigger" {
  name        = "aws-lambda"
  service_id  = kong_service.nikita_lambda_transaction_stream_producer.id
  config_json = <<EOT
    {
      "aws_region": "${var.region}",
      "function_name": "nikita_lambda_transaction_stream_producer"
    }
EOT
}

resource "kong_plugin" "nikita_lambda_transaction_stream_producer_auth" {
  name        = "key-auth"
  service_id  = kong_service.nikita_lambda_transaction_stream_producer.id
  config_json = <<EOT
    {
      "key_names": ["apikey"],
      "hide_credentials": true
    }
EOT
}

resource "kong_plugin" "nikita_lambda_transaction_stream_producer_acl" {
  name        = "acl"
  service_id  = kong_service.nikita_lambda_transaction_stream_producer.id
  config_json = <<EOT
    {
      "whitelist": ["nikita_lambda_transaction_stream_producer"]
    }
EOT
}
