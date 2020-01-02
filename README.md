# Grails Elasticsearch Sample Application
This is a sample Grails 4 REST Application configured to use Elasticsearch grails plugin.

## Prerequisite

1. Setup Elasticsearch Docker Container as:

```
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" docker.elastic.co/elasticsearch/elasticsearch:5.5.3
```

Please see [Install Elasticsearch Documentation](https://www.elastic.co/guide/en/elasticsearch/reference/5.5/install-elasticsearch.html) for other options.

2. Run application using command `./gradlew bootRun`.

3. Verify that the `Post` mapping is created, run the following CURL command:
```
curl -X GET "localhost:9200/_mapping/post?pretty"
```
The output of above command should be something similar to:
```
{
  "example.all_v0" : {
    "mappings" : {
      "post" : {
        "_all" : {
          "enabled" : false
        },
        "properties" : {
          "body" : {
            "type" : "text",
            "term_vector" : "with_positions_offsets"
          },
          "subject" : {
            "type" : "text",
            "term_vector" : "with_positions_offsets"
          }
        }
      }
    }
  }
}
```
