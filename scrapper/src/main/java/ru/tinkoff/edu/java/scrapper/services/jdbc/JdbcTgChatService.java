package ru.tinkoff.edu.java.scrapper.services.jdbc;

import java.util.Optional;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.repository.TgChatRepository;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatAlreadyExistsException;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;

public class JdbcTgChatService implements TgChatService {

    private final TgChatRepository tgChatRepository;

    public JdbcTgChatService(TgChatRepository tgChatRepository) {
        this.tgChatRepository = tgChatRepository;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void register(Long tgChatId) {
        Optional<TgChat> dbTgChat = tgChatRepository.findById(tgChatId);

        if (dbTgChat.isPresent()) {
            throw new ChatAlreadyExistsException(tgChatId);
        }

        tgChatRepository.add(tgChatId);
    }

    @Override
    public void unregister(Long tgChatId) {
        tgChatRepository.remove(tgChatId);
    }
}
