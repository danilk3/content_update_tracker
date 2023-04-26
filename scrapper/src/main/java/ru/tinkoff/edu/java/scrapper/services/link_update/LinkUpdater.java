package ru.tinkoff.edu.java.scrapper.services.link_update;

import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;

public interface LinkUpdater {
    int update(LinkUpdate linkUpdate);
}
