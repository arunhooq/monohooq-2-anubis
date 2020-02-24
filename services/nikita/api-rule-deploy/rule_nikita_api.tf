#
# Nikita API Services
#
resource "kong_service" "nikita_api" {
  name     = "nikita_api"
  protocol = "http"
  host     = "nikita-api.${terraform.workspace}-hooq.tv"
  port     = 80
  path     = "/"
}

# Accessing kong_service.nikita_api requires api key
resource "kong_plugin" "nikita_api_apikey" {
  name        = "key-auth"
  service_id  = kong_service.nikita_api.id
  config_json = <<EOT
    {
      "key_names": ["apikey"],
      "hide_credentials": true
    }
EOT
}

resource "kong_service" "nikita_api_admin" {
  name     = "nikita_api_admin"
  protocol = "http"
  host     = "nikita-api.${terraform.workspace}-hooq.tv"
  port     = 80
  path     = "/"
}

# Accessing kong_service.nikita_api_admin requires api key
resource "kong_plugin" "nikita_api_admin_apikey" {
  name        = "key-auth"
  service_id  = kong_service.nikita_api_admin.id
  config_json = <<EOT
    {
      "key_names": ["apikey"],
      "hide_credentials": true
    }
EOT
}

# Only allow nikita_api_admin group to access kong_service.nikita_api_admin
resource "kong_plugin" "nikita_api_admin_acl" {
  name        = "acl"
  service_id  = kong_service.nikita_api_admin.id
  config_json = <<EOT
    {
      "whitelist": ["nikita_api_admin"]
    }
EOT
}

resource "kong_service" "nikita_api_health" {
  name     = "nikita_api_health"
  protocol = "http"
  host     = "nikita-api.${terraform.workspace}-hooq.tv"
  port     = 80
  path     = "/health"
}

#
# Nikita API Routes
#
resource "kong_route" "public_3_0_subscriptions" {
  name          = "public_3_0_subscriptions"
  protocols     = ["http", "https"]
  hosts         = []
  paths         = ["/3.0/subscriptions"]
  methods       = []
  strip_path    = false
  preserve_host = false
  service_id    = kong_service.nikita_api.id
}

resource "kong_route" "admin_users" {
  name          = "admin_users"
  protocols     = ["http", "https"]
  hosts         = []
  paths         = ["/admin/users"]
  methods       = []
  strip_path    = false
  preserve_host = false
  service_id    = kong_service.nikita_api_admin.id
}

resource "kong_route" "admin_subscriptions" {
  name          = "admin_subscriptions"
  protocols     = ["http", "https"]
  hosts         = []
  paths         = ["/admin/subscriptions"]
  methods       = []
  strip_path    = false
  preserve_host = false
  service_id    = kong_service.nikita_api_admin.id
}

resource "kong_route" "health_nikita_api" {
  name          = "health_nikita_api"
  protocols     = ["http", "https"]
  hosts         = []
  paths         = ["/health/nikita/api*$"]
  methods       = ["GET"]
  strip_path    = true
  preserve_host = false
  service_id    = kong_service.nikita_api_health.id
}
