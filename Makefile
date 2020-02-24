TF									:= $(shell which terraform)
TF_OS								?= linux
TF_ARCH							?= amd64
TF_VERSION					?= $(shell curl -s https://checkpoint-api.hashicorp.com/v1/check/terraform | jq -r -M '.current_version')
TF_PLUGIN_LOCATION 	?= $(HOME)/.terraform.d/plugins
TF_KONG_VERSION 		?= 5.0.0
SSH_PRIVATE					:= ~/.ssh/id_rsa
SSH_PUBLIC					:= ~/.ssh/id_rsa.pub

###
# Terraform makes
#
.PHONY: terraform-install
terraform-install: terraform
	sudo mv $< /usr/bin/terraform

terraform: terraform.zip
	unzip $<

# https://releases.hashicorp.com/terraform/0.12.19/terraform_0.12.19_linux_amd64.zip
terraform.zip:
	curl -L "https://releases.hashicorp.com/terraform/$(TF_VERSION)/terraform_$(TF_VERSION)_$(TF_OS)_$(TF_ARCH).zip" -o $@

.PHONY: terraform-plugin
terraform-plugin:
	mkdir -p $(TF_PLUGIN_LOCATION)

.PHONY: terraform-install-kong-plugin
terraform-install-kong-plugin: terraform-kong-plugin.zip terraform-plugin
	mv $< $(TF_PLUGIN_LOCATION)/$<
	unzip -o $(TF_PLUGIN_LOCATION)/$< -d $(TF_PLUGIN_LOCATION)

# https://github.com/kevholditch/terraform-provider-kong/releases/download/v$5.0.0/terraform-provider-kong_5.0.0_linux_amd64.zip
terraform-kong-plugin.zip:
	curl -L "https://github.com/kevholditch/terraform-provider-kong/releases/download/v$(TF_KONG_VERSION)/terraform-provider-kong_$(TF_KONG_VERSION)_$(TF_OS)_$(TF_ARCH).zip" -o $@

###
# Git makes
#
.PHONY: git-setup-check
git-setup-check:
ifndef SSH_KEY
	$(error SSH_KEY is undefined)
endif

ifndef SSH_KEY_PUB
	$(error SSH_KEY_PUB is undefined)
endif

.PHONY: git-setup
git-setup: git-setup-check
	mkdir -p ~/.ssh && \
	ssh-keyscan -t rsa github.com >> ~/.ssh/known_hosts && \
	echo "$$SSH_KEY" > $(SSH_PRIVATE) && \
	echo "$$SSH_KEY_PUB" > $(SSH_PUBLIC) && \
	chmod -R 600 $(SSH_PRIVATE) && \
	eval $$(ssh-agent) && \
	git config --global url.git@github.com:.insteadOf https://github.com/


.PHONY: git-merge-master-check
git-merge-master-check:
ifndef GITHUB_REF
	$(error GITHUB_REF is undefined)
endif

.PHONY: git-merge-master
git-merge-master: git-merge-master-check
	git config --local user.name "techacc" && \
	git config --global advice.detachedHead false && \
	git fetch --prune --unshallow origin master && \
	git checkout ${GITHUB_REF} && \
	git merge origin/master


.PHONY:validate-commit-sha-check
validate-commit-sha-check:
ifndef INPUT_COMMIT_SHA
	$(error INPUT_COMMIT_SHA is undefined)
endif

ifndef GITHUB_HEAD_COMMIT_SHA
	$(error GITHUB_HEAD_COMMIT_SHA is undefined)
endif



.PHONY: validate-commit-sha
HEAD_COMMIT_SHA := $(patsubst "%",%,$(GITHUB_HEAD_COMMIT_SHA))
validate-commit-sha:validate-commit-sha-check
ifneq ($(HEAD_COMMIT_SHA), $(INPUT_COMMIT_SHA))
	$(error $(HEAD_COMMIT_SHA) and $(INPUT_COMMIT_SHA) not match)
else
	@echo validation completed
endif


.PHONY: docker-login-check
docker-login-check: git-setup
ifndef DOCKER_TOKEN
	$(error DOCKER_TOKEN is undefined)
endif

.PHONY: docker-registry-token-check
docker-registry-token-check:
ifndef  QUAY_ACCESS_TOKEN
	$(error QUAY_ACCESS_TOKEN is undefined)
endif

.PHONY: docker-login
docker-login: docker-login-check
	docker login $(DOCKER_HOST) --username $(DOCKER_USERNAME) -p $(DOCKER_TOKEN) 2>/dev/null

.PHONY: docker-build
docker-build: docker-login
	sudo docker build --file $(DOCKER_FILE_PATH)/Dockerfile --tag $(DOCKER_IMAGE):$(APP_TAG) --build-arg SSH_PRV_KEY="$$(cat ~/.ssh/id_rsa)" --build-arg SSH_PUB_KEY="$$(cat ~/.ssh/id_rsa.pub)" $(DOCKER_FILE_PATH)

.PHONY: docker-pull
docker-pull: docker-login
	sudo docker pull $(DOCKER_IMAGE):$(BRANCH_TAG)


.PHONY: docker-retag-push
docker-retag-push: docker-pull
	sudo docker tag $(DOCKER_IMAGE):$(BRANCH_TAG) $(DOCKER_IMAGE):$(MASTER_TAG)
	sudo docker push $(DOCKER_IMAGE):$(MASTER_TAG)

.PHONY: docker-push
docker-push: docker-build
	sudo docker push $(DOCKER_IMAGE):$(APP_TAG)
