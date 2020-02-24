# Config Deploy

Infra variables consumed by our application should be maintained in SSM. config-deploy is used to write SSM variables.

## List of SSM variables

Please check [parameters.tf](parameters.tf)

## How to add new variable - from hard-coded value

1. In [variables.tf](variables.tf), add new key-value under "env" variable for each environment.

```hcl
variable "env" {
  default = {
    play = {
      ...
      YOUR_NEW_KEY = "YOUR_NEW_VALUE",
    },
    dev = {
      ...
      YOUR_NEW_KEY = "YOUR_NEW_VALUE",
    },
    stag = {
      ...
      YOUR_NEW_KEY = "YOUR_NEW_VALUE",
    },
    prod = {
      ...
      YOUR_NEW_KEY = "YOUR_NEW_VALUE",
    },
  }
}
```

2. In [parameters.tf](parameters.tf), add a new item in `parameter_write` array.

```
parameter_write = [
  ...
  {
    description = "Describe your new key here",
    name        = "/app/your_service_name/${terraform.workspace}/YOUR_NEW_KEY",
    overwrite   = "true",
    type        = "String",
    value       = var.env[terraform.workspace].YOUR_NEW_KEY
  },
]
```

## How to add new variable - from terraform state

1. In [parameters.tf](parameters.tf), add a new data from remote state:

```
data "terraform_remote_state" "from_somewhere_infra_state" {
  backend = "remote"
  config = {
    // The remote state config
  }
}
```

2. In [parameters.tf](parameters.tf), add a new item in `parameter_write` array.

```
parameter_write = [
  ...
  {
    description = "Describe your new key here",
    name        = "/app/your_service_name/${terraform.workspace}/YOUR_NEW_KEY",
    overwrite   = "true",
    type        = "String",
    value       = data.terraform_remote_state.from_somewhere_infra_state.outputs.the_variable_name
  },
]
```

## Adding new variable - secret

TBC. We are going to use ansible vault.
