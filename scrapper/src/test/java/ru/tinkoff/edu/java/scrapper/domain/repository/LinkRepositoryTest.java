package ru.tinkoff.edu.java.scrapper.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatNotExistsException;
import ru.tinkoff.edu.java.scrapper.utils.IntegrationEnvironment;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
public class LinkRepositoryTest extends IntegrationEnvironment {
    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private TgChatRepository tgChatRepository;

    @Transactional
    @Rollback
    @Test
    void addTest() {
        Long tgChatId = new Random().nextLong();
        String url = "google.com";

        tgChatRepository.add(tgChatId);
        linkRepository.add(tgChatId, url);

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
    void addWhenChatDoesNotExistsTest() {
        Long tgChatId = new Random().nextLong();
        String url = "google.com";

        assertThatThrownBy(() -> linkRepository.add(tgChatId, url))
                .isInstanceOf(UndeclaredThrowableException.class)
                .hasCauseInstanceOf(ChatNotExistsException.class);
    }

    @Transactional
    @Rollback
    @Test
    void removeTest() {
        Long tgChatId = new Random().nextLong();
        String url = "google.com";

        tgChatRepository.add(tgChatId);
        linkRepository.add(tgChatId, url);

        List<Link> allChats = linkRepository.findAll();
        assertThat(allChats.size()).isEqualTo(1);

        linkRepository.remove(tgChatId, url);

        List<Link> allChatsAfterRemove = linkRepository.findAll();
        assertThat(allChatsAfterRemove.size()).isEqualTo(0);
    }

    @Transactional
    @Rollback
    @Test
    void findAllTest() {
        Long tgChatId = new Random().nextLong();
        String url = "google.com";
        String url1 = "yandex.com";

        tgChatRepository.add(tgChatId);
        linkRepository.add(tgChatId, url);
        linkRepository.add(tgChatId, url1);

        List<Link> allChats = linkRepository.findAll();

        assertThat(allChats.size()).isEqualTo(2);

        assertThat(allChats.get(0).getLinkId()).isNotNull();
        assertThat(allChats.get(0).getTgChatId()).isEqualTo(tgChatId);
        assertThat(allChats.get(0).getUrl()).isEqualTo(url);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();

        assertThat(allChats.get(1).getLinkId()).isNotNull();
        assertThat(allChats.get(1).getTgChatId()).isEqualTo(tgChatId);
        assertThat(allChats.get(1).getUrl()).isEqualTo(url1);
        assertThat(allChats.get(1).getCreatedAt()).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void findAllByIdTest() {
        Long tgChatId = new Random().nextLong();
        Long tgChatId1 = new Random().nextLong();
        String url = "google.com";
        String url1 = "yandex.com";

        tgChatRepository.add(tgChatId);
        tgChatRepository.add(tgChatId1);
        linkRepository.add(tgChatId, url);
        linkRepository.add(tgChatId1, url1);

        List<Link> allChats = linkRepository.findAllByTgChatId(tgChatId);

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).getLinkId()).isNotNull();
        assertThat(allChats.get(0).getTgChatId()).isEqualTo(tgChatId);
        assertThat(allChats.get(0).getUrl()).isEqualTo(url);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void findAllByIdWhenChatNotExistsTest() {
        assertThatThrownBy(() -> linkRepository.findAllByTgChatId(new Random().nextLong()))
                .isInstanceOf(UndeclaredThrowableException.class)
                .hasCauseInstanceOf(ChatNotExistsException.class);
    }
}
