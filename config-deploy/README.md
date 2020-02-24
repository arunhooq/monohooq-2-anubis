#### install ansible in local with below command
```
pip/pip3  install ansible

```

#### Get the vault-password from cloud-sre team and store in your local system in HOME directory(~) a file like below

```
 ~/.vault_pass.txt

```

#### Encrypt your variables using ansible-vault as below

```
ansibile-vault encrypt vars/play/android.yml --vault-password-file ~/.vault_pass.txt

```

#### View the encrypted variables using below command

```
ansibile-vault view vars/play/android.yml --vault-password-file ~/.vault_pass.txt

```


#### Edit the encrypted variables using below command to update environment variables

```
ansibile-vault edit vars/play/android.yml --vault-password-file ~/.vault_pass.txt

```