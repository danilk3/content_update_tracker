package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;

import java.util.List;

@Repository
public interface TgChatRepositoryJpa extends JpaRepository<TgChat, Long> {

    List<TgChat> findAll();

    TgChat deleteByChatId(Long chatId);

}
