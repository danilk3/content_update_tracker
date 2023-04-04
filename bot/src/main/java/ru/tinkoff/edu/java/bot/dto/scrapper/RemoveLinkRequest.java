package ru.tinkoff.edu.java.bot.dto.scrapper;

import jakarta.validation.constraints.NotNull;

public record RemoveLinkRequest(@NotNull String link) {
}
