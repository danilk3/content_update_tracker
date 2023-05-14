package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.models.CommandDescriptionEnum;
import ru.tinkoff.edu.java.bot.service.models.CommandNameEnum;

@Component
public non-sealed class UntrackCommand extends Command {
    @Override
    public String command() {
        return CommandNameEnum.UNTRACK.toString();
    }

    @Override
    public String description() {
        return CommandDescriptionEnum.UNTRACK.getValue();
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "Enter url to untrack")
            .replyToMessageId(update.message().messageId())
            .replyMarkup(new ForceReply());
    }
}
