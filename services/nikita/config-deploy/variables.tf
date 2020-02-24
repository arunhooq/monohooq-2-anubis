variable "service_name" {
  description = "Tag: Service Name for all resources"
  default     = "nikita"
}

variable "env" {
  default = {
    play = {
      // kong
      kong_baseurl = "kong.play-hooq.tv"

      // evergent
      evergent_api_baseurl  = "https://rest-stag.evergent.com"
      evergent_api_username = "qpadmin"
    },

    dev = {
      // kong
      kong_baseurl = "kong.dev-hooq.tv"

      // evergent
      evergent_api_baseurl  = "https://rest-stag.evergent.com"
      evergent_api_username = "qpadmin"
    },

    stag = {
      // kong
      kong_baseurl = "kong.stag-hooq.tv"

      // evergent
      evergent_api_baseurl  = "https://rest-stag.evergent.com"
      evergent_api_username = "qpadmin"
    }

    prod = {
      // kong
      kong_baseurl = "kong.prod-hooq.tv"

      // evergent
      evergent_api_baseurl  = "https://rest-prod.evergent.com"
      evergent_api_username = "qpadmin"
    },
  }
}

variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "nikita"
}

variable "tag" {
  default = "SED_APP_TAG"
}
