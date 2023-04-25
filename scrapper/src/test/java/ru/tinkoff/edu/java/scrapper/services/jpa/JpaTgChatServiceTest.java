package ru.tinkoff.edu.java.scrapper.services.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.ScrapperApplication;
import ru.tinkoff.edu.java.scrapper.configuration.db.JdbcAccessConfiguration;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.repository.TgChatRepository;
import ru.tinkoff.edu.java.scrapper.utils.IntegrationEnvironment;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ScrapperApplication.class)
@Import(JdbcAccessConfiguration.class)
public class JpaTgChatServiceTest extends IntegrationEnvironment {

    @Autowired
    private JpaTgChatService tgChatService;

    @Autowired
    private TgChatRepository tgChatRepository;


    @Transactional
    @Rollback
    @Test
    void registerTest() {
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
    void unregisterTest() {
        Long id = new Random().nextLong();
        tgChatService.register(id);

        List<TgChat> allChats = tgChatRepository.findAll();

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).getChatId()).isEqualTo(id);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();

        tgChatService.unregister(id);
        List<TgChat> allChatsAfterRemove = tgChatRepository.findAll();
        assertThat(allChats.size()).isEqualTo(0);
    }
}
