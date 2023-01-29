# online-box-office
Online Box Office(obo) is an example project for microservice architecture style.
<!-- TOC -->
* [Deployment](#deployment)
  * [JDK](#jdk)
  * [Maven](#maven)
  * [IDEA / Eclipse](#idea--eclipse)
  * [MySQL](#mysql)
  * [Kafka](#kafka)
  * [ShardingSphere Proxy](#shardingsphere-proxy)
  * [Observability](#observability)
* [Tags](#tags)
  * [s0](#s0)
  * [s1](#s1)
  * [s2](#s2)
  * [s3](#s3)
  * [s4](#s4)
<!-- TOC -->
## Deployment
In order to run this project, you will have to install the following software:
### JDK
The project is based on Java, so JDK is required.
* Download and install either [Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java8) or [OpenJDK](https://www.openlogic.com/openjdk-downloads), JDK 1.8 is preferred.
* Set up 'JAVA_HOME' environment variable to the JDK path
### Maven
Apache Maven is used to manage the project:
* Download [Apache Maven](https://maven.apache.org/download.cgi), choose the latest binary package, unzip it to a specific directory.
* Add Maven '/bin' to the environment variable 'PATH'.
* Configure it following the [instruction](https://maven.apache.org/configure.html) (Normally, it's not required).
### IDEA / Eclipse
An IDE is a good plus to help you understand the source code. Choose one of your favorite IDE and install it:
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download), community version is enough
* [Eclipse](https://www.eclipse.org/downloads/)

IDEA is preferred.
### MySQL
MySQL provides different package for different platform. 
[Here](https://dev.mysql.com/downloads/mysql/) is the page for Windows, you can choose MSI installer to install MySQL.
It's easy to install and configure MySQL following the step of MSI installer. 
If you don't have permission to install MySQL, see [this file](docs/mysql.md) for noninstall archive instruction.
### Kafka
See [here](docs/s3.mq.Kafka.md#Kafka) for instructions.
### ShardingSphere Proxy
Download ShardingSphere Proxy binary files from [here](https://archive.apache.org/dist/shardingsphere/5.1.2/apache-shardingsphere-5.1.2-shardingsphere-proxy-bin.tar.gz).
More detailed information can be found from [s2.db.4.SHARD.PROXY.SEATA](docs/s2.db.4.SHARD.PROXY.SEATA.md). 

### Observability
The following software are not manaditory, they are used to improve the observability of the system:
1. [Prometheus](docs/s4.ob.metrics.md#prometheus)
2. [Grafana](docs/s4.ob.metrics.md#grafana)
3. [Zipkin](docs/s4.ob.traces.Zipkin.md#zipkin-and-brave)/Jaeger

## Tags
In order to show different technology/pattern of microservices, some tags were added to this project.
The following paragraph will explain what's contained in each tag. Just run 'git checkout <tag name>' to see the implementation.
### s0
Tags begin with 's0' show the basic knowledge about microservices, such as the knowledge about Spring Boot, and Domain Driven Design.
* [s0.spring.boot](docs/s0.spring.boot.md)
* [s0.domain.1](docs/s0.domain.1.md)
* [s0.domain.2](docs/s0.domain.2.md)
### s1
Tags begun with 's1' show some common patterns in microservices, and how to implement them in Spring Cloud.
Tags with '**_s1.lb._**' prefix are related to load balancing. Tags with '**_s1.cb._**' prefix are related to circuit breaker.
Tags with '**_s1.gw._**' prefix are related to gateway.
* Load balancing **_s1.lb_**
  * [s1.lb.0.RestTemplate](docs/s1.lb.0.RestTemplate.md)
  * [s1.lb.1.RestTemplate.SimpleDiscoveryClient](docs/s1.lb.1.RestTemplate.SimpleDiscoveryClient.md)
  * [s1.lb.2.RestTemplate.Eureka](docs/s1.lb.2.RestTemplate.Eureka.md)
  * [s1.lb.3.OpenFeign.Eureka](docs/s1.lb.3.OpenFeign.Eureka.md)
* Circuit breaker **_s1.cb_**
  * [s1.cb.1.CircuitBreaker](docs/s1.cb.1.CircuitBreaker.md)
  * [s1.cb.2.FeignCircuitBreaker](docs/s1.cb.2.FeignCircuitBreaker.md)
  * [s1.cb.3.Bulkhead](docs/s1.cb.3.Bulkhead.md)
  * [s1.cb.4.Sentinel](docs/s1.cb.4.Sentinel.md)
* Gateway **_s1.gw_**
  * [s1.gw.Gateway](docs/s1.gw.1.Gateway.md)
### s2
Tags begun with 's2' show some patterns about traditional relational database, such as distributed transaction, database sharding.
* [s2.db.1.SEATA.AT](docs/s2.db.1.SEATA.AT.md)
* [s2.db.2.SHARD.JDBC](docs/s2.db.2.SHARD.JDBC.md)
* [s2.db.3.SHARD.JDBC.SEATA](docs/s2.db.3.SHARD.JDBC.SEATA.md)
* [s2.db.4.SHARD.PROXY.SEATA](docs/s2.db.4.SHARD.PROXY.SEATA.md)
### s3
Tags begun with 's3' show some general requirements in microservice.
* [s3.mq.Kafka](docs/s3.mq.Kafka.md)
* [s3.id.cache](docs/s3.id.cache.md)
### s4
Tags begun with 's4' show the observability of microservice.
* [s4.ob.metrics](docs/s4.ob.metrics.md)
* [s4.ob.traces.Zipkin](docs/s4.ob.traces.Zipkin.md)
* [s4.ob.logs](docs/s4.ob.logs.md)