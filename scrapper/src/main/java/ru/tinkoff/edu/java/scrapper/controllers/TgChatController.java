package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;

@RestController
@RequestMapping("tg-chat")
public class TgChatController {

    private final TgChatService tgChatService;

    public TgChatController(TgChatService tgChatService) {
        this.tgChatService = tgChatService;
    }

    @PostMapping("{id}")
    public ResponseEntity<Void> registerChat(@PathVariable Long id) {
        tgChatService.register(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        tgChatService.unregister(id);
        return ResponseEntity.ok().build();
    }
}
