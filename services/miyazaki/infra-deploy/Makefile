TERRAFORM		:= /usr/bin/terraform
GITHUBSHA		:= $(shell git rev-parse HEAD | cut -c1-7)

## check for environment variables to replace tokens
.PHONY: terraform-setup-check
terraform-setup-check:
ifndef TF_TOKEN_MIYAZAKI
	$(error TF_TOKEN_MIYAZAKI is undefined)
endif

ifndef TF_TOKEN_CLOUD_SRE
	$(error TF_TOKEN_CLOUD_SRE is undefined)
endif

ifndef PRIVATE_REGISTRY_TOKEN
	$(error PRIVATE_REGISTRY_TOKEN is undefined)
endif

# check for environemt variables required by terraform
.PHONY: terraform-init-check
terraform-init-check:
ifndef AWS_ACCESS_KEY_ID
	$(error AWS_ACCESS_KEY_ID is undefined)
endif

ifndef AWS_SECRET_ACCESS_KEY
	$(error AWS_SECRET_ACCESS_KEY is undefined)
endif

# check for environemt variables required to view vault secret
.PHONY: vault-view-check
vault-view-check:
ifndef ENVIRONMENT
	$(error ENVIRONMENT is undefined)
endif

.PHONY: terraform-setup
terraform-setup: terraform-setup-check set-redis-password
	sed -i -e "s/GITHUBSHA/$(GITHUBSHA)/g" *.tf && \
	sed -i -e "s/TF_TOKEN_MIYAZAKI/${TF_TOKEN_MIYAZAKI}/g" *.tf && \
	sed -i -e "s/TF_TOKEN_CLOUD_SRE/${TF_TOKEN_CLOUD_SRE}/g" *.tf && \
	sed -i -e "s/REDIS_PASSWORD/$(REDIS_PASSWORD)/g" *.tf && \
	sed -i -e "s/PRIVATE_REGISTRY_TOKEN/${PRIVATE_REGISTRY_TOKEN}/g" *.tf && \
	sed -i -e "s@AWS_ACCEPTER_ACCESS_KEY@${AWS_ACCEPTER_ACCESS_KEY}@g" *.tf && \
  sed -i -e "s@AWS_ACCEPTER_SECRET_KEY@${AWS_ACCEPTER_SECRET_KEY}@g" *.tf && \
  sed -i -e "s@AWS_INITIATOR_ACCESS_KEY@${AWS_INITIATOR_ACCESS_KEY}@g" *.tf && \
  sed -i -e "s@AWS_INITIATOR_SECRET_KEY@${AWS_INITIATOR_SECRET_KEY}@g" *.tf


.PHONY: terraform-init
terraform-init: terraform-init-check terraform-setup
	$(TERRAFORM) init -input=false

.PHONY: terraform-plan
terraform-plan: terraform-init
	$(TERRAFORM) plan -input=false

.PHONY: terraform-apply
terraform-apply: terraform-plan
	$(TERRAFORM) apply -input=false -auto-approve

.PHONY: terraform-destroy
terraform-destroy: terraform-init
	$(TERRAFORM) destroy -input=false -auto-approve

.PHONY: set-redis-password
set-redis-password: vault-view-check vault_env
	$(eval REDIS_PASSWORD := $(shell ansible-vault view ../config-deploy/vars/${ENVIRONMENT}/secrets.yml \
	                           	     --vault-password-file vault_env | \
	                                 awk '/REDIS_PASSWORD/,/Value/' | \
	                                 grep Value | awk -F\" '{print $2}'))

vault_env:
	printf '%s\n' '#!/bin/bash' 'echo $$ANSIBLE_VAULT_PASSWORD' > $@
	chmod +x $@
