package ru.tinkoff.edu.java.scrapper.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.api.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.RemoveLinkRequest;

import java.util.List;

@RestController
@RequestMapping("links")
public class LinksController {

    @GetMapping
    public ResponseEntity<ListLinksResponse> getAllWatchingLinks(@RequestHeader(name = "Tg-Chat-Id") Long tgChatId) {
        ListLinksResponse stub = new ListLinksResponse(List.of(new LinkResponse(tgChatId, "https://www.google.com/")), 1);
        return ResponseEntity.ok(stub);
    }

    @PostMapping
    public ResponseEntity<LinkResponse> addLink(@RequestHeader("Tg-Chat-Id") Long tgChatId, @RequestBody @Valid AddLinkRequest request) {
        LinkResponse stub = new LinkResponse(tgChatId, request.link());
        return ResponseEntity.ok(stub);
    }

    @DeleteMapping
    public ResponseEntity<LinkResponse> removeLink(@RequestHeader("Tg-Chat-Id") Long tgChatId, @RequestBody @Valid RemoveLinkRequest request) {
        LinkResponse stub = new LinkResponse(tgChatId, request.link());
        return ResponseEntity.ok(stub);
    }
}
