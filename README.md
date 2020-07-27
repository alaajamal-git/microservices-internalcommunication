# microservices-internalcommunication

internal communications :
 in this project we used two different tools to make users-ws microservice communicate with posts-ws microservice:
    1. RestTemplate.
    2. Feign web service client.
    
-RestTemplate: to implement this client we need to define a bean method in Spring main class and annotate this class with @LoadBlanced to make our client balance the requests load to posts-ws microservice and finally use the exchange method of RestTemplate in our userserviceImp class to send a request and receive a response.
-Feign: in this approach, we have to add maven repository called "Spring Cloud Starter Feign" and then enable feign client in the Spring main class. to define our feign client we have to create an interface that annotated by @FeignClient this annotation enables us to set client name which is "posts-ws". Then we can define the signature method and its @GetMapping URL that used to send requests to feign client "posts-ws" after that we can use this method directly from our userserviceImp class.

Feign Exceptions:
 to handle feign exception all we have to do is to create a class FeignErrors that should implement ErrorDecoder and then we can handle the exception using the decode method.
 
Circuit Breaker:
  using hystrix netflix we can implement the fault tolerance during the internal communications between microservices means that when a microservice becomes offline or an error       occours, then we can make our Feign service method "getUserPosts" return specific values under these errors.
    All we have to do is to add a fallbackFactory param in @FeignClient annotation to refer to our FallBackFactory class that should implements FallbackFactory interface. when an     exception triggered then the create method will invoke from this class by Spring framework and this method should return an instance of the failed Feign interface to override     its method to use it by SpringFramework under errors.
  


