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

    public boolean isEqualToStackoverflowItems(StackoverflowItems item) {
        return item.tags().equals(tags) &&
                item.isAnswered().equals(isAnswered) &&
                item.viewCount().equals(viewCount) &&
                item.answerCount().equals(answerCount) &&
                item.score().equals(score) &&
                item.title().equals(title);
    }
}
