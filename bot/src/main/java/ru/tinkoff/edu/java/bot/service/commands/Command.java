package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public abstract sealed class Command permits
        StartCommand, HelpCommand, ListCommand, TrackCommand, UntrackCommand {

    public abstract String command();

    public abstract String description();

    public abstract SendMessage handle(Update update);

    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
