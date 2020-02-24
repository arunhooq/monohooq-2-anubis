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
test: format lint
	$(GO) test -v $(GODIRS)

.PHONY: install-golint
install-golint:
	test -x $(GOLINT) || $(GO) get -u golang.org/x/lint/golint
