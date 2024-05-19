package kz.edu.astanait.qarzhytracker.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qarzhy-tracker.open-ai.default-settings")
public record OpenAiDefaultSettingsConfigProperties(String model,
                                                    String role,
                                                    int n,
                                                    double temperature) {
}
