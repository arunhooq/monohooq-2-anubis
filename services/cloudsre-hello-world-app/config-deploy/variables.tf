variable "availability-zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env"  {
    default= {
        play= {
            APP_NAME = "hello-world-app"
        },
        dev= {

        },
        prod= {

        },
        stag= {

        }
    }
}

variable "min_capacity" {
  default = "1"
}

variable "max_capacity" {
  default = "3"
}

variable "service-name" {
  description = "Tag: Service Name for all resources"
  default     = "helloworld-app"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "helloworld-app"
}

variable "tag" {
  default = "GITHUBSHA"
}