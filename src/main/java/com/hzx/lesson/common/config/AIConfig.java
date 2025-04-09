package com.hzx.lesson.common.config;

import com.hzx.lesson.common.enums.AIModelType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "ai")
public class AIConfig {
    private Map<AIModelType, ModelConfig> configs;

    @Data
    public static class ModelConfig {
        private String host;
        private String path;
        private String model;
        private String apiKey;
    }
}
