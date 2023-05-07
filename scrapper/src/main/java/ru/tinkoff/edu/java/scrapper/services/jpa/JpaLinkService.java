package ru.tinkoff.edu.java.scrapper.services.jpa;

import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.LinkRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.TgChatRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatNotExistsException;
import ru.tinkoff.edu.java.scrapper.mapper.LinkMapper;
import ru.tinkoff.edu.java.scrapper.services.LinkService;

public class JpaLinkService implements LinkService {

    private final LinkRepositoryJpa linkRepository;

    private final TgChatRepositoryJpa tgChatRepository;

    private final LinkMapper linkMapper;

    public JpaLinkService(
        LinkRepositoryJpa linkRepository,
        TgChatRepositoryJpa tgChatRepository,
        LinkMapper linkMapper
    ) {
        this.linkRepository = linkRepository;
        this.tgChatRepository = tgChatRepository;
        this.linkMapper = linkMapper;
    }

    @SneakyThrows
    @Transactional
    @Override
    public LinkResponse addLink(Long tgChatId, String link) {
        Optional<TgChat> tgChatFromDb = tgChatRepository.findById(tgChatId);

        if (tgChatFromDb.isEmpty()) {
            throw new ChatNotExistsException(tgChatId);
        }

        Link linkToSave = new Link();
        linkToSave.setTgChatId(tgChatId);
        linkToSave.setUrl(link);

        Link dbLink = linkRepository.save(linkToSave);
        LinkResponse linkResponse = linkMapper.toLinkResponse(dbLink);
        return linkResponse;
    }

    @Override
    public LinkResponse removeLink(Long tgChatId, String link) {
        linkRepository.deleteByTgChatIdAndUrl(tgChatId, link);
        return linkMapper.toLinkResponse(null);
    }

    @Transactional
    @SneakyThrows
    @Override
    public ListLinksResponse getAllWatchingLinks(Long tgChatId) {
        Optional<TgChat> tgChatFromDb = tgChatRepository.findById(tgChatId);

        if (tgChatFromDb.isEmpty()) {
            throw new ChatNotExistsException(tgChatId);
        }

        List<Link> dbLinks = linkRepository.findAllByTgChatId(tgChatId);
        ListLinksResponse listLinksResponse = linkMapper.toListLinksResponse(dbLinks);
        return listLinksResponse;
    }
}
