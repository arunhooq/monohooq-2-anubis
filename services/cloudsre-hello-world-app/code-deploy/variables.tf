variable "availability_zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env" {
  default = {
    dev = {
      name = "cloudsre-hello-world-app_dev"
    }
    stag = {
      name = "cloudsre-hello-world-app_stag"
    }
    prod = {
      name = "cloudsre-hello-world-app_prod"
    }
    play = {
      name = "cloudsre-hello-world-app_play"
    }
  }
}

variable "min_capacity" {
  default = "1"
}

variable "max_capacity" {
  default = "3"
}

variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "helloworld-app"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default = "helloworld-app"
}

variable "tag" {
  default = "APP_TAG"
}
