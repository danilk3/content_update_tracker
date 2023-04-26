package ru.tinkoff.edu.java.scrapper.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatAlreadyExistsException;
import ru.tinkoff.edu.java.scrapper.utils.IntegrationEnvironment;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class TgChatRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private TgChatRepositoryImpl tgChatRepository;

    @Transactional
    @Rollback
    @Test
    void addTest() {
        Long id = new Random().nextLong();
        tgChatRepository.add(id);

        List<TgChat> allChats = tgChatRepository.findAll();

        assertThat(allChats.size()).isEqualTo(1);

        assertThat(allChats.get(0).getChatId()).isEqualTo(id);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void addWithChatAlreadyExistsTest() {
        Long id1 = new Random().nextLong();
        tgChatRepository.add(id1);
        assertThatThrownBy(() -> tgChatRepository.add(id1))
                .isInstanceOf(UndeclaredThrowableException.class)
                .hasCauseInstanceOf(ChatAlreadyExistsException.class);
    }

    @Transactional
    @Rollback
    @Test
    void removeTest() {
        Long id1 = new Random().nextLong();
        tgChatRepository.add(id1);

        List<TgChat> allChats = tgChatRepository.findAll();
        assertThat(allChats.size()).isEqualTo(1);

        tgChatRepository.remove(id1);

        List<TgChat> allChatsAfterRemove = tgChatRepository.findAll();
        assertThat(allChatsAfterRemove.size()).isEqualTo(0);
    }

    @Transactional
    @Rollback
    @Test
    void findAllTest() {
        Long id1 = new Random().nextLong();
        Long id2 = new Random().nextLong();
        tgChatRepository.add(id1);
        tgChatRepository.add(id2);

        List<TgChat> allChats = tgChatRepository.findAll();

        assertThat(allChats.size()).isEqualTo(2);

        assertThat(allChats.get(0).getChatId()).isEqualTo(id1);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();

        assertThat(allChats.get(1).getChatId()).isEqualTo(id2);
        assertThat(allChats.get(0).getCreatedAt()).isNotNull();
    }
}
