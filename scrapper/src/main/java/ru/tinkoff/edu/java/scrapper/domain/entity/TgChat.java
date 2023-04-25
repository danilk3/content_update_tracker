package ru.tinkoff.edu.java.scrapper.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TgChat {

    @Id
    private Long chatId;

    private OffsetDateTime createdAt;
}
