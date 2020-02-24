APP_TAG				?= $(shell git rev-parse HEAD | cut -c1-7)
DOCKER_HOST 		:= quay.io
DOCKER_IMAGE 		:= quay.io/hooq/nikita-portal
DOCKER_USERNAME	:= hooq+techacc

# check for environment variables to login to docker
.PHONY: docker-login-check
docker-login-check:
ifndef DOCKER_TOKEN
	$(error DOCKER_TOKEN is undefined)
endif

.PHONY: docker-login
docker-login: docker-login-check
	docker login $(DOCKER_HOST) --username $(DOCKER_USERNAME) -p $(DOCKER_TOKEN) 2>/dev/null

.PHONY: docker-build
docker-build: docker-login
	docker build -t $(DOCKER_IMAGE):$(APP_TAG) .

.PHONY: docker-push
docker-push: docker-build
	docker push $(DOCKER_IMAGE):$(APP_TAG)

.PHONY: docker-remove-check
docker-remove-check:
ifndef GITHUB_REF
	$(error GITHUB_REF is undefined)
endif

ifndef QUAY_ACCESS_TOKEN
	$(error QUAY_ACCESS_TOKEN is undefined)
endif

.PHONY: docker-remove
docker-remove: docker-remove-check
	git fetch --prune --unshallow origin ${GITHUB_REF}
	$(eval APP_TAG_PREV	:= $(shell git log -n 1 --skip 1 --pretty=format:"%H" | cut -c1-7))
	-curl -X DELETE "https://quay.io/api/v1/repository/hooq/nikita-portal/tag/$(APP_TAG_PREV)" -H "Authorization: Bearer ${QUAY_ACCESS_TOKEN}"

.PHONY: terraform-install terraform-apply terraform-destroy
terraform-install terraform-apply terraform-destroy:
	make -C terraform $@

