[
  {
    "volumesFrom": [],
    "memory": null,
    "extraHosts": null,
    "linuxParameters": null,
    "dnsServers": null,
    "disableNetworking": null,
    "dnsSearchDomains": null,
    "portMappings": [
      {
        "hostPort": ${host_port},
        "containerPort": ${container_port},
        "protocol": "tcp"
      }
    ],
    "hostname": null,
    "essential": true,
    "entryPoint": [],
    "name": "${container_name}",
    "ulimits": [
      {
        "softLimit": 10000,
        "hardLimit": 30000,
        "name": "nofile"
      }
    ],
    "repositoryCredentials": {
      "credentialsParameter": "${github_package_credentials_arn}"
    },
    "dockerSecurityOptions": null,
    "environment": [],
    "links": null,
    "workingDirectory": null,
    "secrets": {{TAGS}} ,
    "readonlyRootFilesystem": null,
    "image": "${docker_image}:${docker_tag}",
    "command": [],
    "user": null,
    "dockerLabels": null,
    "logConfiguration": {
      "logDriver": "awslogs",
      "options": {
        "awslogs-group": "${awslogs_group}",
        "awslogs-region": "${region}",
        "awslogs-stream-prefix": "${container_name}"
      }
    },
    "cpu": 100,
    "privileged": null,
    "memoryReservation": 500
  }
]