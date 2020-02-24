
######################################################  RESOURCES  #####################################################


module "private_registry_cred" {
  source         =  "../../modules/terraform/aws/secretsmanager"
  user_token     =  "PRIVATE_REGISTRY_TOKEN"
  user_name      =  "hooq+techacc"
}

#######################################################  OUTPUTS #######################################################


output "private_registry_arn" {
  value = module.private_registry_cred.private_registry_arn
}
