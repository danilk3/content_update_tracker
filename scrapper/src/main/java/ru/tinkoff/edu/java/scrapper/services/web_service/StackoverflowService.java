package ru.tinkoff.edu.java.scrapper.services.web_service;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.clients.BotClient;
import ru.tinkoff.edu.java.scrapper.clients.StackoverflowClient;
import ru.tinkoff.edu.java.scrapper.domain.entity.StackoverflowLink;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.updater.UpdatableRepository;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackoverflowItems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StackoverflowService implements UpdatableService {

    private final UpdatableRepository<StackoverflowLink, StackoverflowItems> stackoverflowRepository;

    private final StackoverflowClient stackoverflowClient;

    private final BotClient botClient;

    private final LinkRepository linkRepository;

    public StackoverflowService(UpdatableRepository<StackoverflowLink, StackoverflowItems> stackoverflowRepository, StackoverflowClient stackoverflowClient, BotClient botClient, LinkRepository linkRepository) {
        this.stackoverflowRepository = stackoverflowRepository;
        this.stackoverflowClient = stackoverflowClient;
        this.botClient = botClient;
        this.linkRepository = linkRepository;
    }


    @Override
    public void update() {
        List<StackoverflowLink> oldLinks = stackoverflowRepository.findOldUpdated();
        Map<String, List<StackoverflowLink>> needToSendToBot = new HashMap<>();

        for (StackoverflowLink stackoverflowLink : oldLinks) {
            StackoverflowItems response = stackoverflowClient.getStackoverflowQuestion(stackoverflowLink.getQuestionId()).items().get(0);

            if (!stackoverflowLink.isEqualToStackoverflowItems(response)) {
                StackoverflowLink savedToDbLink = stackoverflowRepository.update(response, stackoverflowLink.getLinkId());

                if (needToSendToBot.containsKey(savedToDbLink.getUrl())) {
                    needToSendToBot.get(savedToDbLink.getUrl()).add(savedToDbLink);
                } else {
                    needToSendToBot.put(savedToDbLink.getUrl(), List.of(savedToDbLink));
                }
                linkRepository.updateUpdatedTime(stackoverflowLink.getLinkId());
            }
        }

        for (Map.Entry<String, List<StackoverflowLink>> link : needToSendToBot.entrySet()) {
            Set<Long> tgChatIds = link.getValue().stream().map(StackoverflowLink::getTgChatId).collect(Collectors.toSet());

            StackoverflowLink updatableLink = link.getValue().get(0);

            botClient.sendUpdate(new LinkUpdate(updatableLink.getId(), updatableLink.getUrl(), "there are some updates)", tgChatIds.stream().toList()));
        }
    }
}
