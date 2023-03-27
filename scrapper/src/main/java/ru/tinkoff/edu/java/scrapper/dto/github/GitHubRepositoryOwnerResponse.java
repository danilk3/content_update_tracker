package ru.tinkoff.edu.java.scrapper.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubRepositoryOwnerResponse(String login,
                                            Long id,
                                            @JsonProperty("avatar_url") String avatarUrl,
                                            String url) {
}

