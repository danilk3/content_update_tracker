package ru.tinkoff.edu.java.scrapper.domain.repository;

import java.util.List;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;

public interface LinkRepository {
    RowMapper<Link> ROW_MAPPER = new DataClassRowMapper<>(Link.class);

    Link add(Long tgChatId, String url);

    Link remove(Long tgChatId, String url);

    List<Link> findAll();

    List<Link> findAllByTgChatId(Long tgChatId);

    Link updateUpdatedTime(Long linkId);
}
