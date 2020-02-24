TERRAFORM		:= /usr/bin/terraform
PLAYBOOK_FILE	:= watcher.yml
PLAYBOOK_LOCAL_PASSWORD_FILE ?= $(HOME)/.vault_pass.txt

vault_env:
	printf '%s\n' '#!/bin/bash' 'echo $$ANSIBLE_VAULT_PASSWORD' > $@
	chmod +x $@

.PHONY: ansible-apply 
ansible-apply: vault_env
	ansible-playbook $(PLAYBOOK_FILE) -vvv  --vault-password-file=vault_env
