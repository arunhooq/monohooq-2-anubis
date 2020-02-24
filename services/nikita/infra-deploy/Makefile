TERRAFORM		:= /usr/bin/terraform
APP_TAG			:= $(shell git rev-parse HEAD | cut -c1-7)

# check for environment variables to replace tokens
.PHONY: terraform-setup-check
terraform-setup-check:
ifndef TF_TOKEN_NIKITA
	$(error TF_TOKEN_NIKITA is undefined)
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

ifndef ANSIBLE_VAULT_PASSWORD
	$(error ANSIBLE_VAULT_PASSWORD is undefined)
endif

.PHONY: terraform-setup
terraform-setup: terraform-setup-check ansible-setup set-pg-db-password set-kong-db-password
	sed -i -e "s/SED_APP_TAG/$(APP_TAG)/g" *.tf
	sed -i -e "s/SED_PG_DB_PASSWORD/$(PG_DB_PASSWORD)/g" *.tf
	sed -i -e "s/SED_KONG_DB_PASSWORD/$(KONG_DB_PASSWORD)/g" *.tf
	sed -i -e "s/SED_TF_TOKEN_NIKITA/${TF_TOKEN_NIKITA}/g" *.tf
	sed -i -e "s/SED_TF_TOKEN_CLOUD_SRE/${TF_TOKEN_CLOUD_SRE}/g" *.tf
	sed -i -e "s/SED_PRIVATE_REGISTRY_TOKEN/${PRIVATE_REGISTRY_TOKEN}/g" *.tf

.PHONY: terraform-install
terraform-install:
	make -C ../../../../monohooq $@

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

.PHONY: set-pg-db-password
set-pg-db-password: vault-view-check vault_env
	$(eval PG_DB_PASSWORD := $(shell ansible-vault view ../config-deploy/vars/${ENVIRONMENT}/secrets.yml \
	                           	     --vault-password-file vault_env | \
	                                 awk '/PG_DB_PASSWORD/,/Value/' | \
	                                 grep Value | sed -E 's/^.*: "(.+)"/\1/'))

.PHONY: set-kong-db-password
set-kong-db-password: vault-view-check vault_env
	$(eval KONG_DB_PASSWORD := $(shell ansible-vault view ../config-deploy/vars/${ENVIRONMENT}/secrets.yml \
	                           	     --vault-password-file vault_env | \
	                                 awk '/KONG_DB_PASSWORD/,/Value/' | \
	                                 grep Value | sed -E 's/^.*: "(.+)"/\1/'))

vault_env:
	printf '%s\n' '#!/bin/bash' 'echo $$ANSIBLE_VAULT_PASSWORD' > $@
	chmod +x $@

.PHONY: ansible-setup
ansible-setup:
	pip install botocore boto3
