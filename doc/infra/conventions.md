# Conventions

## Infra conventions

### General

- terraform as the main infra provisioning language
- separate projects into `infra-deploy`, `code-deploy` and `config-deploy`
- four tier environments `play`, `dev`, `stag`, `prod`
- secrets are stored in github secrets
- infra generated values are stored in AWS SSM

### Terraform 

- terraform states are stored in terraform cloud
- use terraform variable for non derived values and lookup tables
- use terraform local for derived values
- do not use resource directly, instead wrap it as a module
- use snake case with underscore for objects (variable, local, output)
- use [terraform input variable](https://learn.hashicorp.com/terraform/getting-started/variables.html) for externally injected values
- workspace naming pattern
   - `{PROJECT_NAME}-infra-{ENVIRONMENT}` for `infra-deploy` terraforms
   - `{PROJECT_NAME}-{SERVICE_NAME}-{ENVIRONMENT}` for `code-deploy` terraforms 

### Workflow

- commit to feature branch triggers deployment to `play` environment
- commit to master branch triggers deployment to `dev` environment 
- matching `stag` tag pattern triggers to `stag` environment
- matching `prod` tag pattern triggers to `prod` environment
