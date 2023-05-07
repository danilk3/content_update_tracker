package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.dto.LinkUpdate;

@Service
public class BotService {

    private final Bot bot;

    public BotService(Bot bot) {
        this.bot = bot;
    }

    public void notifyOfUpdates(LinkUpdate update) {
        update.tgChatIds().forEach(id -> bot.execute(new SendMessage(id, createMessage(update))));
    }

    private String createMessage(LinkUpdate update) {
        return String.format(
            "Hello! There are a few changes related to the link %s \n%s",
            update.url(),
            update.description()
        );
    }

}
