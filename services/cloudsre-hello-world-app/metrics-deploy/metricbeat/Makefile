TERRAFORM		:= /usr/bin/terraform
PLAYBOOK_FILE	:= metricbeat.yml
PLAYBOOK_LOCAL_PASSWORD_FILE ?= $(HOME)/.vault_pass.txt

# check for environment variables to replace tokens
.PHONY: terraform-setup-check
terraform-setup-check:
ifndef TF_TOKEN_CLOUD_SRE
	$(error TF_TOKEN_CLOUD_SRE is undefined)
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


.PHONY: terraform-setup
terraform-setup: terraform-setup-check
	sed -i -e "s/TF_TOKEN_CLOUD_SRE/${TF_TOKEN_CLOUD_SRE}/g" *.tf

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
ansible-apply: ansible-apply-check vault_env
	ansible-playbook $(PLAYBOOK_FILE) -v -e env="${ENVIRONMENT}" --vault-password-file=vault_env