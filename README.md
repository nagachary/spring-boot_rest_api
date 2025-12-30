## Spring Boot REST API | Dockerized Application | Maven Build

This is my first project where I developed a Spring Boot API, published it on GitHub, and containerized the application using Docker and the Maven build tool.

### To generate and run as a docker image

    - Generate a docker file
        - Dockerfile

    - Build docker image
        - docker build -t spring-boot_rest_api . 

    - Run the generated docker image
        - docker run -p 8085:8085 spring-boot_rest_api

#### Endpoints to test

The following API endpoints are exposed as part of this application.

```
- /mysbapp/country/currency/USD
- /mysbapp/address/<zipcode>
- /mysbapp/objects?ids=3
```

### Implementations

* [Amazon Locker](./src/main/java/com/mylld/amazon/locker/README.md)