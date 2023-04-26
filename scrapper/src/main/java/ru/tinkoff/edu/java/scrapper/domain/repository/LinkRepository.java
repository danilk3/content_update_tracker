package ru.tinkoff.edu.java.scrapper.domain.repository;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;

import java.util.List;

public interface LinkRepository {
    RowMapper<Link> rowMapper = new DataClassRowMapper<>(Link.class);

    Link add(Long tgChatId, String url);

    Link remove(Long tgChatId, String url);

    List<Link> findAll();

    List<Link> findAllByTgChatId(Long tgChatId);

    Link updateUpdatedTime(Long linkId);
}
