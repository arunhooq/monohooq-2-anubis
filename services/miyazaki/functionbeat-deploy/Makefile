PLAYBOOK_FILE	:= functionbeat.yml
PLAYBOOK_LOCAL_PASSWORD_FILE ?= $(HOME)/.vault_pass.txt


# check for variables needed for ansible to run
.PHONY: ansible-apply-check
ansible-apply-check:
ifndef ENVIRONMENT
	$(error ENVIRONMENT is undefined)
endif

ifndef ANSIBLE_VAULT_PASSWORD
	$(error ANSIBLE_VAULT_PASSWORD is undefined)
endif

ifndef AWS_ACCESS_KEY_ID
	$(error AWS_ACCESS_KEY_ID is undefined)
endif

ifndef AWS_SECRET_ACCESS_KEY
	$(error AWS_SECRET_ACCESS_KEY is undefined)
endif

ifndef FUNCTIONBEAT_VERSION
	$(error FUNCTIONBEAT_VERSION is undefined)
endif

ifndef SERVICE
	$(error SERVICE is undefined)
endif


vault_env:
	printf '%s\n' '#!/bin/bash' 'echo $$ANSIBLE_VAULT_PASSWORD' > $@
	chmod +x $@

.PHONY: ansible-apply
ansible-apply: ansible-apply-check vault_env
	ansible-playbook $(PLAYBOOK_FILE) -vv -e env="${ENVIRONMENT}" -e svc="${SERVICE}" -e functionbeat_version="${FUNCTIONBEAT_VERSION}" --vault-password-file=vault_env
