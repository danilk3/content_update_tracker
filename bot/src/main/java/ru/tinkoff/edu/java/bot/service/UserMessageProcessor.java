package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.service.commands.Command;
import ru.tinkoff.edu.java.link_parser.Parser;
import static java.util.Objects.isNull;

@Service
public class UserMessageProcessor {

    public final Map<String, Command> commands;

    public UserMessageProcessor(@Qualifier("commandMap") Map<String, Command> commandMap) {
        this.commands = commandMap;
    }

    private String isReply(Update update) {
        String replyMessage = null;
        Message reply = update.message().replyToMessage();
        if (!isNull(reply) && reply.text().equals("Enter url to track")) {
            Parser parser = new Parser(update.message().text());
            if (!isNull(parser.parse())) {
                replyMessage = "Url successfully added to track";
            } else {
                replyMessage = "Url is not valid :( Try again";
            }
        } else if (!isNull(reply) && reply.text().equals("Enter url to untrack")) {
            replyMessage = "Url successfully untracked";
        }

        return replyMessage;
    }

    public SendMessage process(Update update) {
        String replyMessage = isReply(update);
        if (!isNull(replyMessage)) {
            return new SendMessage(update.message().chat().id(), replyMessage);
        }

        Command command = commands.get(update.message().text());

        if (isNull(command)) {
            return new SendMessage(
                update.message().chat().id(),
                "Unknown command :("
            );
        }

        return command.handle(update);
    }
}
