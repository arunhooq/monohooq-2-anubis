resource "aws_route53_resolver_endpoint" "route53_inbound" {
  name      = var.inbound_name
  direction = "INBOUND"

security_group_ids = var.sec_group

dynamic "ip_address" {
 for_each = var.inbound_ip_address
 iterator = subnet_id
  content {
      subnet_id = subnet_id.value
  }
}

}