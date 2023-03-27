package ru.tinkoff.edu.java.scrapper.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record GitHubRepositoryResponse(Long id,
                                       String name,
                                       @JsonProperty("full_name") String fullName,
                                       GitHubRepositoryOwnerResponse owner,
                                       @JsonProperty("html_url") String htmlUrl,
                                       Boolean fork,
                                       @JsonProperty("forks_count") Long forksCount,
                                       @JsonProperty("watchers_count") Long watchersCount,
                                       @JsonProperty("pushed_at") OffsetDateTime pushedAt,
                                       @JsonProperty("created_at") OffsetDateTime createdAt,
                                       @JsonProperty("updated_at") OffsetDateTime updatedAt) {
}
