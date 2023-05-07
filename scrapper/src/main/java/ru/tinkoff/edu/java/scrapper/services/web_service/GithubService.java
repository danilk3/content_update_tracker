package ru.tinkoff.edu.java.scrapper.services.web_service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.domain.entity.GitHubRepositoryLink;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.updater.UpdatableRepository;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.models.MessageSaver;
import ru.tinkoff.edu.java.scrapper.services.BotUpdater;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class GithubService implements UpdatableService {

    private final UpdatableRepository<GitHubRepositoryLink, GitHubRepositoryResponse> gitHubRepository;

    private final GitHubClient gitHubClient;

    private final BotUpdater botUpdater;

    private final LinkRepository linkRepository;

    public GithubService(UpdatableRepository<GitHubRepositoryLink, GitHubRepositoryResponse> gitHubRepository, GitHubClient gitHubClient, BotUpdater botUpdater, @Qualifier("linkRepositoryImpl") LinkRepository linkRepository) {
        this.gitHubRepository = gitHubRepository;
        this.gitHubClient = gitHubClient;
        this.botUpdater = botUpdater;
        this.linkRepository = linkRepository;
    }

    @Transactional
    @Override
    public void update() {
        List<GitHubRepositoryLink> oldLinks = gitHubRepository.findOldUpdated();
        Map<String, List<MessageSaver<GitHubRepositoryLink>>> needToSendToBot = new HashMap<>();

        for (GitHubRepositoryLink githubLink : oldLinks) {
            GitHubRepositoryResponse response = gitHubClient.getGithubRepository(githubLink.getOwnerName(), githubLink.getName());

            String message = githubLink.getMessage(response);

            if (!isNull(message)) {
                GitHubRepositoryLink savedToDbLink = gitHubRepository.update(response, githubLink.getLinkId());
                if (needToSendToBot.containsKey(savedToDbLink.getUrl())) {
                    needToSendToBot.get(savedToDbLink.getUrl()).add(new MessageSaver<>(savedToDbLink, message));
                } else {
                    needToSendToBot.put(savedToDbLink.getUrl(), List.of(new MessageSaver<>(savedToDbLink, message)));
                }
            }
            linkRepository.updateUpdatedTime(githubLink.getLinkId());
        }

        for (Map.Entry<String, List<MessageSaver<GitHubRepositoryLink>>> saver : needToSendToBot.entrySet()) {
            Set<Long> tgChatIds = saver.getValue().stream().map(el -> el.getValue().getTgChatId()).collect(Collectors.toSet());

            GitHubRepositoryLink updatableLink = saver.getValue().get(0).getValue();
            String message = saver.getValue().get(0).getMessage();

            botUpdater.sendUpdate(new LinkUpdate(updatableLink.getId(), updatableLink.getUrl(), message, tgChatIds.stream().toList()));
        }
    }
}
