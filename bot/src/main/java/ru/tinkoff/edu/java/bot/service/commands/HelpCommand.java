package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.models.CommandDescriptionEnum;
import ru.tinkoff.edu.java.bot.service.models.CommandNameEnum;

@Component
public non-sealed class HelpCommand extends Command {

    @Override
    public String command() {
        return CommandNameEnum.HELP.getValue();
    }

    @Override
    public String description() {
        return CommandDescriptionEnum.HELP.getValue();
    }

    private String getCommands() {
        StringBuilder res = new StringBuilder();
        int counter = 1;

        CommandNameEnum[] names = CommandNameEnum.class.getEnumConstants();
        CommandDescriptionEnum[] descriptions = CommandDescriptionEnum.class.getEnumConstants();

        for (int i = 0; i < names.length; i++) {
            res.append(String.format("%d) <b>%s</b> - %s\n\n", counter++, names[i].getValue(), descriptions[i].getValue()));
        }

        return res.toString();
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), getCommands()).parseMode(ParseMode.HTML);
    }
}
