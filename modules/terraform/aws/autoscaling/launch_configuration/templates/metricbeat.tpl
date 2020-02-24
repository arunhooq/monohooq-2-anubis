###################################### Modules configuration #######################
metricbeat.autodiscover:
  providers:
    - type: docker
      hints.enabled: true

metricbeat.modules:
#-------------------------------- System Module --------------------------------
- module: system
  metricsets:
    - cpu             # CPU usage
    - load            # CPU load averages
    - memory          # Memory usage
    - network         # Network IO
    - process         # Per process metrics
    - process_summary # Process summary
    - uptime          # System Uptime
    - socket_summary  # Socket summary
    - core           # Per CPU core usage
    - diskio         # Disk IO
    - filesystem     # File system usage for each mountpoint
    - fsstat         # File system summary metrics
    - raid           # Raid
    - socket         # Sockets and connection info (linux only)
  enabled: true
  period: 10s
  processes: ['.*']
  # Configure the metric types that are included by these metricsets.
  cpu.metrics:  ["percentages"]  # The other available options are normalized_percentages and ticks.
  core.metrics: ["percentages"]  # The other available option is ticks.
  fields:
    app: ${service}
    env: ${environment}

#-------------------------------Docker Module ----------------------------------------
- module: docker
  metricsets:
    - "container"
    - "cpu"
    - "diskio"
    - "event"
    - "healthcheck"
    - "info"
    - "image"
    - "memory"
    - "network"
  hosts: ["unix:///var/run/docker.sock"]
  period: 10s
  enabled: true
  fields:
    app: ${service}
    env: ${environment}

#------------------------------AWS Module --------------------------------------------

- module: aws
  period: 300s
  metricsets:
    - "ec2"
  access_key_id: '${accesskey}'
  secret_access_key: '${secretkey}'
  default_region: '${region}'
  enabled: true

- module: aws
  period: 300s
  metricsets:
    - cloudwatch
  metrics:
    - namespace: AWS/EBS
    - namespace: AWS/ELB
    - namespace: AWS/ApplicationELB
    - namespace: AWS/Logs
    - namespace: AWS/EC2
    - namespace: AWS/ECS
    - namespace: AWS/S3
    - namespace: AWS/AutoScaling
    - namespace: AWS/ElastiCache
    - namespace: AWS/RDS
    - namespace: AWS/Route53
  access_key_id: '${accesskey}'
  secret_access_key: '${secretkey}'
  default_region: '${region}'
  enabled: true

#================================ General =============================================================
name: HOSTNAME
fields_under_root: true

#============================= Elastic Cloud ===============================================================

cloud.id: "${elastic_cloudid}"
cloud.auth: "elastic:${elastic_password}"

#==================== Elasticsearch template setting ======================================================
setup:
  template:
    settings:
      index.number_of_shards: 2
      index.number_of_replicas: 1
      index.codec: best_compression

#========================== Setup ilm =====================================================================

setup:
  ilm:
    enabled: true
    rollover_alias: "metricbeat-${environment}"
    pattern: "{now/d}-000001"
    policy_name: "metricbeat_ilm_policy"

#================================ Elasticsearch output =====================================================
output:
  elasticsearch:
    index: "metricbeat-${environment}-${date_format}"
setup.template.name: "metricbeat-${environment}"
setup.template.pattern: "metricbeat-${environment}-*"

#================================ Logging ==================================================================
logging.to_files: true
logging.files:
  rotateeverybytes: 50485760 # = 50MB
  keepfiles: 2
  permissions: 0644
