package ru.tinkoff.edu.java.scrapper.dto.api;

import jakarta.validation.constraints.NotNull;

public record RemoveLinkRequest(@NotNull String link) {
}
