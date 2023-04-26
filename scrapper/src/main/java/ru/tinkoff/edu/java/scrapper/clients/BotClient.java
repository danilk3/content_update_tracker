package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;

@Component
public class BotClient {

    private final WebClient webClient;

    public BotClient(@Qualifier("botWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public int sendUpdate(LinkUpdate linkUpdate) {
        return webClient
                .post()
                .uri("/updates")
                .body(BodyInserters.fromValue(linkUpdate))
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode().value();
    }
}
