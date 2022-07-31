# online-box-office
Online Box Office(obo) is an example project for microservice architecture style.
<!-- TOC -->
* [Deployment](#deployment)
  * [JDK](#jdk)
  * [Maven](#maven)
  * [IDEA / Eclipse](#idea--eclipse)
* [Tags](#tags)
  * [s0](#s0)
  * [s1](#s1)
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
* Load balancing **_s1.lb_**
  * [s1.lb.0.RestTemplate](docs/s1.lb.0.RestTemplate.md)
  * [s1.lb.1.RestTemplate.SimpleDiscoveryClient](docs/s1.lb.1.RestTemplate.SimpleDiscoveryClient.md)
  * [s1.lb.2.RestTemplate.Eureka](docs/s1.lb.2.RestTemplate.Eureka.md)
  * [s1.lb.3.OpenFeign.Eureka](docs/s1.lb.3.OpenFeign.Eureka.md)