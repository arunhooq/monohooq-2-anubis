APP_TAG			?= $(shell git rev-parse HEAD | cut -c1-7)
DOCKER_HOST 	:= quay.io
DOCKER_IMAGE 	:= quay.io/hooq/nikita-api
DOCKER_USERNAME	:= hooq+techacc

# path and binaries
GO		:= $(shell which go)
GOFMT		:= $(shell which gofmt)
GOPATH		:= $(shell $(GO) env GOPATH)
GOLINT		:= $(GOPATH)/bin/golint

# source files and directories
GOFILES		:= $(wildcard */*.go)
GOFILES		+= $(wildcard */*/*.go)
GODIRS 		:= $(addsuffix ...,$(addprefix ./,$(sort $(dir $(GOFILES)))))

.PHONY: format
format:
	$(GOFMT) -w $(GOFILES)

.PHONY: lint
lint: install-golint
	$(GOLINT) $(GODIRS)

.PHONY: test
test:
	$(GO) test -v $(GODIRS)

.PHONY: install-golint
install-golint:
	test -x $(GOLINT) || $(GO) get -u golang.org/x/lint/golint

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
docker-build: build docker-login
	docker build -t $(DOCKER_IMAGE):$(APP_TAG) .

.PHONY: docker-push
docker-push: docker-build
	docker push $(DOCKER_IMAGE):$(APP_TAG)

.PHONY: build
build:
	go build -o ./build/nikita-api  ./cmd/nikita-api/.

.PHONY: terraform-install terraform-apply terraform-destroy
terraform-install terraform-apply terraform-destroy:
	make -C terraform $@

.PHONY: git-setup
git-setup:
	make -C ../../../../../monohooq $@
