# Microservices

webService (Service Oriented Architecture) : You don't have any idea of, where you are goint to use it. You will create and it acts as utility. 
You can reuse wherever you want.

MicroService : You will have create picture of what you are going to create. Eg, create an amazon like application. You can also reuse this, but you 
will not create for the indent of reusing.

Technology used : Spring cloud
Each microservice will be a spring-boot application.

If you are calling another service using RestTemplate and convert to object. That object should have no-args constructor.
Eg : restTemplate.getForObject("http://localhost:8081/technologies/" + rating.getTechnoId(), Technology.class);
Technonlogy class should have no-args constructor.

Eureka is the technonlogy to do sevice discovery.

@EnableEurekaServer - add this in Discovery microservice.

Eventhough Eureka server started, we will get some errors/warnings in console. To avoid that we have to add below in application.properties
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

To give application name, which can be seen in Eureka instance
spring.application.name=TechnologyInfoService

@LoadBalanced - Without it cannot able to find the instance in Eureka server.
It not only discovers service, it also does client side load balancing.

We can do clientSide load balancing by using DiscoveryClient.

Fault tolerance - 
what happens when any microService goes down ?
How we are going to handle the fault ? 

Resilience -
how many fault a system can tolerate ?
how much a system can bounce back from the fault?

What if a microservice goes down ?
	We can avoid by creating multiple instances for a microservice.

What if a microservice is slow ?
	We can set timeout in RestTemplate. Below I set 3 seconds. But this will not solve the problem completely.
	
	public RestTemplate getRestTemplate() {
		final HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setConnectTimeout(3000);
		return new RestTemplate(httpComponentsClientHttpRequestFactory);
	}
	
	The best solution is Circuit Breaker. This is the popular pattern for fault tolerance.
		a. detects something is wrong
		b. avoiding the situation getting worse, by not sending the request, to the microservice which is slow.
		c. deactivate the problem component, so that it doesn't affect downstream components.
	
	Where to use Circuit Breaker ?
		you have to use in all microservice which calls another microservice.
		
	When the circuit trip ?
		Last n requests to consider for the decision.
		How many of those should fail ?
		Timeout duration
	
	When the circuit un-trip(getting back to normal) ?		
		How long after a circuit trip to try again ?
			based on number of request and thread pool size.
			
	After circuit breaks, we will send the possible response from cache.
	
Hystrix - open source library created by netFlix. It implements circuit breaker patterns.	
	
1. add dependency <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
2. add @EnableCircuitBreaker in main class
3. add @HystrixCommand(fallbackMethod = "getFallback") over the method, which call another microservice. Here by default getFallback method will get called.


Distributed log tracing :

Sleuth + zipkins

Spring Cloud Sleuth provides Spring Boot auto-configuration for distributed tracing.

add the below dependencies

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-sleuth</artifactId>
	<version>3.0.2</version>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-zipkin</artifactId>
	<version>2.2.7.RELEASE</version>
</dependency>

Application + TraceID (same throughout all microservices) + SpanID (differs for different services) + Zipkin Flag (the boolean flag indicates whether to export to zipkin)
Spring Cloud Sleuth will take care of creating TraceID and SpanID

Zipkin service will serve as the store for all our spans. Each span is sent to this service and collected into traces for future identification.

<dependency>
	<groupId>io.zipkin</groupId>
	<artifactId>zipkin-server</artifactId>
	<version>2.23.2</version>
</dependency>
<dependency>
	<groupId>io.zipkin.java</groupId>
	<artifactId>zipkin-autoconfigure-ui</artifactId>
	<version>2.12.9</version>
</dependency>

@EnableZipkinServer in application class



spring.zipkin.base-url=http://localhost:9411/ >> we need to add this in microservices for which you want to see log in zipkins

http://localhost:8080/employee/viki
http://localhost:8091/technologies/1
http://localhost:8082/ratingsdata/3
http://localhost:8082/ratingsdata/employee/1

in28minutes
nana
ameego
mosh >> threading

javascript
angular
java8
spring
springboot
microservices >> in28minutes, koushik
typescript

Topics covered
  1. Service Discovery
  2. Fault tolerance/ resilience (Fallback using Hystrix)
  3. Spring slueth + zipkins
