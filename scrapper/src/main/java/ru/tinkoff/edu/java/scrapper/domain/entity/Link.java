package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class Link {

    private Long linkId;

    private Long tgChatId;

    private String url;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
