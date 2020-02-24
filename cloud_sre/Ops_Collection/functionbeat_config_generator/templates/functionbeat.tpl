functionbeat.provider.aws.endpoint: "s3.amazonaws.com"
functionbeat.provider.aws.deploy_bucket: "{{ S3_BUCKET }}"
functionbeat.provider.aws.functions:
  - name: {{ SERVICE_NAME }}-{{ ENVIRONMENT }}-cloudwatch
    enabled: true
    type: cloudwatch_logs
    description: "lambda function for cloudwatch logs"
    triggers:
      - log_group_name: {{LOG_GROUP }}

cloud.id: "HOOQ-ELASTIC:{{ CLOUD_ID }}"
cloud.auth: "{{ USERNAME }}:{{ PASSWORD }}"

setup:
  template:
    enabled: true
    name: "hooq-{{ ENVIRONMENT }}-app-{{ SERVICE_NAME }}-logs-template"
    pattern: "hooq-{{ ENVIRONMENT }}-app-{{ SERVICE_NAME }}-logs-*"
    settings:
      index.number_of_shards: 2
      index.number_of_replicas: 1
  ilm:
    enabled: true
    rollover_alias: "hooq-{{ ENVIRONMENT }}-app-{{ SERVICE_NAME }}-logs"
    pattern: "{now/d}-000001"
    policy_name: "{{ POLICY_NAME }}"

output.elasticsearch.index: "hooq-{{ ENVIRONMENT }}-app-{{ SERVICE_NAME }}-logs-%{+yyyy.MM.dd}"

tags: {{ TAGS }}

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