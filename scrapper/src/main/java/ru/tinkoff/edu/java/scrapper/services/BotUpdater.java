package ru.tinkoff.edu.java.scrapper.services;

import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;

public interface BotUpdater {

    void sendUpdate(LinkUpdate update);
}
