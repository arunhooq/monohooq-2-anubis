TERRAFORM	:= $(shell which terraform)

# check for environment variables to replace tokens
.PHONY: terraform-setup-check
terraform-setup-check:
ifndef TF_TOKEN_NIKITA
	$(error TF_TOKEN_NIKITA is undefined)
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

.PHONY: terraform-install
terraform-install:
	make -C ../../../../monohooq $@

.PHONY: terraform-install-kong-plugin
terraform-install-kong-plugin:
	make -C ../../../../monohooq $@

.PHONY: terraform-setup
terraform-setup: terraform-setup-check set-kong-apikey-hooqinternal-nikita-api set-kong-apikey-evergent-default
	sed -i -e "s/SED_TF_TOKEN_NIKITA/${TF_TOKEN_NIKITA}/g" *.tf
	sed -i -e "s/SED_KONG_APIKEY_HOOQINTERNAL_NIKITA_API/${KONG_APIKEY_HOOQINTERNAL_NIKITA_API}/g" *.tf
	sed -i -e "s/SED_KONG_APIKEY_EVERGENT_DEFAULT/${KONG_APIKEY_EVERGENT_DEFAULT}/g" *.tf

.PHONY: terraform-init
terraform-init: terraform-setup
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

.PHONY: set-kong-apikey-hooqinternal-nikita-api
set-kong-apikey-hooqinternal-nikita-api: vault-view-check vault_env
	$(eval KONG_APIKEY_HOOQINTERNAL_NIKITA_API := $(shell ansible-vault view ../config-deploy/vars/${ENVIRONMENT}/secrets.yml \
	                           	     --vault-password-file vault_env | \
	                                 awk '/KONG_APIKEY_HOOQINTERNAL_NIKITA_API/,/Value/' | \
	                                 grep Value | sed -E 's/^.*: "(.+)"/\1/'))

.PHONY: set-kong-apikey-evergent-default
set-kong-apikey-evergent-default: vault-view-check vault_env
	$(eval KONG_APIKEY_EVERGENT_DEFAULT := $(shell ansible-vault view ../config-deploy/vars/${ENVIRONMENT}/secrets.yml \
									 --vault-password-file vault_env | \
									 awk '/KONG_APIKEY_EVERGENT_DEFAULT/,/Value/' | \
									 grep Value | sed -E 's/^.*: "(.+)"/\1/'))

vault_env:
	printf '%s\n' '#!/bin/bash' 'echo $$ANSIBLE_VAULT_PASSWORD' > $@
	chmod +x $@
