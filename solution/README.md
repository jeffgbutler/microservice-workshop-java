# Workshop Solution

The projects in this directory fully demonstrate using circuit breakers and service discovery.
When running, all projects can be accessed at `localhost:port`. However, Eureka is very sensitive
to host names so the Eureka configuration uses `nip.io` names.

# Running the Application
To fully run the application, several projects must be started as detailed below.

## Eureka Servers

Start all of these projects:

| Project                                     | URL                                  |
|---------------------------------------------|--------------------------------------|
| [Primary Eureka Server](eureka-server/)     | http://eureka1.127.0.0.1.nip.io:8761 |
| [Secondary Eureka Server](eureka-server-2/) | http://eureka2.127.0.0.1.nip.io:8762 |

## Component Microservice

Start all of these projects:

| Project                                     | URL                   |
|---------------------------------------------|-----------------------|
| [Movie Service](movie-service/)             | http://localhost:8091 |
| [Movie Award Service](movie-award-service/) | http://localhost:8093 |
| [Movie Cast Service](movie-cast-service/)   | http://localhost:8092 |

If you access the component microservices individually, you will see a Swagger (OpenAPI) client
that you can use to exercise the service.

## Front-End (Aggregator) Service

Start ONE of these projects:

| Project                                                            | URL                   |
|--------------------------------------------------------------------|-----------------------|
| [Front-End with OpenFeign Client](movie-aggregator-service/)       | http://localhost:8080 |
| [Front-End with RestTemplate Client](movie-aggregator-service-rt/) | http://localhost:8080 |

Once one of the front-end projects is started, you can access the project URL and simulate traffic to
all the microservices.

# Resiliency

1. Any of the component microservices can be stopped and a circuit breaker will keep the application
   from experiencing a cascading failure
2. Either Eureka server can be stopped. The clients use Spring Cloud Load Balancer to find an active server
3. If both Eureka server clients are stopped, the clients can continue running because of client-side
   caching of Eureka data. This will only work if the clients were able to obtain data from Eureka before
   both servers are stopped. 
