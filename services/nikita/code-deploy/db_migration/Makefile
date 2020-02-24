APP_TAG			?= $(shell git rev-parse HEAD | cut -c1-7)
DOCKER_HOST 	:= quay.io
DOCKER_IMAGE 	:= quay.io/hooq/nikita-db-migration
DOCKER_USERNAME := hooq+techacc

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

.PHONY: terraform-install terraform-apply terraform-taint-task-runner terraform-destroy
terraform-install terraform-apply terraform-taint-task-runner terraform-destroy:
	make -C terraform $@
