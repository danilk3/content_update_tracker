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

    private Long openIssuesCount;

    private OffsetDateTime pushedAt;

    private OffsetDateTime createdAt;

    private Long linkId;

    private String url;

    private Long tgChatId;

    public String getMessage(GitHubRepositoryResponse response) {
        String message = "";

        message += response.name().equals(name) ? "" : String.format("name = %s ; ", response.name());
        message += response.openIssuesCount().equals(openIssuesCount) ? "" : String.format("openIssuesCount = %d ; ", response.openIssuesCount());
        message += response.fork().equals(fork) ? "" : String.format("fork = %s ; ", response.fork());
        message += response.forksCount().equals(forksCount) ? "" : String.format("forksCount = %d ; ", response.forksCount());
        message += response.watchersCount().equals(watchersCount) ? "" : String.format("watchersCount = %d ; ", response.watchersCount());

        return message.isEmpty() ? null : message;
    }
}
