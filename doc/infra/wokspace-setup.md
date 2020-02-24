# Setting up workspace

1. Get terraform cloud key from Cloud Engineering team
2. Create `~/.terraformrc` file

   ```plaintext
   credentials "app.terraform.io" {
     token = "my-secret-api-key"
   }
   ```
   
3. Use terraform to create workspace

   ```
   $ cd myproject
   $ terraform init
   $ terraform workspace new my-workspace-name
   ```

4. Go to terraform cloud workspace-config page `https://app.terraform.io/app/Nikita/workspaces/${MY_WORKSPACE}/settings/general` and set **Execution Mode** to local
  
    ![Terraform Workspace General Settings Page](terraform-cloud-workspace-general-setting-local-execution.png)
