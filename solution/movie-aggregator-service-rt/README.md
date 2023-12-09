# Movie Aggregator Service - REST Template Version

This is an aggregator web service. It demonstrates calling multiple backing web services to create an aggregated answer.
The calls to backing web services are all protected by circuit breakers to stop a cascading failure,
and the backing web services are found through service discovery with Eureka.

This project was bootstrapped with https://start.spring.io with the following dependencies:

- Spring Web
- Spring Boot Actuator
- Eureka Discovery Client
- Resilience4J
- Spring security
