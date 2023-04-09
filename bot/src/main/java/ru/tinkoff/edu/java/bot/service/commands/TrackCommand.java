package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public non-sealed class TrackCommand extends Command {
    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Start tracking the link";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "Enter url to track")
                .replyToMessageId(update.message().messageId())
                .replyMarkup(new ForceReply());
    }
}
