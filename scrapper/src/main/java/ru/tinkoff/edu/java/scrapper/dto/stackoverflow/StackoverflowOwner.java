package ru.tinkoff.edu.java.scrapper.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StackoverflowOwner(@JsonProperty("account_id") Long accountId,
                                 Integer reputation,
                                 @JsonProperty("user_id") Long userId,
                                 @JsonProperty("user_type") String userType,
                                 @JsonProperty("profile_image") String profileImage,
                                 @JsonProperty("display_name") String displayName,
                                 String link) {
}
