package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.services.GitHubService;

@RestController
@RequestMapping("api/github")
public class GitHubController {

    private final GitHubService gitHubService;

    @Autowired
    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("repo")
    public ResponseEntity<GitHubRepositoryResponse> getAllWatchingLinks(@RequestParam String username, @RequestParam String repositoryName) {
        return ResponseEntity.ok().body(gitHubService.getGitHubRepository(username, repositoryName));
    }
}
