## Ansible playbook to configure elasticsearch roles,index templates and to run functionbeat
Install ansible to your local(or) any CI paltform(docker or any) using `pip install ansible`

Encrypt elasticsearch passwords and sensitive data.

```
echo -n XXXXXXXX | ansible-vault --vault-password-file  ~/.vault_pass.txt --encrypt-vault-id default encrypt_string
Reading plaintext input from stdin. (ctrl-d to end input)
!vault |
          $ANSIBLE_VAULT;1.1;AES256
          39333031346335613361333132376432333039616662336464646639396136313037623937633262
          3965383235636664383331333339373563353038636261640a373736363937666630396538373231
          38613338346231326439636363613463353866633632616430613762376431303462363634633030
          6534396138623865610a663035326530653361323731363534346563636264343136633539646166
          32333562306435623635623266316639386562323565353235643436653162613632
Encryption successful
```

Before running the schema makesure choose the correct elastic stack cluster: `HOOQ-ELASTIC` or `hooq-elastic-stack`

### To configure kibana space

Run ansible playbook using tags `configspaces`

```

ansible-playbook playbook.yml -vvv --tags configspaces,HOOQ-ELASTIC --vault-password-file ~/.vault_pass.txt 

```

### To configure elasticsearch role and role_mappings

Run ansible playbook using tags `configroles`

```

ansible-playbook playbook.yml -vvv --tags configroles,HOOQ-ELASTIC --vault-password-file ~/.vault_pass.txt 

```

### To configure elasticsearch ilm policy

Run ansible playbook using tags `configilm`

```

ansible-playbook playbook.yml -vvv --tags configilm,HOOQ-ELASTIC --vault-password-file ~/.vault_pass.txt 

```

### Run functionbeat to ingest cloudwatch logs to elasitc cloud.

Run ansible playbook using tags `functionbeat` 

For this we need, 
AWS Full access to  Cloudwatch logs, AWS Lambda functions, S3 bucket, CloudFormation Template and to Create IAM roles for Lamda functions.

Working in vars/functionbeat
```
ansible-playbook playbook.yml -vvv --tags functionbeat --vault-password-file ~/.vault_pass.txt
```

and/or

Working for specific teams vars/functionbeat/{team-name}
```
FUNCTIONBEAT_TEAM=Video ansible-playbook playbook.yml -vvv --tags functionbeat --vault-password-file ~/.vault_pass.txt

```

#### For publishing video team related logs, please follow the steps [here](./vars/functionbeat/Video/README.md)
