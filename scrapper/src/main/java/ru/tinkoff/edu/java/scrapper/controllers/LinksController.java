package ru.tinkoff.edu.java.scrapper.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.dto.api.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.services.LinkService;

@RestController
@RequestMapping("links")
public class LinksController {

    private final LinkService linkService;

    public LinksController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    public ResponseEntity<ListLinksResponse> getAllWatchingLinks(@RequestHeader(name = "Tg-Chat-Id") Long tgChatId) {
        ListLinksResponse listLinksResponse = linkService.getAllWatchingLinks(tgChatId);
        return ResponseEntity.ok(listLinksResponse);
    }

    @PostMapping
    public ResponseEntity<LinkResponse> addLink(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestBody @Valid AddLinkRequest request
    ) {
        LinkResponse linkResponse = linkService.addLink(tgChatId, request.link());
        return ResponseEntity.ok(linkResponse);
    }

    @DeleteMapping
    public ResponseEntity<LinkResponse> removeLink(
        @RequestHeader("Tg-Chat-Id") Long tgChatId,
        @RequestBody @Valid RemoveLinkRequest request
    ) {
        LinkResponse linkResponse = linkService.removeLink(tgChatId, request.link());
        return ResponseEntity.ok(linkResponse);
    }
}
