data "template_file" "test" {
  template = <<EOF
    echo "secret_ANSIBLE_VAULT_PASSWORD:  ANSIBLE_VAULT_PASSWORD "
      echo "secret_AWS_CC_PROD_ACCESS_KEY:  AWS_CC_PROD_ACCESS_KEY "
      echo "secret_AWS_CC_PROD_SECRET_KEY:  AWS_CC_PROD_SECRET_KEY "
      echo "secret_AWS_DEFAULT_REGION:  AWS_DEFAULT_REGION "
      echo "secret_AWS_DEV_ACCESS_KEY:  AWS_DEV_ACCESS_KEY "
      echo "secret_AWS_DEV_SECRET_KEY: AWS_DEV_SECRET_KEY "
      echo "secret_AWS_ECR_REPO: AWS_ECR_REPO "
      echo "secret_AWS_ECR_URL: AWS_ECR_URL "
      echo "secret_AWS_PEER_DEV_ACCESS_KEY: AWS_PEER_DEV_ACCESS_KEY "
      echo "secret_AWS_PEER_DEV_SECRET_KEY: AWS_PEER_DEV_SECRET_KEY "
      echo "secret_AWS_PLAY_ACCESS_KEY: AWS_PLAY_ACCESS_KEY "
      echo "secret_AWS_PLAY_SECRET_KEY: AWS_PLAY_SECRET_KEY "
      echo "secret_AWS_PROD_ACCESS_KEY: AWS_PROD_ACCESS_KEY "
      echo "secret_AWS_PROD_SECRET_KEY: AWS_PROD_SECRET_KEY "
      echo "secret_AWS_SRE_ACCESS_KEY: AWS_SRE_ACCESS_KEY "
      echo "secret_AWS_SRE_SECRET_KEY: AWS_SRE_SECRET_KEY "
      echo "secret_AWS_STAG_ACCESS_KEY: AWS_STAG_ACCESS_KEY "
      echo "secret_AWS_STAG_SECRET_KEY: AWS_STAG_SECRET_KEY "
      echo "secret_DB_PASSWORD: DB_PASSWORD "
      echo "secret_DEPLOYER_KEY_PRIV: DEPLOYER_KEY_PRIV "
      echo "secret_DEPLOYER_KEY_PUB: DEPLOYER_KEY_PUB "
      echo "secret_DOCKERGITHUBTOKEN: DOCKERGITHUBTOKEN "
      echo "secret_DOCKER_TAG_MIYAZAKI_PROD: DOCKER_TAG_MIYAZAKI_PROD"
      echo "secret_ELASTIC_CLOUDID: ELASTIC_CLOUDID "
      echo "secret_ELASTIC_CLOUD_ID: ELASTIC_CLOUD_ID "
      echo "secret_ELASTIC_CLOUD_VAULT_PASS: ELASTIC_CLOUD_VAULT_PASS "
      echo "secret_ELASTIC_PASS: ELASTIC_PASS "
      echo "secret_ELASTIC_PASSWORD: ELASTIC_PASSWORD "
      echo "secret_ELASTIC_URL: ELASTIC_URL "
      echo "secret_ELASTIC_USERNAME: ELASTIC_USERNAME "
      echo "secret_FUNCTIONBEAT_VERSION: FUNCTIONBEAT_VERSION "
      echo "secret_GITHUB_PACKAGE_TOKEN: GITHUB_PACKAGE_TOKEN "
      echo "secret_MIYAZAKI_ANSIBLE_VAULT_PASSWORD: MIYAZAKI_ANSIBLE_VAULT_PASSWORD "
      echo "secret_MIYAZAKI_DEV_KEY: MIYAZAKI_DEV_KEY "
      echo "secret_MIYAZAKI_DEV_NEWRELIC_BROWSER_LICENSE_KEY: 	MIYAZAKI_DEV_NEWRELIC_BROWSER_LICENSE_KEY "
      echo "secret_MIYAZAKI_DEV_NEWRELIC_KEY: MIYAZAKI_DEV_NEWRELIC_KEY	"
      echo "secret_MIYAZAKI_DEV_REDIS_PASSWORD: MIYAZAKI_DEV_REDIS_PASSWORD "
      echo "secret_MIYAZAKI_PLAY_REDIS_PASSWORD: MIYAZAKI_PLAY_REDIS_PASSWORD "
      echo "secret_MIYAZAKI_PROD_KEY: MIYAZAKI_PROD_KEY "
      echo "secret_MIYAZAKI_PROD_NEWRELIC_BROWSER_LICENSE_KEY: MIYAZAKI_PROD_NEWRELIC_BROWSER_LICENSE_KEY "
      echo "secret_MIYAZAKI_PROD_NEWRELIC_KEY: MIYAZAKI_PROD_NEWRELIC_KEY "
      echo "secret_MIYAZAKI_PROD_REDIS_PASSWORD: MIYAZAKI_PROD_REDIS_PASSWORD "
      echo "secret_MIYAZAKI_STAG_KEY: MIYAZAKI_STAG_KEY "
      echo "secret_MIYAZAKI_STAG_NEWRELIC_BROWSER_LICENSE_KEY: MIYAZAKI_STAG_NEWRELIC_BROWSER_LICENSE_KEY "
      echo "secret_MIYAZAKI_STAG_NEWRELIC_KEY: MIYAZAKI_STAG_NEWRELIC_KEY "
      echo "secret_MIYAZAKI_STAG_REDIS_PASSWORD: MIYAZAKI_STAG_REDIS_PASSWORD "
      echo "secret_NIKITA_ANSIBLE_VAULT_PASSWORD: NIKITA_ANSIBLE_VAULT_PASSWORD "
      echo "secret_POC_ELASTIC_CLOUDID: POC_ELASTIC_CLOUDID "
      echo "secret_POC_ELASTIC_PASSWORD: POC_ELASTIC_PASSWORD "
      echo "secret_POC_ELASTIC_URL: POC_ELASTIC_URL "
      echo "secret_QUAY_REGISTRY_TOKEN: QUAY_REGISTRY_TOKEN "
      echo "secret_QUAY_ACCESS_TOKEN: QUAY_ACCESS_TOKEN "
      echo "secret_TECHACC_TOKEN: TECHACC_TOKEN "
      echo "secret_TF_NIKITA_POSTGRES_PASSWORD: TF_NIKITA_POSTGRES_PASSWORD "
      echo "secret_TF_STABLE_VERSION: TF_STABLE_VERSION "
      echo "secret_TF_TEST_VERSION: TF_TEST_VERSION "
      echo "secret_TF_TOKEN_ANUBIS: TF_TOKEN_ANUBIS "
      echo "secret_TF_TOKEN_CLOUD_SRE: TF_TOKEN_CLOUD_SRE "
      echo "secret_TF_TOKEN_DISCOVER: TF_TOKEN_DISCOVER "
      echo "secret_TF_TOKEN_MIYAZAKI: TF_TOKEN_MIYAZAKI "
      echo "secret_TF_TOKEN_NIKITA: TF_TOKEN_NIKITA "
      echo "secret_TF_TOKEN_TEST_SERVICE: TF_TOKEN_TEST_SERVICE "
EOF
}


resource "null_resource" "local" {
  triggers = {
    template = "${data.template_file.test.rendered}"
  }

  provisioner "local-exec" {
    command = "echo \"${data.template_file.test.rendered}\""
  }
}
