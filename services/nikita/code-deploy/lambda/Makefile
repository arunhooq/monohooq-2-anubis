APP_TAG		?= $(shell git rev-parse HEAD | cut -c1-7)
GIT_BRANCH	:= $(shell git rev-parse --abbrev-ref HEAD)
PATH_PREFIX	:= services/nikita/code-deploy/lambda

# append lambda functions here
LAMBDAS := account_stream_consumer
LAMBDAS += account_stream_producer
LAMBDAS += transaction_stream_consumer
LAMBDAS += transaction_stream_producer

.DEFAULT_TARGET: build

.PHONY: clean format lint test build upload download terraform-apply terraform-destroy terraform-install git-setup
clean format lint test build upload download terraform-apply terraform-destroy terraform-install git-setup:
	for i in $(LAMBDAS); do make -C $$i $@; done

.PHONY: auto-clean auto-format auto-lint auto-test auto-build auto-upload auto-terraform-apply auto-terraform-destroy
auto-clean auto-format auto-lint auto-test auto-build auto-upload auto-terraform-apply auto-terraform-destroy: git-fetch-master
	$(eval AFFECTED_FILES	:= $(shell git diff-tree --no-commit-id --name-only -r $(APP_TAG) origin/master))
	$(eval AFFECTED_DIRS	:= $(filter $(PATH_PREFIX)/%,$(dir $(AFFECTED_FILES))))
	$(eval AFFECTED_LAMBDAS	:= $(sort $(patsubst %/src/,%,$(patsubst %/terraform/,%,$(patsubst $(PATH_PREFIX)/%,%,$(AFFECTED_DIRS))))))
	$(eval TARGET		:= $(patsubst auto-%,%,$@))
	@for i in $(AFFECTED_LAMBDAS); do make -C $$i $(TARGET); done

.PHONY: git-fetch-master
git-fetch-master:
	git fetch origin master:master
