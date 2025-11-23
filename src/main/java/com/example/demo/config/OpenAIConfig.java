package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openai.api")
@Data
public class OpenAIConfig {

    @Value("${openai.api.key}")
    private String key;
    @Value("${openai.api.base-url}")
    private String baseUrl;
    private String model;

    /*public String getUrl() {
        return baseUrl + "/responses";
    }*/
}
