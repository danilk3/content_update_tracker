package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.services.BotUpdater;

@Component
public class BotClient implements BotUpdater {

    private final WebClient webClient;

    public BotClient(@Qualifier("botWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void sendUpdate(LinkUpdate linkUpdate) {
        webClient
                .post()
                .uri("/updates")
                .body(BodyInserters.fromValue(linkUpdate))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
