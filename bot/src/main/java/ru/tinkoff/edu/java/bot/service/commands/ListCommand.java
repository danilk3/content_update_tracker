package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.models.CommandDescriptionEnum;
import ru.tinkoff.edu.java.bot.service.models.CommandNameEnum;

@Component
public non-sealed class ListCommand extends Command {

    @Override
    public String command() {
        return CommandNameEnum.LIST.getValue();
    }

    @Override
    public String description() {
        return CommandDescriptionEnum.LIST.getValue();
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(),
                "list of tracking url`s: \n1) https://stackoverflow.com/questions/49645510/telegram-bot-send-photo-by-url-returns-bad-request-wrong-file-identifier-http");
    }
}
