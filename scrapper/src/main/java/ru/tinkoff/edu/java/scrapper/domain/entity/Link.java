package ru.tinkoff.edu.java.scrapper.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Link {

    @Id
    private Long linkId;

    private Long tgChatId;

    private String url;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
