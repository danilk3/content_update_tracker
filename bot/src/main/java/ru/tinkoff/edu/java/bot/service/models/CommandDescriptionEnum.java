package ru.tinkoff.edu.java.bot.service.models;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandDescriptionEnum {
    HELP("Display list of commands"),
    START("Starts the bot"),
    LIST("Show the list of tracked links"),
    TRACK("Start tracking the link"),
    UNTRACK("Stop tracking the link");

    private String value;
}
