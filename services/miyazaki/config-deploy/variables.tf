variable "availability-zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env"  {
    default= {
        dev= {
            API_GATEWAY_ENV= "nightly",
            API_GATEWAY_VERSION_2_0= "2.0",
            ATTEMPT_TO_SHOW= "10",
            CHANNEL_BASE_URL= "https://cdn-discover-nightly.hooq.tv/v1.5",
            CONVIVA_CORE_SDK_PATH= "/static/conviva/conviva-core-sdk.min.js",
            CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH= "/static/conviva/conviva-html5native-impl.js",
            DEFAULT_TEST_IP= "202.138.229.73",
            DEFAULT_TEST_REGION= "ID",
            DISCOVER_BASE_URL= "https://cdn-discover-nightly.hooq.tv/v1.5",
            ENABLE_NEWRELIC= "true",
            ENV= "development",
            EVE_BASE_URL= "http://eve-nightly.hooq.vpc",
            EV_WEBVIEW_BASE_URL= "https://stag-web-hooq.evergent.com",
            INTERVAL_TO_SHOW= "3000",
            KASHMIR_BASE_URL= "http://localhost=4321/2.0",
            KASHMIR_CACHE_EXPIRY= "600",
            KASHMIR_ENABLE_CACHE= "false",
            MMDB_VERSION= "20180828",
            NEWRELIC_APP= "HOOQ-GRAB-WEBVIEW-NIGHTLY",
            NEWRELIC_BROWSER_APPLICATION_ID= "178266040",
            NEWRELIC_BROWSER_FILE_PATH= "/static/nr/nr.js",
            NEWRELIC_LEVEL= "trace",
            NODE_DEBUG= "true",
            NODE_ENV= "development",
            NODE_PORT= "4352",
            PLAY_BASE_URL= "https://api.hooq.tv",
            R21_ENABLED= "false",
            REDIS_HOST= "redis-miyazaki-dev.x0jzou.ng.0001.apse1.cache.amazonaws.com",
            REDIS_PORT= "6379",
            SANCTUARY_API_ENV= "nightly",
            SANCTUARY_API_VERSION= "2.0",
            SEARCH_BASE_URL= "https://api.hooq.tv",
            STAGING= "true",
            STAGING_DISCOVER_BASE_URL= "https://cdn-discover-nightly.hooq.tv/v1.1",
            STAGING_TITLE_ID= "ddb672cc-134f-460a-a89e-12e35eb426c7"
        },
        prod= {
            API_GATEWAY_ENV= "prod",
            API_GATEWAY_VERSION_2_0= "2.0",
            ATTEMPT_TO_SHOW= "10",
            CHANNEL_BASE_URL= "https://cdn-discover.hooq.tv/v1.5",
            CONVIVA_CORE_SDK_PATH= "/static/conviva/conviva-core-sdk.min.js",
            CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH= "/static/conviva/conviva-html5native-impl.js",
            DEFAULT_TEST_IP= "202.138.229.73",
            DEFAULT_TEST_REGION= "ID",
            DISCOVER_BASE_URL= "https://cdn-discover.hooq.tv/v1.5",
            ENABLE_NEWRELIC= "true",
            ENV= "production",
            EVE_BASE_URL= "http://eve.hooq.vpc",
            EV_WEBVIEW_BASE_URL= "https://authenticate.hooq.tv",
            INTERVAL_TO_SHOW= "3000",
            KASHMIR_BASE_URL= "http://kashmir.hooq.vpc/2.0",
            KASHMIR_CACHE_EXPIRY= "600",
            KASHMIR_ENABLE_CACHE= "true",
            MMDB_VERSION= "20180828",
            NEWRELIC_APP= "HOOQ-GRAB-WEBVIEW-PROD",
            NEWRELIC_BROWSER_APPLICATION_ID= "178266241",
            NEWRELIC_BROWSER_FILE_PATH= "/static/nr/nr.js",
            NEWRELIC_LEVEL= "info",
            NODE_DEBUG= "false",
            NODE_ENV= "production",
            NODE_PORT= "4352",
            PLAY_BASE_URL= "https://api.hooq.tv",
            R21_ENABLED= "true",
            REDIS_PORT= "6379",
            SANCTUARY_API_ENV= "prod",
            SANCTUARY_API_VERSION= "2.0",
            SEARCH_BASE_URL= "https://api.hooq.tv",
            STAGING= "false",
            STAGING_DISCOVER_BASE_URL= "none",
            STAGING_TITLE_ID= "none"
        },
        stag= {
            API_GATEWAY_ENV= "prod",
            API_GATEWAY_VERSION_2_0= "2.0",
            ATTEMPT_TO_SHOW= "10",
            CHANNEL_BASE_URL= "https://cdn-discover.hooq.tv/v1.5",
            CONVIVA_CORE_SDK_PATH= "/static/conviva/conviva-core-sdk.min.js",
            CONVIVA_HTML5_NATIVE_IMPLEMENTATION_PATH= "/static/conviva/conviva-html5native-impl.js",
            DEFAULT_TEST_IP= "202.138.229.73",
            DEFAULT_TEST_REGION= "ID",
            DISCOVER_BASE_URL= "https://cdn-discover.hooq.tv/v1.5",
            ENABLE_NEWRELIC= "false",
            ENV= "sandbox",
            EVE_BASE_URL= "http://eve.hooq.vpc",
            EV_WEBVIEW_BASE_URL= "https://authenticate.hooq.tv",
            INTERVAL_TO_SHOW= "3000",
            KASHMIR_BASE_URL= "http://kashmir-sandbox.hooq.vpc/2.0",
            KASHMIR_CACHE_EXPIRY= "600",
            KASHMIR_ENABLE_CACHE= "false",
            MMDB_VERSION= "20180828",
            NEWRELIC_APP= "HOOQ-GRAB-WEBVIEW-PREPROD",
            NEWRELIC_BROWSER_APPLICATION_ID= "182105430",
            NEWRELIC_BROWSER_FILE_PATH= "/static/nr/nr.js",
            NEWRELIC_LEVEL= "error",
            NODE_DEBUG= "true",
            NODE_ENV= "production",
            NODE_PORT= "4352",
            PLAY_BASE_URL= "https://api.hooq.tv",
            R21_ENABLED= "true",
            REDIS_PORT= "6379",
            SANCTUARY_API_ENV= "prod",
            SANCTUARY_API_VERSION= "2.0",
            SEARCH_BASE_URL= "https://api.hooq.tv",
            STAGING= "true",
            STAGING_DISCOVER_BASE_URL= "none",
            STAGING_TITLE_ID= "none"
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
  default     = "miyazaki"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "miyazaki"
}

variable "tag" {
  default = "GITHUBSHA"
}