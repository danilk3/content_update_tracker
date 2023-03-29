package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackoverflowQuestionResponse;

@Component
public class StackoverflowClient {

    private final WebClient webClient;

    @Autowired
    public StackoverflowClient(@Qualifier("stackoverflowWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public StackoverflowQuestionResponse getStackoverflowQuestion(Long questionId) {
        return webClient
                .get()
                .uri(String.format("questions/%d?site=stackoverflow", questionId))
                .retrieve()
                .bodyToMono(StackoverflowQuestionResponse.class)
                .block();
    }
}
