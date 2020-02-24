resource "aws_route53_record" "domainname" {
  count = var.is_A_record ? 1 : 0
  zone_id	= var.zone_id
  name		= var.subdomainame
  type		= var.recordtype
  allow_overwrite = true
  alias {
    name =  "${var.domain_record}"
    evaluate_target_health = true
    zone_id = "${var.alb_zone_id}"

  }
}

resource "aws_route53_record" "domainnameexternal" {
  count = var.is_A_record ? 0 : length(var.domainname_record_list)
  zone_id	= var.zone_id
  name		= element(var.domainname_record_list, count.index)
  type		= var.recordtype
  ttl		= var.ttl
  allow_overwrite = true
  records	= ["${var.domain_record}"]
}
