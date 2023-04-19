package ru.tinkoff.edu.java.bot.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.LinkUpdate;
import ru.tinkoff.edu.java.bot.service.BotService;

@RestController
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("updates")
    public ResponseEntity<Void> sendUpdate(@RequestBody @Valid LinkUpdate request) {
        botService.notifyOfUpdates(request);
        return ResponseEntity.ok().build();
    }
}
