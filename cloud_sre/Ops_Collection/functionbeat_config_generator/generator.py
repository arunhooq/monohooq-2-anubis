#!/usr/bin/env python3
from jinja2 import Environment, FileSystemLoader
from configparser import ConfigParser
import subprocess
import glob, os, sys

# Get the config file
config_file         = sys.argv[1]
config_parser       = ConfigParser()
found_files         = config_parser.read(config_file)

# Get the value from config file
s3Bucket            = "hooq-functionbeat-" +str(os.getenv('ENV')) 
serviceName         = config_parser.get( os.getenv('ENV'), 'service_name')
environment         = os.getenv('ENV')
logGroup            = config_parser.get( os.getenv('ENV'), 'log_group')
policyName          = "hooq-prod-app-logs-ilm" if os.getenv('ENV') == "prod" else "hooq-nonprod-app-logs-ilm"
tags                = config_parser.get( os.getenv('ENV'), 'tags')

# Get from environment variable
cloudID             = os.getenv('CLOUD_ID')
elasticUsername     = os.getenv('USERNAME')
elasticPassword     = os.getenv('PASSWORD')

# Load the template
file_loader         = FileSystemLoader('templates')
env                 = Environment(loader=file_loader)
template            = env.get_template('functionbeat.tpl')

# Render the result from template
output              = template.render(
                        S3_BUCKET=s3Bucket, 
                        ENVIRONMENT=environment, 
                        SERVICE_NAME=serviceName, 
                        LOG_GROUP=logGroup, 
                        POLICY_NAME=policyName, 
                        TAGS=tags,
                        CLOUD_ID=cloudID, 
                        USERNAME=elasticUsername, 
                        PASSWORD=elasticPassword
                      )

# Save the result to file 
with open("functionbeat.yml", "w") as fh:
    fh.write(output)