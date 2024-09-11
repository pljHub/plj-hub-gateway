package com.plj.hub.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service", r -> r.path(PathConfig.USER_SERVICE)
                        .uri("lb://USER-SERVICE"))

                .route("hub_service", r -> r.path(PathConfig.HUB_SERVICE)
                        .uri("lb://HUB-SERVICE"))

                .route("product_service", r -> r.path(PathConfig.PRODUCT_SERVICE)
                        .uri("lb://HUB-SERVICE"))

                .route("company_service", r -> r.path(PathConfig.COMPANY_SERVICE)
                        .uri("lb://HUB-SERVICE"))

                .route("hub_path_service", r -> r.path(PathConfig.HUB_PATH_SERVICE)
                        .uri("lb://HUB-SERVICE"))

                .build();
    }
}
