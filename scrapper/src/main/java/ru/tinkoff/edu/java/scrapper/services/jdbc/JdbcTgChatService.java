package ru.tinkoff.edu.java.scrapper.services.jdbc;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.repository.TgChatRepository;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;

@Service
public class JdbcTgChatService implements TgChatService {

    private final TgChatRepository tgChatRepository;

    public JdbcTgChatService(TgChatRepository tgChatRepository) {
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    public void register(Long tgChatId) {
        tgChatRepository.add(tgChatId);
    }

    @Override
    public void unregister(Long tgChatId) {
        tgChatRepository.remove(tgChatId);
    }
}
