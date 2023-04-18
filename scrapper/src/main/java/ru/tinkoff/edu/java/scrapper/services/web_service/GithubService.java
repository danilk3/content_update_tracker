package ru.tinkoff.edu.java.scrapper.services.web_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.clients.BotClient;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.domain.entity.GitHubRepositoryLink;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.updater.UpdatableRepository;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubRepositoryResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GithubService implements UpdatableService {

    private final UpdatableRepository<GitHubRepositoryLink, GitHubRepositoryResponse> gitHubRepository;

    private final GitHubClient gitHubClient;

    private final BotClient botClient;

    private final LinkRepository linkRepository;

    public GithubService(UpdatableRepository<GitHubRepositoryLink, GitHubRepositoryResponse> gitHubRepository, GitHubClient gitHubClient, BotClient botClient, LinkRepository linkRepository) {
        this.gitHubRepository = gitHubRepository;
        this.gitHubClient = gitHubClient;
        this.botClient = botClient;
        this.linkRepository = linkRepository;
    }

    @Transactional
    @Override
    public void update() {
        List<GitHubRepositoryLink> oldLinks = gitHubRepository.findOldUpdated();
        Map<String, List<GitHubRepositoryLink>> needToSendToBot = new HashMap<>();

        for (GitHubRepositoryLink githubLink : oldLinks) {
            GitHubRepositoryResponse response = gitHubClient.getGithubRepository(githubLink.getOwnerName(), githubLink.getName());

            if (!githubLink.isEqualToGitHubRepositoryResponse(response)) {
                GitHubRepositoryLink savedToDbLink = gitHubRepository.update(response, githubLink.getLinkId());
                if (needToSendToBot.containsKey(savedToDbLink.getUrl())) {
                    needToSendToBot.get(savedToDbLink.getUrl()).add(savedToDbLink);
                } else {
                    needToSendToBot.put(savedToDbLink.getUrl(), List.of(savedToDbLink));
                }
            }
            linkRepository.updateUpdatedTime(githubLink.getLinkId());
        }

        for (Map.Entry<String, List<GitHubRepositoryLink>> link : needToSendToBot.entrySet()) {
            Set<Long> tgChatIds = link.getValue().stream().map(GitHubRepositoryLink::getTgChatId).collect(Collectors.toSet());

            GitHubRepositoryLink updatableLink = link.getValue().get(0);

            botClient.sendUpdate(new LinkUpdate(updatableLink.getId(), updatableLink.getUrl(), "there are some updates)", tgChatIds.stream().toList()));
        }
    }
}
