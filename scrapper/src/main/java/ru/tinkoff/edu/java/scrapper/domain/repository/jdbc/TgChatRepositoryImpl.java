package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.TgChat;
import ru.tinkoff.edu.java.scrapper.domain.repository.TgChatRepository;

@Repository
public class TgChatRepositoryImpl implements TgChatRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TgChatRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TgChat add(Long tgChatId) {
        return jdbcTemplate.queryForObject(
            "insert into tg_chat(chat_id) values(:chatId) returning *",
            Map.of("chatId", tgChatId),
            ROW_MAPPER
        );
    }

    @Override
    public TgChat remove(Long chatId) {
        return jdbcTemplate.queryForObject(
            "delete from tg_chat where chat_id = :chatId returning *",
            Map.of("chatId", chatId),
            ROW_MAPPER
        );
    }

    @Override
    public List<TgChat> findAll() {
        return jdbcTemplate.query("select t.* from tg_chat t", ROW_MAPPER);
    }

    @Override
    public Optional<TgChat> findById(Long chatId) {
        return Optional.ofNullable(
            DataAccessUtils.singleResult(
                jdbcTemplate.query(
                    "select t.* from tg_chat t where chat_id = :chatId",
                    Map.of("chatId", chatId),
                    ROW_MAPPER
                )
            )
        );
    }
}
