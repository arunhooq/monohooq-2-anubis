
######################################################  VARIABLES  #####################################################

variable "custom_policy_actions" {
  type = "map"
  default = {
      "kms" = [ "kms:Decrypt" ]
  }
}

variable "custom_policy_resources" {
type = "map"
default = {
            "kms" = ["*"]
  }
}


variable "role" {
  default = {
     play = {
       name              = ["hooq-play-full-access","hooq-play-limited-read-write-access"]
    }
     dev = {
       name              = ["hooq-dev-full-access"]
     }
     stag = {
       name              = ["hooq-stag-full-access"]
     }
    prod = {
       name              = ["hooq-prod-full-access"]
     }
   }
 }

#######################################################  DATA  #####################################################


# Custom Policy Document for creating json
data "aws_iam_policy_document" "custom_policy" {
  statement {
    sid = "1"
    actions = "${var.custom_policy_actions["kms"]}"
    effect    = "Deny"
    resources = "${var.custom_policy_resources["kms"]}"
  }
}

######################################################  RESOURCES  #####################################################

module "ssm_decrypt_secret_custom_policy" {
  source = "../../modules/terraform/aws/iam/role_policy/policy/custom"
  role_name  =  var.role[terraform.workspace].name
  iam_custom_role_policy_data = data.aws_iam_policy_document.custom_policy.json
  iam_custom_policy_name = "ssm-decrypt-secret-policy"
}

#######################################################  OUTPUTS #######################################################
output "custom_policy_name" {
  value = module.ssm_decrypt_secret_custom_policy.custom_policy_name
}
