package ru.tinkoff.edu.java.scrapper.domain.repository.jooq;

import java.time.OffsetDateTime;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records.LinkRecord;
import ru.tinkoff.edu.java.scrapper.domain.repository.LinkRepository;

@Repository
public class LinkRepositoryJooqImpl implements LinkRepository {

    private final DSLContext context;

    private final RecordMapper<LinkRecord, Link> mapper;

    public LinkRepositoryJooqImpl(DSLContext context) {
        this.context = context;
        mapper = linkRecord -> new Link(linkRecord.getLinkId().longValue(),
            linkRecord.getTgChatId(),
            linkRecord.getUrl(),
            linkRecord.getCreatedAt(),
            linkRecord.getUpdatedAt()
        );
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
