# Config Deploy

We usually need to decouple an application image with its configuration. For example in monohooq, it is common to configure a running application using environment variables to provide infrastructure-related variables like database credentials, which are different across all 4 environments. `config-deploy` is used to configure such variables.

## Setup

1. Files to copy-paste (if copying from nikita):
```
.github/workflows/nikita-config-deploy-{env}.tf
services/nikita/config-deploy
```

Update the copy content with your service name and other things that you deem necessary.

2. Install ansible.
```sh
pip3 install ansible
```

3. Ask your team for the shared ansible secret. If there is none, generate a new one. Save the secret in `$HOME/.vault_pass.txt`.
```sh
# Generate a new secret and save it in $HOME/.vault_pass.txt
openssl rand -hex 32 > $HOME/.vault_pass.txt
```

## Add Non-Secret variable

1. Configure the variable in `config-deploy/variables.tf`.
```hcl
variable "my_new_var" {
  description = "My brand new variable"
  default     = "awdawdawd"
}
```

2. Add the variable in `config-deploy/parameters.tf`, in the parameterstore module.

```hcl
module "your_service_parameters" {
  source = "../../../modules/terraform/aws/parameterstore"
  tags   = local.tags

  parameter_write = [
    ...
    {
      description = "My brand new variable",
      name        = "/app/nikita/MY_NEW_VAR",
      overwrite   = "true",
      type        = "String",
      value       = var.MY_NEW_VAR
    },
  ]
}
```

## Add Non-Secret variables from another terraform state

1. Add the remote state data in `config-deploy/parameters.tf`.

```hcl
data "terraform_remote_state" "an_external_infra_state" {
  backend = "remote"
  config = {
    organization = "nikita"
    token        = "TF_TOKEN_EXTERNAL_INFRA"
    workspaces = {
      name = "external-infra-${terraform.workspace}"
    }
  }
}
```

2. Add one of the remote state outputs in `config-deploy/parameters.tf`, in the parameterstore module.

```hcl
module "your_service_parameters" {
  source = "../../../modules/terraform/aws/parameterstore"
  tags   = local.tags

  parameter_write = [
    ...
    {
      description = "My brand new variable from an external state",
      name        = "/app/nikita/MY_NEW_VAR_FROM_EXTERNAL_STATE",
      overwrite   = "true",
      type        = "String",
      value       = data.terraform_remote_state.an_external_infra_state.outputs.an_output_variable
    },
  ]
}
```

## Add a secret variable

1. Edit the encrypted secret yaml and add a new secret.

```sh
ansible-vault edit config-deploy/vars/play/secrets.yml --vault-password-file $HOME/.vault_pass.txt
```

OR if you'd like to create a new encrypted secret files:

```sh
# Write a new yaml
vim config-deploy/vars/play/my-new-secret.yml

# Encrypt the yaml
ansible-vault encrypt config-deploy/vars/play/secrets.yml --vault-password-file $HOME/.vault_pass.txt
```
