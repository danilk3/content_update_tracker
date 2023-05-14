package ru.tinkoff.edu.java.scrapper.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;

public interface TgChatRepository {
    RowMapper<TgChat> ROW_MAPPER = new DataClassRowMapper<>(TgChat.class);

    TgChat add(Long tgChatId);

    TgChat remove(Long chatId);

    List<TgChat> findAll();

    Optional<TgChat> findById(Long chatId);
}
