package com.example.demo.service;

import com.example.demo.config.OpenAIConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class AIService {
    /*@Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${ai.api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public AIService() {
        this.webClient = WebClient.builder().build();
    }

    public String getAIResponse(String userMessage) {
        // Prepare the request payload (OpenAI Chat Completions example)
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", userMessage);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", new Map[]{message});

        // Call AI API
        Mono<Map> response = webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class);

        Map<String, Object> result = response.block(); // blocking for simplicity
        if (result != null && result.containsKey("choices")) {
            Object[] choices = ((java.util.List) result.get("choices")).toArray();
            if (choices.length > 0) {
                Map firstChoice = (Map) choices[0];
                Map messageMap = (Map) firstChoice.get("message");
                return messageMap.get("content").toString();
            }
        }
        return "No response from AI";
    }*/

    private final WebClient webClient;
    private final OpenAIConfig config;

    public AIService(WebClient webClient, OpenAIConfig config) {
        this.webClient = webClient;
        this.config = config;

        System.out.println("Using OpenAI API key: " + config.getKey());
    }

    public Mono<Map> getChatCompletion(String message) {

        Map<String, Object> body = Map.of(
                "model", "gpt-4.1",
                "input", message,
                /*"messages", List.of(
                        Map.of("role", "user", "content", message)
                ),*/
                "tools", List.of(
                        Map.of("type", "web_search")
                ),
                "tool_choice", "auto"
        );

        return webClient.post()
                .uri(config.getBaseUrl())
                .header("Authorization", "Bearer " + "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")
                //.header("Authorization", "Bearer " + config.getKey())
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                /*.retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof WebClientResponseException.TooManyRequests))*/;
    }
}
