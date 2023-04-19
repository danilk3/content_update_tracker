package ru.tinkoff.edu.java.scrapper.services.jooq;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.mapper.LinkMapper;
import ru.tinkoff.edu.java.scrapper.services.LinkService;

import java.util.List;

@Service
public class JooqLinkService implements LinkService {

    private final LinkRepository linkRepository;

    private final LinkMapper linkMapper;

    public JooqLinkService(@Qualifier("linkRepositoryJooqImpl") LinkRepository linkRepository, LinkMapper linkMapper) {
        this.linkRepository = linkRepository;
        this.linkMapper = linkMapper;
    }

    @Override
    public LinkResponse addLink(Long tgChatId, String link) {
        Link dbLink = linkRepository.add(tgChatId, link);
        LinkResponse linkResponse = linkMapper.toLinkResponse(dbLink);
        return linkResponse;
    }

    @Override
    public LinkResponse removeLink(Long tgChatId, String link) {
        Link dbLink = linkRepository.remove(tgChatId, link);
        LinkResponse linkResponse = linkMapper.toLinkResponse(dbLink);
        return linkResponse;
    }

    @Override
    public ListLinksResponse getAllWatchingLinks(Long tgChatId) {
        List<Link> dbLinks = linkRepository.findAllByTgChatId(tgChatId);
        ListLinksResponse listLinksResponse = linkMapper.toListLinksResponse(dbLinks);
        return listLinksResponse;
    }
}
