package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.Data;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubRepositoryResponse;

import java.time.OffsetDateTime;

@Data
public class GitHubRepositoryLink {

    private Long id;

    private String name;

    private Boolean fork;

    private Long forksCount;

    private String ownerName;

    private Long watchersCount;

    private OffsetDateTime pushedAt;

    private OffsetDateTime createdAt;

    private Long linkId;

    private String url;

    private Long tgChatId;

    public boolean isEqualToGitHubRepositoryResponse(GitHubRepositoryResponse response) {
        return response.name().equals(name) &&
                response.owner().login().equals(ownerName) &&
                response.fork().equals(fork) &&
                response.forksCount().equals(forksCount) &&
                response.watchersCount().equals(watchersCount) &&
                response.pushedAt().equals(pushedAt);
    }

}
