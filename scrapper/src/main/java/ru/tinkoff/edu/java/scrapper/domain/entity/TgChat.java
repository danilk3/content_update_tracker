package ru.tinkoff.edu.java.scrapper.domain.entity;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TgChat {

    private Long chatId;

    private OffsetDateTime createdAt;
}
