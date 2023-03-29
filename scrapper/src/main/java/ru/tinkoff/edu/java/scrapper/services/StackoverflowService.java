package ru.tinkoff.edu.java.scrapper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.clients.StackoverflowClient;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackoverflowQuestionResponse;

@Service
public class StackoverflowService {

    private final StackoverflowClient stackoverflowClient;

    @Autowired
    public StackoverflowService(StackoverflowClient stackoverflowClient) {
        this.stackoverflowClient = stackoverflowClient;
    }

    public StackoverflowQuestionResponse getStackoverflowQuestion(Long questionId) {
        return stackoverflowClient.getStackoverflowQuestion(questionId);
    }
}
