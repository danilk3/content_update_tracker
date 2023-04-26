package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";
    private static final String STACKOVERFLOW_BASE_URL = "https://api.stackexchange.com/2.3/";

    private final ApplicationConfig applicationConfig;

    @Autowired
    public ClientConfiguration(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public WebClient gitHubWebClient(@Value("${app.github.baseUrl:#{null}}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl == null ? GITHUB_BASE_URL : baseUrl)
                .defaultHeader("Accept", "application/vnd.github+json")
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
                .defaultHeader("Authorization", String.format("Bearer %s", applicationConfig.gitHubToken()))
                .build();
    }

    @Bean
    public WebClient stackoverflowWebClient(@Value("${app.stackoverflow.baseUrl:#{null}}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl == null ? STACKOVERFLOW_BASE_URL : baseUrl)
                .build();
    }

    @Bean
    public WebClient botWebClient(@Value("{bot.base-url}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
