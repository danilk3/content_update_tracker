package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackoverflowQuestionResponse;
import ru.tinkoff.edu.java.scrapper.services.StackoverflowService;

@RestController
@RequestMapping("api/stack")
public class StackoverflowController {

    private final StackoverflowService stackoverflowService;

    @Autowired
    public StackoverflowController(StackoverflowService stackoverflowService) {
        this.stackoverflowService = stackoverflowService;
    }

    @GetMapping("question")
    public ResponseEntity<StackoverflowQuestionResponse> getStackoverflowQuestion(@RequestParam Long questionId) {
        return ResponseEntity.ok(stackoverflowService.getStackoverflowQuestion(questionId));
    }
}
