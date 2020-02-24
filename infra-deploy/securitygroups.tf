######################################################  RESOURCES  #####################################################



##################################################################################
# AUTOSCALINGGROUP(ASG) SECURITY-GROUP AND ITS SECURITY GROUP RULES
##################################################################################

module "autoscalinggroup_sg" {
  source       = "../../modules/terraform/aws/security_groups"
  vpc_id       = module.vpc.vpc_id
  name         = "${local.name}-autoscalling-group"
  tags         = local.tags
  all_ips      = local.all_ips
  any_port     = local.any_port
  any_protocol = local.any_protocol
  tcp_protocol = local.tcp_protocol
}

resource "aws_security_group_rule" "server-ingress-self" {
  description              = "Allow servers to communicate with each other"
  from_port                = local.any_port
  protocol                 = local.any_protocol
  security_group_id        = module.autoscalinggroup_sg.sg_id
  source_security_group_id = module.autoscalinggroup_sg.sg_id
  to_port                  = local.any_port
  type                     = "ingress"
}

resource "aws_security_group_rule" "allow-asg-https" {
  description       = "Allow https inside vpc"
  from_port         = local.https_port
  protocol          = local.tcp_protocol
  security_group_id = module.autoscalinggroup_sg.sg_id
  cidr_blocks       = ["${local.cidr}"]
  to_port           = local.https_port
  type              = "ingress"
}

resource "aws_security_group_rule" "allow-asg-http" {
  description       = "Allow http inside vpc"
  from_port         = local.http_port
  protocol          = local.tcp_protocol
  security_group_id = module.autoscalinggroup_sg.sg_id
  cidr_blocks       = ["${local.cidr}"]
  to_port           = local.http_port
  type              = "ingress"
}


#######################################################  OUTPUTS #######################################################

output "autoscalinggroup_sg_id" {
  value = module.autoscalinggroup_sg.sg_id
}
