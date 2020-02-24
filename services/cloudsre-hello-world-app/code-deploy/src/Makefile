DOCKER_HOST 		:= quay.io
DOCKER_IMAGE 		:= quay.io/hooq/cloudsre-helloworld
DOCKER_USERNAME	:= hooq+techacc

#####
.PHONY: git-setup
git-setup:
	make -C ../../../../../monohooq $@


# check for environment variables to login to docker
.PHONY: docker-login-check
docker-login-check: git-setup
ifndef DOCKER_TOKEN
	$(error DOCKER_TOKEN is undefined)
endif

.PHONY: docker-login
docker-login: docker-login-check
	docker login $(DOCKER_HOST) --username $(DOCKER_USERNAME) -p $(DOCKER_TOKEN) 2>/dev/null

.PHONY: docker-build
docker-build: docker-login
	docker build -t $(DOCKER_IMAGE):$(APP_TAG) --build-arg SSH_PRV_KEY="$$(cat ~/.ssh/id_rsa)" --build-arg SSH_PUB_KEY="$$(cat ~/.ssh/id_rsa.pub)"  .

.PHONY: docker-pull
docker-pull: docker-login-check
	docker pull $(DOCKER_IMAGE):$(BRANCH_TAG)


.PHONY: docker-retag-push
	retag-docker-image: docker-pull
	docker tag $(DOCKER_IMAGE):$(BRANCH_TAG) $(DOCKER_IMAGE):$(MASTER_TAG)
	docker rmi $(DOCKER_IMAGE):$(BRANCH_TAG)
	docker push $(DOCKER_IMAGE):$(MASTER_TAG)

.PHONY: docker-push
docker-push: docker-build
	docker push $(DOCKER_IMAGE):$(APP_TAG)
