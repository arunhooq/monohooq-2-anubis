###################
.PHONY: git-setup
git-setup:
	make -C ../../../../../monohooq $@

.PHONY: yarn-install
yarn-install: git-setup
	yarn install

.PHONY: yarn-test
yarn-test: yarn-install
	NODE_ENV=test yarn test

.PHONY: yarn-test-client
yarn-test-client: yarn-install
	NODE_ENV=test yarn test:client

.PHONY: yarn-test-common
yarn-test-common: yarn-install
	NODE_ENV=test yarn test:common

.PHONY: yarn-test-server
yarn-test-server: yarn-install
	NODE_ENV=test yarn test:server
