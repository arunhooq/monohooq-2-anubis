DOCKER_HOST 		:= quay.io
DOCKER_IMAGE 		:= quay.io/hooq/discover
DOCKER_USERNAME		:= hooq+techacc

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
	echo ${GITHUB_WORKSPACE}
	docker build -f ${GITHUB_WORKSPACE}/discover/docker/discover/Dockerfile -t $(DOCKER_IMAGE):$(APP_TAG) ${GITHUB_WORKSPACE}/discover/.

.PHONY: docker-push
docker-push: docker-build
	docker push $(DOCKER_IMAGE):$(APP_TAG)
