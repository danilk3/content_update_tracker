package ru.tinkoff.edu.java.scrapper.services.web_service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.clients.StackoverflowClient;
import ru.tinkoff.edu.java.scrapper.domain.entity.StackoverflowLink;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.updater.UpdatableRepository;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackoverflowItems;
import ru.tinkoff.edu.java.scrapper.models.MessageSaver;
import ru.tinkoff.edu.java.scrapper.services.BotUpdater;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class StackoverflowService implements UpdatableService {

    private final UpdatableRepository<StackoverflowLink, StackoverflowItems> stackoverflowRepository;

    private final StackoverflowClient stackoverflowClient;

    private final BotUpdater botUpdater;

    private final LinkRepository linkRepository;

    public StackoverflowService(UpdatableRepository<StackoverflowLink, StackoverflowItems> stackoverflowRepository, StackoverflowClient stackoverflowClient, BotUpdater botUpdater, @Qualifier("linkRepositoryImpl") LinkRepository linkRepository) {
        this.stackoverflowRepository = stackoverflowRepository;
        this.stackoverflowClient = stackoverflowClient;
        this.botUpdater = botUpdater;
        this.linkRepository = linkRepository;
    }


    @Override
    public void update() {
        List<StackoverflowLink> oldLinks = stackoverflowRepository.findOldUpdated();
        Map<String, List<MessageSaver<StackoverflowLink>>> needToSendToBot = new HashMap<>();

        for (StackoverflowLink stackoverflowLink : oldLinks) {
            StackoverflowItems response = stackoverflowClient.getStackoverflowQuestion(stackoverflowLink.getQuestionId()).items().get(0);

            String message = stackoverflowLink.getMessage(response);

            if (!isNull(message)) {
                StackoverflowLink savedToDbLink = stackoverflowRepository.update(response, stackoverflowLink.getLinkId());

                if (needToSendToBot.containsKey(savedToDbLink.getUrl())) {
                    needToSendToBot.get(savedToDbLink.getUrl()).add((new MessageSaver<>(savedToDbLink, message)));
                } else {
                    needToSendToBot.put(savedToDbLink.getUrl(), List.of((new MessageSaver<>(savedToDbLink, message))));
                }
                linkRepository.updateUpdatedTime(stackoverflowLink.getLinkId());
            }
        }

        for (Map.Entry<String, List<MessageSaver<StackoverflowLink>>> saver : needToSendToBot.entrySet()) {
            Set<Long> tgChatIds = saver.getValue().stream().map(el -> el.getValue().getTgChatId()).collect(Collectors.toSet());

            StackoverflowLink updatableLink = saver.getValue().get(0).getValue();
            String message = saver.getValue().get(0).getMessage();

            botUpdater.sendUpdate(new LinkUpdate(updatableLink.getId(), updatableLink.getUrl(), message, tgChatIds.stream().toList()));
        }
    }
}
