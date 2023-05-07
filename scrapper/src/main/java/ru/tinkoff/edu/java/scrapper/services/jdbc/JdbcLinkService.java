package ru.tinkoff.edu.java.scrapper.services.jdbc;

import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.TgChatRepository;
import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatNotExistsException;
import ru.tinkoff.edu.java.scrapper.mapper.LinkMapper;
import ru.tinkoff.edu.java.scrapper.services.LinkService;

public class JdbcLinkService implements LinkService {

    private final LinkRepository linkRepository;

    private final TgChatRepository tgChatRepository;

    private final LinkMapper linkMapper;

    public JdbcLinkService(LinkRepository linkRepository, TgChatRepository tgChatRepository, LinkMapper linkMapper) {
        this.linkRepository = linkRepository;
        this.tgChatRepository = tgChatRepository;
        this.linkMapper = linkMapper;
    }

    @SneakyThrows @Transactional @Override public LinkResponse addLink(Long tgChatId, String link) {
        Optional<TgChat> tgChatFromDb = tgChatRepository.findById(tgChatId);

        if (tgChatFromDb.isEmpty()) {
            throw new ChatNotExistsException(tgChatId);
        }

        Link dbLink = linkRepository.add(tgChatId, link);
        LinkResponse linkResponse = linkMapper.toLinkResponse(dbLink);
        return linkResponse;
    }

    @Override public LinkResponse removeLink(Long tgChatId, String link) {
        Link dbLink = linkRepository.remove(tgChatId, link);
        LinkResponse linkResponse = linkMapper.toLinkResponse(dbLink);
        return linkResponse;
    }

    @SneakyThrows @Transactional @Override public ListLinksResponse getAllWatchingLinks(Long tgChatId) {
        Optional<TgChat> tgChatFromDb = tgChatRepository.findById(tgChatId);

        if (tgChatFromDb.isEmpty()) {
            throw new ChatNotExistsException(tgChatId);
        }

        List<Link> dbLinks = linkRepository.findAllByTgChatId(tgChatId);
        ListLinksResponse listLinksResponse = linkMapper.toListLinksResponse(dbLinks);
        return listLinksResponse;
    }
}
