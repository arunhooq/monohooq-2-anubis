#### To Publish Video Team related logs to Elastic Search

##### Pre-requisites  
#####  Ensure the right bucket is used in the hooq-elasticsearch/roles/functionbeat/vars/main.yml for the environment 

```
function_s3bucket: "video-elastic-functionbeat-<environment>"
```
 

#####  Set the access key and secret key
```
export AWS_ACCESS_KEY_ID=<access_key>
export AWS_SECRET_ACCESS_KEY=<secret_key>
```

##### For Video team related services, run the below command by replacing the environment (staging, preprod or production) 
```
FUNCTIONBEAT_TEAM=Video/<environment> ansible-playbook playbook.yml -vvv --tags functionbeat --vault-password-file ~/.vault_pass.txt
```


