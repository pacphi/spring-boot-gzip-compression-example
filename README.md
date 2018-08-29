# Spring Boot Gzip Compression Example

Simple example that demonstrates how to configure Gzip [compression](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-web-servers.html#how-to-enable-http-response-compression) for HTTP responses in a [Spring Boot](https://spring.io/projects/spring-boot#learn) 2.x microservice.

## Requirements

* [java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 8u181 or better
* [cf](https://github.com/cloudfoundry/cli/releases) CLI 6.38.0 or better

## To get the code

    $ git clone https://github.com/pacphi/spring-boot-gzip-compression-example.git

> If this is your first time using Github, review http://help.github.com to learn the basics.

## To enable the /citylots.json endpoint

From a shell

    $ cd spring-boot-gzip-compression-example
    $ cd src/main/resources
    $ mkdir static
    $ cd static
    $ git clone https://github.com/zemirco/sf-city-lots-json
    $ mv sf-city-lots-json/citylots.json .
    $ rm -Rf sf-city-lots-json
    $ cd ../../../../

## To run the application

From a shell

    $ cd spring-boot-gzip-compression-example
    $ ./gradlew clean bootRun 

## To deploy to Cloud Foundry 

e.g. on [Pivotal Web Services](https://run.pivotal.io/features/)

    $ cf login -a api.run.pivotal.io
    $ cf push 


## To verify response is compressed

### Locally

    $ curl -H "Accept-Encoding: gzip" http://localhost:8080/citylots.json > citylots.json.gz
    $ curl -H "Content-Type: application/json" http://localhost:8080/actuator/httptrace

```json
{
  "traces": [
    {
      "timestamp": "2018-08-29T00:27:30.284200Z",
      "principal": null,
      "session": null,
      "request": {
        "method": "GET",
        "uri": "http://localhost:8080/citylots.json",
        "headers": {
          "host": [
            "localhost:8080"
          ],
          "accept-encoding": [
            "gzip"
          ],
          "user-agent": [
            "curl/7.54.0"
          ],
          "accept": [
            "*/*"
          ]
        },
        "remoteAddress": null
      },
      "response": {
        "status": 200,
        "headers": {
          "Accept-Ranges": [
            "bytes"
          ],
          "Transfer-Encoding": [
            "chunked"
          ],
          "Content-Encoding": [
            "gzip"
          ],
          "Vary": [
            "Accept-Encoding"
          ],
          "Last-Modified": [
            "Wed, 29 Aug 2018 00:26:23 GMT"
          ],
          "Date": [
            "Wed, 29 Aug 2018 00:27:30 GMT"
          ],
          "Content-Type": [
            "application/json"
          ]
        }
      },
      "timeTaken": 3307
    }
  ]
}
```

### on PWS

    $ curl -H "Accept-Encoding: gzip" http://spring-boot-gzip-compression-example-active-meerkat.cfapps.io/citylots.json > citylots.json.gz 
    $ curl -H "Content-Type: application/json" http://spring-boot-gzip-compression-example-active-meerkat.cfapps.io/actuator/httptrace

```json
{
  "traces": [
    {
      "timestamp": "2018-08-29T00:30:28.641Z",
      "principal": null,
      "session": null,
      "request": {
        "method": "GET",
        "uri": "http://spring-boot-gzip-compression-example-active-meerkat.cfapps.io/citylots.json",
        "headers": {
          "x-cf-applicationid": [
            "54cd4b9f-71a8-4ce2-b457-79cc32e5f368"
          ],
          "x-forwarded-proto": [
            "http"
          ],
          "x-b3-traceid": [
            "214b30441344844d"
          ],
          "x-b3-spanid": [
            "214b30441344844d"
          ],
          "x-cf-instanceid": [
            "c6fe8b5a-f417-415d-6c8c-314a"
          ],
          "x-vcap-request-id": [
            "78c2ebe5-b5c0-4f76-66a5-0e8cee2f8563"
          ],
          "x-request-start": [
            "1535502627509"
          ],
          "host": [
            "spring-echo-example-terrific-badger.cfapps.io"
          ],
          "x-forwarded-port": [
            "80"
          ],
          "x-cf-instanceindex": [
            "0"
          ],
          "accept-encoding": [
            "gzip"
          ],
          "accept": [
            "*/*"
          ],
          "user-agent": [
            "curl/7.54.0"
          ]
        },
        "remoteAddress": null
      },
      "response": {
        "status": 200,
        "headers": {
          "Transfer-Encoding": [
            "chunked"
          ],
          "Accept-Ranges": [
            "bytes"
          ],
          "Content-Encoding": [
            "gzip"
          ],
          "Vary": [
            "Accept-Encoding"
          ],
          "Last-Modified": [
            "Wed, 29 Aug 2018 00:26:52 GMT"
          ],
          "Date": [
            "Wed, 29 Aug 2018 00:30:28 GMT"
          ],
          "Content-Type": [
            "application/json"
          ]
        }
      },
      "timeTaken": 9796
    }
  ]
}
```

# License

Released under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html)

# Credits

* Thanks to [Ben Hale](https://github.com/nebhale) for the `/actuator/httptrace` tip (I used for verification purposes).
