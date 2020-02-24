
resource "aws_route53_zone" "public_domain" {
  count = length(var.public_hosted_zones)

  name    = "${var.public_hosted_zones[count.index]}"
  comment = "${var.comment}"

  tags = "${merge(
    map("Name", var.public_hosted_zones[count.index]),
    map("Route53Type", "domain"),
    var.tags
  )}"
}