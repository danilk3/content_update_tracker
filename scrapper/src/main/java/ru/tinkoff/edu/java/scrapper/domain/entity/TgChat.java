package ru.tinkoff.edu.java.scrapper.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TgChat {

    @Id
    private Long chatId;

    private OffsetDateTime createdAt = OffsetDateTime.now();
}
