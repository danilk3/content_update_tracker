package ru.tinkoff.edu.java.scrapper.configuration.db;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.LinkRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.TgChatRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.mapper.LinkMapper;
import ru.tinkoff.edu.java.scrapper.services.LinkService;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;
import ru.tinkoff.edu.java.scrapper.services.jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.services.jpa.JpaTgChatService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "databaseAccessType", havingValue = "jpa")
public class JpaAccessConfiguration {

    @Bean
    public TgChatService tgChatService(TgChatRepositoryJpa tgChatRepository) {
        return new JpaTgChatService(tgChatRepository);
    }

    @Bean
    public LinkService linkService(
        LinkRepositoryJpa linkRepository,
        TgChatRepositoryJpa tgChatRepository,
        LinkMapper linkMapper
    ) {
        return new JpaLinkService(linkRepository, tgChatRepository, linkMapper);
    }
}
