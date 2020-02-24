variable "availability-zones" {
  default = [
    "ap-southeast-1a",
    "ap-southeast-1b",
    "ap-southeast-1c"]
}

variable "env" {
  default = {
    play = {
      web = {
        environment = "prod",
        Project = "WEB",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "iPhone-Safari",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv",
        strTestRailSuiteName = "PWA_Smoke_and_Regression",
        strTestRail = "true",
        strMaxRetryCount = "5",
        isVideoRecord = "false"
      },

      api = {
        environment = "stag",
        Project = "API",
        EmailList = "arun@hooq.tv,david@hooq.tv"
      },

      ios = {
        environment = "prod",
        Project = "IOS",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "iPhone",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv",
        strTestRailSuiteName = "IOS_Automated_Regression_Suite",
        strTestRail = "true",
        strMaxRetryCount = "2",
        isVideoRecord = "false",
        browserstackAppId = "bs://92239f9a4d8df5c16488d9d8fc6e507e1d44872a"
      },

      partnerapi = {
        environment = "stag",
        eligibilityService = "UMS",
        Project = "APIPartner",
        EmailList = "yayan@hooq.tv,arun@hooq.tv",
      },

      android = {
        environment = "prod",
        Project = "ANDROID",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "Android",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv"
      }

    }

    dev = {
      web = {
        environment = "prod",
        Project = "WEB",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "iPhone-Safari",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv,ali@hooq.tv,yayan@hooq.tv,depa@hooq.tv",
        strTestRailSuiteName = "PWA_Smoke_and_Regression",
        strTestRail = "true",
        strMaxRetryCount = "5",
        isVideoRecord = "false",
        RunType       = "AWS-Run"
      },

      api = {
        environment = "stag",
        Project = "API",
        EmailList = "arun@hooq.tv,david@hooq.tv,ali@hooq.tv,yayan@hooq.tv,depa@hooq.tv",
        RunType       = "AWS-Run"
      },

      ios = {
        environment = "prod",
        Project = "IOS",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "iPhone",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv,ali@hooq.tv,yayan@hooq.tv,depa@hooq.tv",
        strTestRailSuiteName = "IOS_Automated_Regression_Suite",
        strTestRail = "true",
        strMaxRetryCount = "2",
        isVideoRecord = "false",
        RunType       = "AWS-Run"
      },

      partnerapi = {
        environment = "stag",
        eligibilityService = "MJOLNIR",
        Project = "APIPartner",
        RunType  = "AWS-Run",
        EmailList = "arun@hooq.tv,david@hooq.tv,ali@hooq.tv,yayan@hooq.tv,depa@hooq.tv"
      },

      android = {
        environment = "prod",
        Project = "ANDROID",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "Android",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv,ali@hooq.tv,yayan@hooq.tv,depa@hooq.tv,raja.ala@cigniti.com",
        RunType       = "AWS-Run"
      }

    }
    stag = {
      web = {
        environment = "prod",
        Project = "WEB",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "iPhone-Safari",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv",
        strTestRailSuiteName = "PWA_Smoke_and_Regression",
        strTestRail = "true",
        strMaxRetryCount = "5",
        isVideoRecord = "false",
        RunType       = "AWS-Run"
      },

      api = {
        environment = "stag",
        Project = "API",
        EmailList = "arun@hooq.tv,david@hooq.tv",
        RunType       = "AWS-Run"
      },

      ios = {
        environment = "prod",
        Project = "IOS",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "iPhone",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv",
        strTestRailSuiteName = "IOS_Automated_Regression_Suite",
        strTestRail = "true",
        strMaxRetryCount = "2",
        isVideoRecord = "false",
        browserstackAppId = "bs://92239f9a4d8df5c16488d9d8fc6e507e1d44872a",
        RunType       = "AWS-Run"
      },

      partnerapi = {
        environment = "stag",
        eligibilityService = "UMS",
        Project = "APIPartner",
        EmailList = "yayan@hooq.tv,arun@hooq.tv",
        RunType       = "AWS-Run"
      },

      android = {
        environment = "prod",
        Project = "ANDROID",
        suiteType = "Sanity",
        userTypeList = "Active,Lapsed,Visitor",
        countries = "SG,ID,PH,TH,IN",
        strPlatformName = "Android",
        targetExecution = "browserstack",
        strGlobalTimeout = "30",
        EmailList = "arun@hooq.tv,david@hooq.tv",
        RunType       = "AWS-Run"
      }

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
  default = "anubis"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default = "anubis"
}

variable "tag" {
  default = "GITHUBSHA"
}