package com.manas.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced // Client (this) will not understand which inventory-service to call (10.0.0.1 or 10.0.0.2 or ...) so this will help to pick one
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }
}
