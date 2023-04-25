package ru.tinkoff.edu.java.scrapper.configuration.db;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.TgChatRepositoryImpl;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.LinkRepositoryJooqImpl;
import ru.tinkoff.edu.java.scrapper.mapper.LinkMapper;
import ru.tinkoff.edu.java.scrapper.services.LinkService;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;
import ru.tinkoff.edu.java.scrapper.services.jdbc.JdbcTgChatService;
import ru.tinkoff.edu.java.scrapper.services.jooq.JooqLinkService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "databaseAccessType", havingValue = "jooq")
public class JooqAccessConfiguration {
    @Bean
    public LinkService linkService(LinkRepositoryJooqImpl linkRepositoryJooqImpl, LinkMapper linkMapper) {
        return new JooqLinkService(linkRepositoryJooqImpl, linkMapper);
    }

    @Bean
    public TgChatService tgChatService(TgChatRepositoryImpl tgChatRepositoryImpl) {
        return new JdbcTgChatService(tgChatRepositoryImpl);
    }
}
