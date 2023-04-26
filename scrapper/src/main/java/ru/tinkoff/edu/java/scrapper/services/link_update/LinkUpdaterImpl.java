package ru.tinkoff.edu.java.scrapper.services.link_update;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.clients.BotClient;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;

@Service
public class LinkUpdaterImpl implements LinkUpdater {

    private final BotClient botClient;

    public LinkUpdaterImpl(BotClient botClient) {
        this.botClient = botClient;
    }

    @Override
    public int update(LinkUpdate linkUpdate) {
        return botClient.sendUpdate(linkUpdate);
    }
}
