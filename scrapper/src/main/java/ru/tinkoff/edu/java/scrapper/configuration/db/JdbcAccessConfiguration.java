package ru.tinkoff.edu.java.scrapper.configuration.db;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.LinkRepositoryImpl;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.TgChatRepositoryImpl;
import ru.tinkoff.edu.java.scrapper.mapper.LinkMapper;
import ru.tinkoff.edu.java.scrapper.services.LinkService;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;
import ru.tinkoff.edu.java.scrapper.services.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.services.jdbc.JdbcTgChatService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "databaseAccessType", havingValue = "jdbc")
public class JdbcAccessConfiguration {

    @Bean
    public LinkService linkService(LinkRepositoryImpl linkRepositoryImpl, TgChatRepositoryImpl tgChatRepositoryImpl, LinkMapper linkMapper) {
        return new JdbcLinkService(linkRepositoryImpl, tgChatRepositoryImpl, linkMapper);
    }

    @Bean
    public TgChatService tgChatService(TgChatRepositoryImpl tgChatRepositoryImpl) {
        return new JdbcTgChatService(tgChatRepositoryImpl);
    }
}
