package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Link {

    private Long linkId;

    private Long tgChatId;

    private String url;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
