variable "availability-zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env" {
  default = {
    dev = {
      name = "anubis_dev"
    }
    stag = {
      name = "anubis_stag"
    }

    play = {
      name = "anubis_play"
    }
  }
}

variable "min_capacity" {
  default = "0"
}

variable "max_capacity" {
  default = "2"
}

variable "instance_volume_path" {
  default = "/var/log/anubis"
}

variable "volume_name" {
  default = "anubis-log"
}

variable "container_port" {
  default = "4352"
}

variable "container_mount_path" {
  default = "/usr/local/anubis/logs"
}

variable "service-name" {
  description = "Tag: Service Name for all resources"
  default     = "anubis"
}

variable "region" {
  default     = "ap-southeast-1"
}

variable "docker_image" {
  default     = "quay.io/hooq/anubis"
}

variable "host_port" {
  default = "0"
}


variable "docker_tag" {
  default     = "APP_TAG"
}


variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "anubis"
}

variable "tag" {
  default = "GITHUBSHA"
}
