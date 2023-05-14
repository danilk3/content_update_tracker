package ru.tinkoff.edu.java.scrapper.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;

public record StackoverflowItems(List<String> tags,
                                 @JsonProperty("is_answered") Boolean isAnswered,
                                 @JsonProperty("view_count") Long viewCount,
                                 @JsonProperty("answer_count") Long answerCount,
                                 Integer score,
                                 @JsonProperty("creation_date") OffsetDateTime creationDate,
                                 @JsonProperty("question_id") Long questionId,
                                 String title) {
}
