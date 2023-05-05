package ru.tinkoff.edu.java.scrapper.services.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.configuration.db.JpaAccessConfiguration;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.TgChatRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.services.TgChatService;
import ru.tinkoff.edu.java.scrapper.utils.IntegrationEnvironment;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ScrapperApplication.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaAccessConfiguration.class})
@EnableAutoConfiguration
public class JpaTgChatServiceTest extends IntegrationEnvironment {

    @Autowired
    private TgChatRepositoryJpa tgChatRepository;

    @Autowired
    private TgChatService tgChatService;

    @Transactional
    @Test
    public void registerTest() {
        Long id = new Random().nextLong();
        tgChatService.register(id);

        List<TgChat> allChats = tgChatRepository.findAll();

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).getChatId()).isEqualTo(id);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    public void unregisterTest() throws InterruptedException {
        Long id = new Random().nextLong();
        tgChatService.register(id);

        List<TgChat> allChats = tgChatRepository.findAll();

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).getChatId()).isEqualTo(id);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();

        tgChatService.unregister(id);
        List<TgChat> allChatsAfterRemove = tgChatRepository.findAll();
        assertThat(allChatsAfterRemove.size()).isEqualTo(0);
    }
}
