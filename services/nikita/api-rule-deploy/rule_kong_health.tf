resource "kong_service" "kong_health_check" {
  name     = "kong_health_check"
  protocol = "http"
  host     = "localhost"
  port     = 8001
  path     = "/"
}

resource "kong_route" "health_kong" {
  name          = "health_kong"
  protocols     = ["http", "https"]
  hosts         = []
  paths         = ["/health/kong", "/api/health/*$"]
  methods       = ["GET"]
  strip_path    = true
  preserve_host = true
  service_id    = kong_service.kong_health_check.id
}
