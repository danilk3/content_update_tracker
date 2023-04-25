package ru.tinkoff.edu.java.scrapper.services.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.TgChatRepository;
import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.utils.IntegrationEnvironment;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JpaLinkServiceTest extends IntegrationEnvironment {

    @Autowired
    private JpaLinkService linkService;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private TgChatRepository tgChatRepository;

    @Transactional
    @Rollback
    @Test
    void addLinkTest() {
        Long tgChatId = new Random().nextLong();
        String url = "google.com";

        tgChatRepository.add(tgChatId);
        linkService.addLink(tgChatId, url);

        List<Link> allChats = linkRepository.findAll();

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).getLinkId()).isNotNull();
        assertThat(allChats.get(0).getTgChatId()).isEqualTo(tgChatId);
        assertThat(allChats.get(0).getUrl()).isEqualTo(url);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void removeLinkTest() {
        Long tgChatId = new Random().nextLong();
        String url = "google.com";

        tgChatRepository.add(tgChatId);
        linkService.addLink(tgChatId, url);

        List<Link> allChats = linkRepository.findAll();

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).getLinkId()).isNotNull();
        assertThat(allChats.get(0).getTgChatId()).isEqualTo(tgChatId);
        assertThat(allChats.get(0).getUrl()).isEqualTo(url);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();

        linkService.removeLink(tgChatId, url);

        List<Link> allChatsAfterRemove = linkRepository.findAll();

        assertThat(allChatsAfterRemove.size()).isEqualTo(0);
    }

    @Transactional
    @Rollback
    @Test
    void getAllWatchingLinksTest() {
        Long tgChatId = new Random().nextLong();
        String url = "google.com";

        tgChatRepository.add(tgChatId);
        linkService.addLink(tgChatId, url);

        List<LinkResponse> allChats = linkService.getAllWatchingLinks(tgChatId).links();

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).id()).isNotNull();
        assertThat(allChats.get(0).url()).isEqualTo(url);
    }
}
