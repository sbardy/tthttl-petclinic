package com.tthttl.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("owners", predicateSpec -> predicateSpec
                        .path("/owners/**")
                        .uri("lb://customer-service")
                )
                .route("pets", predicateSpec -> predicateSpec
                        .path("/pets/**")
                        .uri("lb://customer-service")
                )
                .route("vets", predicateSpec -> predicateSpec
                        .path("/vets/**")
                        .uri("lb://vet-service")
                )
                .route("visits", predicateSpec -> predicateSpec
                        .path("/visits/**")
                        .uri("lb://visit-service")
                )
                .build();
    }

}

