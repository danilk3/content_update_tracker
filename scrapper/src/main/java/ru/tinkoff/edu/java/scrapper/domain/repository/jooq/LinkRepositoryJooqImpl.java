package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.LinkRecord;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

import java.sql.Connection;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class LinkRepositoryJooqImpl implements LinkRepository {

    private final Connection connection;

    private final DSLContext context;

    private final RecordMapper<LinkRecord, Link> mapper;

    public LinkRepositoryJooqImpl(Connection connection) {
        this.connection = connection;
        this.context = DSL.using(connection, SQLDialect.POSTGRES);
        mapper = linkRecord -> new Link(linkRecord.getLinkId().longValue(), linkRecord.getTgChatId(), linkRecord.getUrl(), linkRecord.getCreatedAt(), linkRecord.getUpdatedAt());
    }

    @Override
    public Link add(Long tgChatId, String url) {
        return context.insertInto(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK)
                .set(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK.URL, url)
                .set(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK.TG_CHAT_ID, tgChatId)
                .returning()
                .fetchSingle(this.mapper);
    }

    @Override
    public Link remove(Long tgChatId, String url) {
        context.deleteFrom(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK)
                .where(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK.TG_CHAT_ID.eq(tgChatId))
                .and(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK.URL.eq(url))
                .execute();
        return null;
    }

    @Override
    public List<Link> findAll() {
        return context
                .selectFrom(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK)
                .fetch(this.mapper);
    }

    @Override
    public List<Link> findAllByTgChatId(Long tgChatId) {
        return context
                .selectFrom(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK)
                .where(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK.TG_CHAT_ID.eq(tgChatId))
                .fetch(this.mapper);
    }

    @Override
    public Link updateUpdatedTime(Long linkId) {
        context
                .update(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK)
                .set(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK.UPDATED_AT, OffsetDateTime.now())
                .where(ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK.LINK_ID.eq(Math.toIntExact(linkId)))
                .execute();
        return null;
    }

}
