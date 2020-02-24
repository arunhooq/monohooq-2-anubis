# Config Converter

## Purposes

1. To convert and merge existing service environment file across all environment into parameters.tf, variables.tf, and task-definitions.json.
1. The parameters.tf used for store the environment variable to AWS parameter store.
1. The variables.tf used for define the value of environment variable, developer should edit this based on application environment.
1. The task-definitions.json file used for creating the ecs service based on environment variable that stored in AWS parameter store.

## Usage

format: 

`[script_file_location] [environment_name] [existing_env_file_location] [service_name]`

Example:

`~/monohooq/cloud_sre/Ops_Collection/config_converter/converter.py prod ~/monohooq/services/miyazaki/code-deploy/src/deploy/production/dotenv miyazaki`

## Things to note

1. This converter only applied one time only
1. After succesfully convert all env file on each environment, you should delete the config.json file.
1. Developer should edit the variables.tf to define application environment variable value later on if there's any value changes. 
1. Developer should trigger the parameter store pipeline on github action in order to update the environment variable values.