package ru.tinkoff.edu.java.bot.client;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.dto.scrapper.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.scrapper.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.scrapper.ListLinksResponse;
import ru.tinkoff.edu.java.bot.dto.scrapper.RemoveLinkRequest;

@Component
public class ScrapperClient {

    private final WebClient webClient;

    @Autowired
    public ScrapperClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ListLinksResponse getAllWatchingLinks(Long tgChatId) {
        return webClient
            .get()
            .uri("/links")
            .header("Tg-Chat-Id", tgChatId.toString())
            .retrieve()
            .bodyToMono(ListLinksResponse.class)
            .block();
    }

    public LinkResponse addLink(Long tgChatId, AddLinkRequest addLinkRequest) {
        return webClient
            .post()
            .uri("/links")
            .header("Tg-Chat-Id", tgChatId.toString())
            .body(BodyInserters.fromValue(addLinkRequest))
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
    }

    public LinkResponse removeLink(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return webClient
            .method(HttpMethod.DELETE)
            .uri("/links")
            .header("Tg-Chat-Id", tgChatId.toString())
            .body(BodyInserters.fromValue(removeLinkRequest))
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
    }

    public void registerChat(Long id) {
        webClient
            .post()
            .uri("/tg-chat", Map.of("id", id.toString()))
            .retrieve();
    }

    public void deleteChat(Long id) {
        webClient
            .method(HttpMethod.DELETE)
            .uri("/tg-chat", Map.of("id", id.toString()))
            .retrieve();
    }
}
