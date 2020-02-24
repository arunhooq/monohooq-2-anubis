module "postgres_sg" {
  source       = "../../../modules/terraform/aws/security_groups"
  vpc_id       = module.vpc.vpc_id
  name         = local.postgres_db_name
  tags         = local.tags
  all_ips      = [local.cidr]
  any_port     = var.postgres_db_port
  any_protocol = var.postgres_db_security_group_protocol
  tcp_protocol = var.postgres_db_security_group_protocol
}

module "lambda_sg" {
  source       = "../../../modules/terraform/aws/security_groups"
  vpc_id       = module.vpc.vpc_id
  name         = local.lambda_sg_name
  tags         = local.tags
  all_ips      = var.all_ips
  any_port     = var.any_port
  any_protocol = var.any_protocol
  tcp_protocol = var.any_protocol
}

module "aws_security_group_rule_with_cidr" {
  source              = "../../../modules/terraform/aws/security_group_rules/cidr_range"
  ingress_rules       = local.sg_ingress_rules
  ingress_rules_count = length(local.sg_ingress_rules)
  sg_id               = module.postgres_sg.sg_id
  type                = "ingress"
}

module "aws_security_group_rule_with_source_sg_id_self" {
  source              = "../../../modules/terraform/aws/security_group_rules/source_sg_id"
  ingress_rules       = local.sg_ingress_rules
  ingress_rules_count = length(local.sg_ingress_rules)
  sg_id               = module.postgres_sg.sg_id
  source_sg_id        = module.postgres_sg.sg_id
  type                = "ingress"
}

module "alb_sg" {
  source       = "../../../modules/terraform/aws/security_groups"
  vpc_id       = module.vpc.vpc_id
  name         = "${local.name}-alb"
  tags         = local.tags
  all_ips      = var.all_ips
  any_port     = var.any_port
  any_protocol = var.any_protocol
  tcp_protocol = var.tcp_protocol
}

module "alb_sg_rules" {
  source              = "../../../modules/terraform/aws/security_group_rules/cidr_range"
  sg_id               = module.alb_sg.sg_id
  type                = "ingress"
  ingress_rules       = local.alb_sg_ingress_rules
  ingress_rules_count = length(local.alb_sg_ingress_rules)
}

module "autoscalinggroup_sg" {
  source       = "../../../modules/terraform/aws/security_groups"
  vpc_id       = module.vpc.vpc_id
  name         = "${local.name}-autoscalling-group"
  tags         = local.tags
  all_ips      = var.all_ips
  any_port     = var.any_port
  any_protocol = var.any_protocol
  tcp_protocol = var.tcp_protocol
}

module "asg_sg_rules_with_cidr" {
  source              = "../../../modules/terraform/aws/security_group_rules/cidr_range"
  ingress_rules       = local.asg_sg_ingress_rules
  ingress_rules_count = length(local.asg_sg_ingress_rules)
  sg_id               = module.autoscalinggroup_sg.sg_id
  type                = "ingress"
}

########################################################################################################################
# Outputs
########################################################################################################################
output "autoscalinggroup_sg_id" {
  value = module.autoscalinggroup_sg.sg_id
}

output "alb_sg_id" {
  value = module.alb_sg.sg_id
}

output "database_postgres_security_group_id" {
  value = module.postgres_sg.sg_id
}

output "lambda_security_group_id" {
  value = module.lambda_sg.sg_id
}
