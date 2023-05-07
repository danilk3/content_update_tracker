package ru.tinkoff.edu.java.scrapper.domain.repository.jdbc;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LinkRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Link add(Long tgChatId, String url) {
        return jdbcTemplate.queryForObject(
            "insert into link(tg_chat_id, url) values(:tgChatId, :url) returning *",
            Map.of("tgChatId", tgChatId, "url", url),
            ROW_MAPPER
        );
    }

    @Override
    public Link remove(Long tgChatId, String url) {
        return jdbcTemplate.queryForObject(
            "delete from link where tg_chat_id = :tgChatId and url = :url returning *",
            Map.of("tgChatId", tgChatId, "url", url),
            ROW_MAPPER
        );
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("select l.* from link l", ROW_MAPPER);
    }

    @Override
    public List<Link> findAllByTgChatId(Long tgChatId) {
        return jdbcTemplate.query(
            "select l.* from link l where tg_chat_id = :tgChatId",
            Map.of("tgChatId", tgChatId),
            ROW_MAPPER
        );
    }

    @Override
    public Link updateUpdatedTime(Long linkId) {
        return jdbcTemplate.queryForObject("update link set updated_at = now() where link_id = :linkId",
            Map.of("linkId", linkId), ROW_MAPPER
        );
    }
}
