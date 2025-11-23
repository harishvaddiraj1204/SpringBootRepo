package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(WebClient.Builder builder, OpenAIConfig config) {
        return builder
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }
}
