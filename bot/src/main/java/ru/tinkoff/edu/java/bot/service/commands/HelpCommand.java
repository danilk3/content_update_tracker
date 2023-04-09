package ru.tinkoff.edu.java.bot.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.UserMessageProcessor;

@Component
public non-sealed class HelpCommand extends Command {

    private final UserMessageProcessor userMessageProcessor;

    public HelpCommand(UserMessageProcessor userMessageProcessor) {
        this.userMessageProcessor = userMessageProcessor;
    }

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "Display list of commands";
    }

    private String getCommands() {
        StringBuilder res = new StringBuilder();
        int counter = 1;
        for (Command command : userMessageProcessor.commands.values() ) {
            res.append(String.format("%d) <b>%s</b> - %s\n\n", counter++, command.command(), command.description()));
        }
        return res.toString();
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), getCommands()).parseMode(ParseMode.HTML);
    }
}
