# tthttl-petclinic
My version of https://github.com/spring-petclinic/spring-petclinic-microservices. 

I wanted to get a hands-on experience with the microservices architecture with spring boot and docker. 

Multiple profiles for: testing/development/production.  
Microservice stack: spring cloud gateway, eureka, zipkin, feign.  
Client-side: angular 7, ag-grid, bootstrap.

1. Start with docker-compose: **docker-compose up**
2. Start without docker-compose:  
Start up the services one-by-one: **mvn spring-boot:run**  
Start the client: **npm install** => **ng serve**  

- Angular7 frontend - http://localhost:4200
- Discovery Server - http://localhost:8761
- Config Server - http://localhost:8888
- API Gateway - http://localhost:8080
- Customer, Vet and Visit Services - random port, check Eureka Dashboard  

![alt text](https://github.com/spring-petclinic/spring-petclinic-microservices/blob/master/docs/microservices-architecture-diagram.jpg)  
