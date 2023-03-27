package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LinkUpdate(@NotNull Long id,
                         @NotNull String url,
                         @NotNull String description,
                         @NotNull List<Long> tgChatIds) {
}
