package com.example.demo.service;

import com.example.demo.config.OpenAIConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class AIService {
    private final WebClient webClient;
    private final OpenAIConfig config;

    public AIService(WebClient.Builder builder, OpenAIConfig config) {
        this.webClient = builder.build();
        this.config = config;
    }

    public Mono<Map> getChatCompletion(String message) {

        Map<String, Object> body = Map.of(
                "model", config.getModel(),
                "input", message,
                "tools", List.of(
                        Map.of("type", "web_search")
                ),
                "tool_choice", "auto"
        );

        return webClient.post()
                .uri(config.getBaseUrl())
                .header("Authorization", "Bearer " + config.getKey())
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class);
    }
}


