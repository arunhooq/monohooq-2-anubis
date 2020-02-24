APP_TAG		?= $(shell git rev-parse HEAD | cut -c1-7)
TERRAFORM	:= /usr/bin/terraform

# check for environment variables to replace tokens
.PHONY: terraform-setup-check
terraform-setup-check:
ifndef TF_TOKEN_NIKITA
	$(error TF_TOKEN_NIKITA is undefined)
endif

# check for environment variables required by terraform
.PHONY: terraform-init-check
terraform-init-check:
ifndef AWS_ACCESS_KEY_ID
	$(error AWS_ACCESS_KEY_ID is undefined)
endif

ifndef AWS_SECRET_ACCESS_KEY
	$(error AWS_SECRET_ACCESS_KEY is undefined)
endif

.PHONY: terraform-setup
terraform-setup: terraform-setup-check
	sed -i -e "s/SED_APP_TAG/$(APP_TAG)/g" *.tf
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

.PHONY:format
format:
	$(TERRAFORM) fmt
