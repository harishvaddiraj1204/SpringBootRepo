package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openai.api")
@Data
public class OpenAIConfig {

    private String key;
    private String baseUrl;
    private String model;
}
