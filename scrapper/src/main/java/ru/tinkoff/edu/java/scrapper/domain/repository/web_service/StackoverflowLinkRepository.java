package ru.tinkoff.edu.java.scrapper.domain.repository.web_service;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.StackoverflowLink;
import ru.tinkoff.edu.java.scrapper.domain.updater.UpdatableRepository;
import ru.tinkoff.edu.java.scrapper.dto.stackoverflow.StackoverflowItems;

@Repository
public class StackoverflowLinkRepository implements UpdatableRepository<StackoverflowLink, StackoverflowItems> {

    private final RowMapper<StackoverflowLink> rowMapper = new DataClassRowMapper<>(StackoverflowLink.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StackoverflowLinkRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StackoverflowLink> findOldUpdated() {
        return jdbcTemplate.query(
            """
                select s.id, l.link_id, s.question_id, s.tags, s.is_answered, s.view_count, s.answer_count,
                 s.score, s.creation_date, s.title, l.url, l.tg_chat_id
                from stackoverflow_link_info s
                join link l on l.link_id = s.link_id
                where now() - l.updated_at >= interval '1 day'
                """,
            rowMapper
        );
    }

    @Override
    public StackoverflowLink update(StackoverflowItems response, Long linkId) {
        return jdbcTemplate.queryForObject(
            """
                update stackoverflow_link_info
                set question_id = :questionId, tags = :tags, is_answered = :isAnswered, view_count = :viewCount,
                 answer_count = :answerCount, score = :score, title = :title
                where link_id = :linkId
                """,
            Map.of("question_id", response.questionId(), "tags", response.tags(), "is_answered", response.isAnswered(),
                "view_count", response.viewCount(), "answer_count", response.answerCount(), "score", response.score(),
                "title", response.title(), "linkId", linkId
            ),
            rowMapper
        );
    }
}
