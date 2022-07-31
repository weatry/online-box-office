## Business of Schedule
Please see [business script](BusinessScript.md) of movie schedule.

## Spring Cloud Technical point

### Invocation between Services
RestTemplate is the simplest client of REST services.

### Service Registry & Load balance
Load balancing in microservices is usually based on service registry and discovery.

@EnableDiscoveryClient makes application look for implementations of the DiscoveryClient, which can be seen as a client of service registry. 
Examples of DiscoveryClient implementations include **Spring Cloud Netflix Eureka**, **Spring Cloud Consul Discovery**, and **Spring Cloud Zookeeper Discovery**.

If there is no implementation of DiscoveryClient in the classpath, SimpleDiscoveryClient instance, that uses properties to get information on service and instances, will be used.