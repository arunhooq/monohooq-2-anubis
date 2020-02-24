APP_TAG			?= $(shell git rev-parse HEAD | cut -c1-7)
GO				:= $(shell which go)
GOFMT			:= $(shell which gofmt)
GOPATH			:= $(shell $(GO) env GOPATH)
GOLINT			:= $(GOPATH)/bin/golint
GOFILES			:= $(wildcard */*.go)
GODIRS 			:= $(addsuffix ...,$(addprefix ./,$(sort $(dir $(GOFILES)))))
BUILD_DIR		:= build
SRC_DIR			:= src
BINARY			:= lambda_account_stream_consumer
JFROG_BASE_URL	?= "https://hooq.jfrog.io/hooq/example-repo-local/nikita/lambda/account_stream_consumer"
VERSIONING_FILE	:= ../../../../../modules/go/constant/sed_values.go

.DEFAULT_GOAL := build

.PHONY: clean
clean:
	rm -rf $(BUILD_DIR)/$(BINARY) $(BUILD_DIR)/$(BINARY).zip

.PHONY: build
build: clean lint test
	sed -i -e "s/SED_APP_TAG/$(APP_TAG)/g" $(VERSIONING_FILE)
	cd $(SRC_DIR) && $(GO) build -o ../$(BUILD_DIR)/$(BINARY)
	cd $(BUILD_DIR) && zip -j $(BINARY).zip $(BINARY)

.PHONY: format
format:
	$(GOFMT) -w $(GOFILES)
	make -C terraform $@

.PHONY: lint
lint:
	test -f $(GOLINT) || $(GO) get -u golang.org/x/lint/golint
	$(GOLINT) $(GODIRS)

.PHONY: test
test:
	$(GO) test -v $(GODIRS)

.PHONY: upload-download-check
upload-download-check:
ifndef JFROG_API_KEY
	$(error JFROG_API_KEY is undefined)
endif

.PHONY: upload
upload: upload-download-check build
	curl -H "X-JFrog-Art-Api:${JFROG_API_KEY}" -X PUT "$(JFROG_BASE_URL)/$(BINARY)-${APP_TAG}.zip" -T $(BUILD_DIR)/$(BINARY).zip

.PHONY: download
download: upload-download-check clean
	curl -H "X-JFrog-Art-Api:${JFROG_API_KEY}" -X GET "$(JFROG_BASE_URL)/$(BINARY)-${APP_TAG}.zip" -o $(BUILD_DIR)/$(BINARY).zip

.PHONY: terraform-apply terraform-destroy
terraform-apply terraform-destroy:
	make -C terraform $@

.PHONY: terraform-install git-setup
terraform-install git-setup:
	make -C ../../../../../../monohooq $@
