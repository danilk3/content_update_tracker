package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.models.CommandDescriptionEnum;
import ru.tinkoff.edu.java.bot.service.models.CommandNameEnum;

@Component
public non-sealed class StartCommand extends Command {

    @Override
    public String command() {
        return CommandNameEnum.START.getValue();
    }

    @Override
    public String description() {
        return CommandDescriptionEnum.START.getValue();
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(
                update.message().chat().id(),
                "Hello, " + update.message().chat().firstName())
                .replyMarkup(new ReplyKeyboardMarkup(
                        new KeyboardButton(CommandNameEnum.START.getValue()),
                        new KeyboardButton(CommandNameEnum.HELP.getValue()),
                        new KeyboardButton(CommandNameEnum.LIST.getValue()),
                        new KeyboardButton(CommandNameEnum.TRACK.getValue()),
                        new KeyboardButton(CommandNameEnum.UNTRACK.getValue()))
                        .oneTimeKeyboard(false)
                        .resizeKeyboard(true)
                );
    }
}
