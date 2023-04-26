package ru.tinkoff.edu.java.scrapper.domain.repository;

import lombok.SneakyThrows;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatNotExistsException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final TgChatRepository tgChatRepository;

    public LinkRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, TgChatRepository tgChatRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.tgChatRepository = tgChatRepository;
    }

    @Transactional
    @SneakyThrows
    @Override
    public Link add(Long tgChatId, String url) {
        Optional<TgChat> tgChatFromDb = tgChatRepository.findById(tgChatId);

        if (tgChatFromDb.isEmpty()) {
            throw new ChatNotExistsException(tgChatId);
        }

        return jdbcTemplate.queryForObject("insert into link(tg_chat_id, url) values(:tgChatId, :url) returning *",
                Map.of("tgChatId", tgChatId, "url", url),
                rowMapper);
    }

    @Override
    public Link remove(Long tgChatId, String url) {
        return jdbcTemplate.queryForObject("delete from link where tg_chat_id = :tgChatId and url = :url returning *",
                Map.of("tgChatId", tgChatId, "url", url),
                rowMapper);
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("select l.* from link l", rowMapper);
    }

    @SneakyThrows
    @Transactional
    @Override
    public List<Link> findAllByTgChatId(Long tgChatId) {
        Optional<TgChat> tgChatFromDb = tgChatRepository.findById(tgChatId);

        if (tgChatFromDb.isEmpty()) {
            throw new ChatNotExistsException(tgChatId);
        }

        return jdbcTemplate.query("select l.* from link l where tg_chat_id = :tgChatId",
                Map.of("tgChatId", tgChatId),
                rowMapper);
    }

    @Override
    public Link updateUpdatedTime(Long linkId) {
        return jdbcTemplate.queryForObject("update link set updated_at = now() where link_id = :linkId",
                Map.of("linkId", linkId), rowMapper);
    }
}
