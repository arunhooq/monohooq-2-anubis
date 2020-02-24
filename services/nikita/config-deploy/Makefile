TERRAFORM		:= /usr/bin/terraform
ANSIBLE_PLAYBOOK := $(shell which ansible-playbook)
ANSIBLE_VAULT	:= $(shell which ansible-vault)
APP_TAG			:= $(shell git rev-parse HEAD | cut -c1-7)
PLAYBOOK_FILE	:= playbook.yml
PLAYBOOK_LOCAL_PASSWORD_FILE ?= $(HOME)/.vault_pass.txt

# check for environment variables to replace tokens
.PHONY: terraform-setup-check
terraform-setup-check:
ifndef TF_TOKEN_NIKITA
	$(error TF_TOKEN_NIKITA is undefined)
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

.PHONY: ansible-apply-check
ansible-apply-check:
ifndef ENVIRONMENT
	$(error ENVIRONMENT is undefined)
endif

ifndef ANSIBLE_VAULT_PASSWORD
	$(error ANSIBLE_VAULT_PASSWORD is undefined)
endif

.PHONY: terraform-install
terraform-install:
	make -C ../../../../monohooq $@

.PHONY: terraform-setup
terraform-setup: terraform-setup-check ansible-setup
	sed -i -e "s/SED_APP_TAG/$APP_TAG/g" *.tf
	sed -i -e "s/SED_TF_TOKEN_NIKITA/${TF_TOKEN_NIKITA}/g" *.tf

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

vault_env:
	printf '%s\n' '#!/bin/bash' 'echo $$ANSIBLE_VAULT_PASSWORD' > $@
	chmod +x $@

.PHONY: ansible-apply
ansible-apply: ansible-apply-check ansible-setup vault_env
	$(ANSIBLE_PLAYBOOK) $(PLAYBOOK_FILE) -v -e env="${ENVIRONMENT}" --vault-password-file=vault_env

.PHONY: edit-secrets-play edit-secrets-dev edit-secrets-stag edit-secrets-prod
edit-secrets-play edit-secrets-dev edit-secrets-stag edit-secrets-prod:
	$(eval ENVIRONMENT	:= $(patsubst edit-secrets-%,%,$@))
	$(ANSIBLE_VAULT) edit vars/$(ENVIRONMENT)/secrets.yml --vault-password-file $(PLAYBOOK_LOCAL_PASSWORD_FILE)

.PHONY: list-parameters
list-parameters:
	@echo "Non-secret parameters in parameters.tf"
	@cat parameters.tf | grep /app/nikita | sed -E 's/.+(\/app\/nikita[^"]+).+/  \1/' | sort
	@echo ""

.PHONY: list-parameters-secret-play list-parameters-secret-dev list-parameters-secret-stag list-parameters-secret-prod
list-parameters-secret-play list-parameters-secret-dev list-parameters-secret-stag list-parameters-secret-prod:
	$(eval ENVIRONMENT	:= $(patsubst list-parameters-secret-%,%,$@))
	@echo "Secret parameters in vars/$(ENVIRONMENT)/secrets.yml"
	@ansible-vault view vars/$(ENVIRONMENT)/secrets.yml --vault-password-file $(PLAYBOOK_LOCAL_PASSWORD_FILE) | grep /app/nikita | sed -E 's/.+(\/app\/nikita[^"]+)"/  \1/' | sort
	@echo ""

.PHONY: ansible-setup
ansible-setup:
	pip install botocore boto3
