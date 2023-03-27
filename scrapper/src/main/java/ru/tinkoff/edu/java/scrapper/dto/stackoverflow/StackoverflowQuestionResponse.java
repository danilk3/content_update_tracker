package ru.tinkoff.edu.java.scrapper.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StackoverflowQuestionResponse(List<StackoverflowItems> items,
                                            @JsonProperty("has_more") Boolean hasMore,
                                            @JsonProperty("quota_max") Long quotaMax,
                                            @JsonProperty("quota_remaining") Long quotaRemaining) {
}
