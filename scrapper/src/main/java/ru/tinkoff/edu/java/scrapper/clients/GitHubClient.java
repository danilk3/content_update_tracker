package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubRepositoryResponse;

@Component
public class GitHubClient {

    private final WebClient webClient;

    @Autowired
    public GitHubClient(@Qualifier("gitHubWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public GitHubRepositoryResponse getGithubRepository(String username, String repositoryName) {
        return webClient
                .get()
                .uri(String.format("repos/%s/%s", username, repositoryName))
                .retrieve()
                .bodyToMono(GitHubRepositoryResponse.class)
                .block();
    }
}
