package ru.tinkoff.edu.java.scrapper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubRepositoryResponse;

@Service
public class GitHubService {

    private final GitHubClient gitHubClient;

    @Autowired
    public GitHubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public GitHubRepositoryResponse getGitHubRepository(String username, String repositoryName) {
        return gitHubClient.getGithubRepository(username, repositoryName);
    }
}
