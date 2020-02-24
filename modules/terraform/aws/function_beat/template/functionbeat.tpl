functionbeat.provider.aws.endpoint: "s3.amazonaws.com"
functionbeat.provider.aws.deploy_bucket: "${ function-s3bucket }"
functionbeat.provider.aws.functions:
  - name: ${ function-service-name }-${ function-environment }-cloudwatch
    enabled: true
    type: cloudwatch_logs
    description: "lambda function for cloudwatch logs"
    triggers:
      - log_group_name: ${ function-log-group }

cloud.id: "HOOQ-ELASTIC:${ function-cloudid }"
cloud.auth: "${ function-elastic-username }:${ function-elastic-password }"

setup:
  template:
    enabled: true
    name: "hooq-${ function-environment }-app-${ function-service-name }-logs-template"
    pattern: "hooq-${ function-environment }-app-${ function-service-name }-logs-*"
    settings:
      index.number_of_shards: 2
      index.number_of_replicas: 1
  ilm:
    enabled: true
    rollover_alias: "hooq-${ function-environment }-app-${ function-service-name }-logs"
    pattern: "{now/d}-000001"
    policy_name: "${ function-policy-name }"

output.elasticsearch.index: "hooq-${ function-environment }-app-${ function-service-name }-logs-${ function-format }"

tags: ${ function-tags }

queue:
  mem:
    events: 4096
    flush.min_events: 2048
    flush.timeout: 100ms

processors:
 - decode_json_fields:
     fields: ["message"]
     max_depth: 3
     target: ""
     overwrite_keys: true