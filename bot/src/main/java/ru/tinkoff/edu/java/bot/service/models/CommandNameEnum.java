package ru.tinkoff.edu.java.bot.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandNameEnum {

    HELP("/help"),
    START("/start"),
    LIST("/list"),
    TRACK("/track"),
    UNTRACK("/untrack");

    private String value;

}
