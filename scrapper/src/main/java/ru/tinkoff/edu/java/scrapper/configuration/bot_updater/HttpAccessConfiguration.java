package ru.tinkoff.edu.java.scrapper.configuration.bot_updater;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.clients.BotClient;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "useQueue", havingValue = "false")
public class HttpAccessConfiguration {

    @Bean
    public BotClient botUpdater(@Qualifier("botWebClient") WebClient webClient) {
        return new BotClient(webClient);
    }
}
