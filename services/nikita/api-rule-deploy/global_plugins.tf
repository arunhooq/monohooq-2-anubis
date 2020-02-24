resource "kong_plugin" "kong_global_request_id" {
  name        = "correlation-id"
  config_json = <<EOT
    {
      "header_name": "X-Request-ID",
      "generator" : "uuid",
      "echo_downstream": true
    }
EOT
}
