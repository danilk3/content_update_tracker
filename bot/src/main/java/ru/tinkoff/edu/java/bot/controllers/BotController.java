package ru.tinkoff.edu.java.bot.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.LinkUpdate;

@RestController
public class BotController {
    @PostMapping("updates")
    public ResponseEntity<Void> sendUpdate(@RequestBody @Valid LinkUpdate request) {
        return ResponseEntity.ok().build();
    }
}
