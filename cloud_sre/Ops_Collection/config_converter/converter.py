#!/usr/bin/env python3

from jinja2 import Environment, FileSystemLoader
from configparser import ConfigParser
import subprocess
import re 
import json
import glob, os, sys
from pathlib import Path

environment         = sys.argv[1]
file_path           = sys.argv[2]
service_name        = sys.argv[3]
result_path         = os.getcwd()
string_check        = re.compile('=')
data                = {}
script_directory    = str(Path(__file__).resolve().parent)
envs                = ["dev","stag","prod"]
result              = ""

####################################################################################

# Generate config.json file to get all the value from existing environment file

exists = os.path.isfile(result_path+'/config.json')

## Checking if there's existing config.json if not, creating the new one

if exists:

    with open(result_path+'/config.json') as json_file:
        data = json.load(json_file)

    with open(file_path) as fp:
      
        line= fp.readline()
        
        while line:
            if(string_check.search(line.strip()) != None): 

                key_exist = data.get(line.strip().split('=')[0], False)
                
                if key_exist == False:
                    print(line.strip().split('=')[0])
                    reference = { environment:line.strip().split('=')[1] }
                    data[line.strip().split('=')[0]] = reference
                    for x in envs:                      
                     if x != environment:
                         reference = { x:"" }
                         data[line.strip().split('=')[0]].update(reference)
                   
                else:
                    reference = { environment:line.strip().split('=')[1] }   
                    data[line.strip().split('=')[0]].update(reference)
                    
                
            line = fp.readline()
                 
    result = json.dumps(data, sort_keys=True, indent=3) 

    with open(result_path+"/config.json", "w") as fh:
        fh.write(result)

else:

    with open(file_path) as fp:
        line= fp.readline()
        
        while line:
                if(string_check.search(line.strip()) != None): 
                    reference = { environment:line.strip().split('=')[1] }
                    data[line.strip().split('=')[0]] = reference
                line = fp.readline()
                

    result = json.dumps(data, sort_keys=True, indent=3) 
                 
    with open(result_path+"/config.json", "w") as fh:
        fh.write(result)

with open(result_path+"/config.json") as json_file:
        data = json.load(json_file)

result = json.dumps(data, sort_keys=True, indent=3) 

####################################################################################

# Generate terraform paramaters.tf file

## Load the template

file_loader = FileSystemLoader(script_directory+'/templates')
env         = Environment(loader=file_loader)
template    = env.get_template('parameters.tpl')
params      = []
json_object = json.loads(result)

## print the keys and values

for key in json_object:
    value = json_object[key]
    param_type = "String"
    if "KEY" in key or  "PASS" in key:
        param_type = "SecureString"
    reference = { 
                  "name"         : "/app/"+service_name+"/"+key, 
                  "value"        : "${var.env[terraform.workspace]."+key+"}", 
                  "type"         : param_type, 
                  "overwrite"    : "true", 
                  "description"  : "environment for "+key
                }
    params.append(reference)
    
param_result = json.dumps(params, sort_keys=True, indent=4) 
param_result = re.sub(r'(?<!: )"(\S*?)"', '\\1', param_result)
param_result = param_result.replace(':', '=')
param_result = param_result.replace('http=', 'http:')
param_result = param_result.replace('https=', 'https:')

## Render the result from template

output  = template.render(
            TAGS=param_result,
            SERVICE_NAME=service_name
        )

## Save the result to parameters.tf
 
with open(result_path+"/parameters.tf", "w") as fh:
    fh.write(output)

####################################################################################

# Generate task definition file

## Load the template

template     = env.get_template('task-definitions.tpl')
params       = []
json_object  = json.loads(result)

## print the keys and values

for key in json_object:
    value = json_object[key]
    reference = { 
                  "valueFrom"   : "arn:aws:ssm:${region}:${aws_account_id}:parameter/app/"+service_name+"/"+key, 
                  "name"        : key
                }
    params.append(reference)
    

param_result = json.dumps(params, sort_keys=True, indent=8) 

## Render the result from template

output  = template.render(
            TAGS=param_result
        )

## Save the result to parameters.tf 

with open(result_path+"/hooq-"+service_name+".json", "w") as fh:
    fh.write(output)


####################################################################################

# Generate terraform variables.tf file

def check_key_or_pass(service_name,env,argument): 
    
    result = ""

    if "KEY" in argument:
        result = service_name.upper()+"_"+env.upper()+"_"+argument
    elif "PASS" in argument:
        result = service_name.upper()+"_"+env.upper()+"_"+argument

    return result, argument


## Load the template

template    = env.get_template('variables.tpl')
json_object = json.loads(result)
default_var = { "default" : {  } }

## print the keys and values

for env in envs:
    temp = { env : {  } }
    for key in json_object:
        value = json_object[key]
        
        if env in value:
           reference = { key :  "none" if value[env] == "" else value[env] }
           v, k = check_key_or_pass(service_name,env,key)
           if v != "":
            reference[key] = v
                    
           temp[env].update(reference)
           default_var["default"].update(temp)

result_var = json.dumps(default_var, sort_keys=True, indent=4) 
result_var = re.sub(r'(?<!: )"(\S*?)"', '\\1', result_var)
result_var = result_var.replace(':', '=')
result_var = result_var.replace('http=', 'http:')
result_var = result_var.replace('https=', 'https:')

## Render the result from template

output  = template.render(
            TAGS=result_var,
            SERVICE_NAME=service_name
        )

## Save the result to parameters.tf 

with open(result_path+"/variables.tf", "w") as fh:
    fh.write(output)

## Print the result

print("variables.tf, parameters.tf and hooq "+service_name+" task definition file saved at: "+os.getcwd())
