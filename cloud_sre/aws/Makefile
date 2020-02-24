TERRAFORM		:= /usr/bin/terraform
PLAYBOOK_FILE := playbook.yml

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
ifndef PRIVATE_REGISTRY_TOKEN
	$(error PRIVATE_REGISTRY_TOKEN is undefined)
endif


.PHONY: ansible-apply-check
ansible-apply-check:
ifndef ENVIRONMENT
	$(error ENVIRONMENT is undefined)
endif


.PHONY: terraform-setup
terraform-setup: terraform-setup-check
	sed -i -e "s/TF_TOKEN_CLOUD_SRE/${TF_TOKEN_CLOUD_SRE}/g" *.tf && \
    sed -i -e "s/PRIVATE_REGISTRY_TOKEN/${PRIVATE_REGISTRY_TOKEN}/g" *.tf

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
	ansible-playbook $(PLAYBOOK_FILE) -v -e env="${ENVIRONMENT}"