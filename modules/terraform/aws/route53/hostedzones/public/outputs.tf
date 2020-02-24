output "domain_name" {
  value = aws_route53_zone.public_domain.*.name
}

output "hosted_zone_id" {
  value = aws_route53_zone.public_domain.*.id
}

output "first_hosted_zone_id" {
  value = concat(aws_route53_zone.public_domain.*.id, [""])[0]
}
