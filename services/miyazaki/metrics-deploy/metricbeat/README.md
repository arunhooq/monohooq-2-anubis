# Metricbeat Deployment
Deploying Metricbeat as ECS Service


#### Set the ILM POLICY for Metricbeat

* Setting the ilm_policy for metricbeat as below for one time.

```
curl -s -XPUT -u elastic:XXXXXXXXXXXX "https://e57ad45d8ace49edaca54e2d19b2c830.ap-southeast-1.aws.found.io:9243/_ilm/policy/metricbeat_ilm_policy?pretty" -H 'Content-Type: application/json' -d'
{
  "policy": {
    "phases": {
      "hot": {
        "min_age": "0ms",
        "actions": {
          "rollover": {
            "max_age": "7d",
            "max_size": "10gb"
          },
          "set_priority": {
            "priority": 100
          }
        }
      },
      "warm": {
        "min_age": "0ms",
        "actions": {
          "allocate": {
            "number_of_replicas": 1,
            "include": {},
            "exclude": {},
            "require": {
              "data": "warm"
            }
          },
          "forcemerge": {
            "max_num_segments": 1
          },
          "set_priority": {
            "priority": 50
          },
          "shrink": {
            "number_of_shards": 1
          }
        }
      },
      "delete": {
        "min_age": "21d",
        "actions": {
          "delete": {}
        }
      }
    }
  }
}
'

```

#### Get the ILM POLICY for Metricbeat

* Get the ilm_policy for metricbeat as below.
```
$ curl -s -XGET -u elastic:XXXXXXXXXX "https://e57ad45d8ace49edaca54e2d19b2c830.ap-southeast-1.aws.found.io:9243/_ilm/policy/metricbeat_ilm_policy?pretty" -H 'Content-Type: application/json' | jq
{
  "metricbeat_ilm_policy": {
    "version": 2,
    "modified_date": "2019-12-05T08:06:17.902Z",
    "policy": {
      "phases": {
        "hot": {
          "min_age": "0ms",
          "actions": {
            "rollover": {
              "max_size": "10gb",
              "max_age": "7d"
            },
            "set_priority": {
              "priority": 100
            }
          }
        },
        "delete": {
          "min_age": "21d",
          "actions": {
            "delete": {}
          }
        },
        "warm": {
          "min_age": "0ms",
          "actions": {
            "allocate": {
              "number_of_replicas": 1,
              "include": {},
              "exclude": {},
              "require": {
                "data": "warm"
              }
            },
            "forcemerge": {
              "max_num_segments": 1
            },
            "set_priority": {
              "priority": 50
            },
            "shrink": {
              "number_of_shards": 1
            }
          }
        }
      }
    }
  }
}

``` 

