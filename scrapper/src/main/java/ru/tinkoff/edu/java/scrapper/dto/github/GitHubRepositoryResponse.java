package ru.tinkoff.edu.java.scrapper.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubRepositoryResponse(String name,
                                       GitHubRepositoryOwnerResponse owner,
                                       Boolean fork,
                                       @JsonProperty("forks_count") Long forksCount,
                                       @JsonProperty("watchers_count") Long watchersCount,
                                       @JsonProperty("pushed_at") OffsetDateTime pushedAt,
                                       @JsonProperty("created_at") OffsetDateTime createdAt) {
}
