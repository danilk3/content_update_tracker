package ru.tinkoff.edu.java.scrapper.services.jpa;

import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.TgChatRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;

public class JpaTgChatService implements TgChatService {

    private final TgChatRepositoryJpa tgChatRepository;

    public JpaTgChatService(TgChatRepositoryJpa tgChatRepository) {
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    public void register(Long tgChatId) {
        TgChat chat = new TgChat();
        chat.setChatId(tgChatId);
        tgChatRepository.save(chat);
    }

    @Override
    public void unregister(Long tgChatId) {
        tgChatRepository.deleteByChatId(tgChatId);
    }
}
