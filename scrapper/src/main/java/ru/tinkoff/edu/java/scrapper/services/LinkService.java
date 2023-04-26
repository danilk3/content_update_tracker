package ru.tinkoff.edu.java.scrapper.services;

import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.ListLinksResponse;

public interface LinkService {

    LinkResponse addLink(Long tgChatId, String link);

    LinkResponse removeLink(Long tgChatId, String link);

    ListLinksResponse getAllWatchingLinks(Long tgChatId);
}
