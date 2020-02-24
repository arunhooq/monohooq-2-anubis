TERRAFORM	:= /usr/bin/terraform

# check for environment variables to replace tokens
.PHONY: terraform-setup-check
terraform-setup-check:

ifndef TF_TOKEN_CLOUD_SRE
	$(error TF_TOKEN_CLOUD_SRE is undefined)
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
	echo "APP TAG is: " $(APP_TAG)
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
