package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;

@Repository
public interface TgChatRepositoryJpa extends JpaRepository<TgChat, Long> {

    List<TgChat> findAll();

    Integer deleteByChatId(Long chatId);

}
