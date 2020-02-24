/*output "name_of_record" {
value = aws_route53_record.domainname.records
}

output "name_of_app_domain" {
  value = aws_route53_record.domainname.name
}
*/




output "name_of_app_domain" {
  value = var.is_A_record ? concat(aws_route53_record.domainname.*.name, [""])[0] : concat(aws_route53_record.domainnameexternal.*.name, [""])[0]
}

output "name_of_record" {
  value = var.is_A_record ? aws_route53_record.domainname.*.records : aws_route53_record.domainnameexternal.*.records

}
