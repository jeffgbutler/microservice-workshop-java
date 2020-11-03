# Movie Aggregator Service - Open Feign Edition

This is an aggregator web service. It demonstrates calling multiple backing web services to create an aggregated answer. The calls to backing web services are all protected by Hystrix fallback methods to stop a cascading failure, and the backing web services are found through service discovery with Eureka.
