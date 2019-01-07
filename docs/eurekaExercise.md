# Eureka Exercise

In this exercise, we will install and configure a Eureka server for service discovery, then alter all web services to lookup endpoints from Eureka rather than hard coding URLs.

## Eureka Server

There is a simple Spring boot starter that will stand up a eureka server with almost no coding required. We'll use the Spring initializer web site to create the server.

1. Navigate to [https://start.spring.io](https://start.spring.io)
1. Create a Maven project with Java and the latest version of SpringBoot (2.1.1 at the time of writing)
1. Specify group: `microservice.workshop`
1. Specify artifact: `eureka-server`
1. For dependencies, add the following:
    - Eureka Server
1. Generate the project (causes a download)
1. Unzip the downloaded file somewhere convenient
1. Add the new project to your IDE workspace
    - Eclipse: File->Import->Existing Maven Project
    - IntelliJ: File->New->Module From Existing Sources...
    - VS Code: File->Add Folder to Workspace
1. Open `microservice.workshop.eurekaserver.EurekaServerApplication.java` and add the `@EnableEurekaServer` annotation to the class
1. Navigate to `src/main/resources`, then rename `application.properties` to `application.yml`
1. Enter the following values in `application.yml`:

```yml
info:
  app:
    name: Eureka Server

server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

These changes specify the port for the Eureka server (not really needed in this case because 8761 is default). More importantly, they tell the server not to register with Eureka - without these settings the server would try to register with itself!

Once these changes are made, start the server by starting the Spring boot application. After it starts, you can see the Eureka console at [http://localhost:8761](http://localhost:8761)

## Individual Web Services - Register with Eureka

The three individual web services (`movie-servive`, `movie-cast-service`, and `movie-award-service`) need to register themselves with eureka when they start. This is very simple with Spring boot - we simply have to add the eureka client dependency to the project and Spring Boot will do the magic.

In all three projects, open the `pom.xml` and uncomment the dependency for `spring-cloud-starter-netflix-eureka-client`.

Once all three `pom.xml` files have been changed, start all three services then navigate to the eureka console - you should see all three services registered with eureka.

Note that you may see a scary looking warning in the Eureka console. You can safely ignore this warning.

Note that the Eureka client will look for and register with a Eureka server at `localhost:8761` by default. In production you would need to change this default by configuring a proper production grade Eureka server. See the "Spring Cloud Netflix" documentation for details.

## Configure the Aggregator to Use Eureka

Spring Feign and Eureka have a very simple integration - it's automatic!

1. Open `pom.xml` in the `movie-aggreagator-service` project and uncomment the dependency for `spring-cloud-starter-netflix-eureka-client`
1. For each of the three Feign clients in the package `microservice.workshop.movieaggregatorservice.service`, simply remove the `url` attribute from the `@FeignClient` annotation. Without the URL, Feign will lookup the service address from Eureka using the specified name

Once thes changes are made, start the `movie-aggregator-service` project. If you open the Eureka console, you will see that the aggregator service has also registered itself with eureka.

Finally, start the `traffic-simulator` and make sure that information is still being returned correctly from the aggregate service.

Once you are happy with the results, stop the `movie-aggregator-service` - we'll make more shange there for Hystrix in the next exercise.
