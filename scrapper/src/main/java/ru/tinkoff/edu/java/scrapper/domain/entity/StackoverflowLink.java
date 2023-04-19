package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.Data;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackoverflowItems;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class StackoverflowLink {

    private Long id;

    private Long questionId;

    private List<String> tags;

    private Boolean isAnswered;

    private Long viewCount;

    private Long answerCount;

    private Integer score;

    private OffsetDateTime creationDate;

    private String title;

    private Long linkId;

    private String url;

    private Long tgChatId;

    public String getMessage(StackoverflowItems item) {
        String message = "";

        message += item.tags().equals(tags) ? "" : String.format("tags = %s ; ", item.tags());
        message += item.isAnswered().equals(isAnswered) ? "" : String.format("openIssuesCount = %s ; ", item.isAnswered());
        message += item.viewCount().equals(viewCount) ? "" : String.format("viewCount = %d ; ", item.viewCount());
        message += item.answerCount().equals(answerCount) ? "" : String.format("answerCount = %d ; ", item.answerCount());
        message += item.score().equals(score) ? "" : String.format("score = %d ; ", item.score());
        message += item.title().equals(title) ? "" : String.format("score = %s ; ", item.title());

        return message.isEmpty() ? null : message;
    }
}
