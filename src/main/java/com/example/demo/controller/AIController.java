package com.example.demo.controller;

import com.example.demo.service.AIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {
    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

   /* @PostMapping("/chat")
    public String chatWithAI(@RequestParam String message) {
        return aiService.getAIResponse(message);
    }*/

    @GetMapping
    public Mono<Map> chat(@RequestParam String q) {
        return aiService.getChatCompletion(q);
    }
}
