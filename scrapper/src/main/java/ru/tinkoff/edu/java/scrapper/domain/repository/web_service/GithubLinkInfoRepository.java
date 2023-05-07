package ru.tinkoff.edu.java.scrapper.domain.repository.web_service;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.GitHubRepositoryLink;
import ru.tinkoff.edu.java.scrapper.domain.updater.UpdatableRepository;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubRepositoryResponse;

@Repository
public class GithubLinkInfoRepository implements UpdatableRepository<GitHubRepositoryLink, GitHubRepositoryResponse> {

    private final RowMapper<GitHubRepositoryLink> rowMapper = new DataClassRowMapper<>(GitHubRepositoryLink.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public GithubLinkInfoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GitHubRepositoryLink> findOldUpdated() {
        return jdbcTemplate.query(
            """
                select g.id, l.link_id, g.name, g.owner_name, g.fork, g.forks_count, g.watchers_count, g.pushed_at,
                g.created_at, g.open_issues_count, l.url, l.tg_chat_id
                from github_link_info g
                join link l on l.link_id = g.link_id
                where now() - l.updated_at >= interval '1 day'
                """,
            rowMapper
        );
    }

    @Override
    public GitHubRepositoryLink update(GitHubRepositoryResponse response, Long linkId) {
        return jdbcTemplate.queryForObject(
            "update github_link_info "
                + "set name = :name, fork = :fork, forks_count = :forksCount, watchers_count = :watchersCount,"
                + " pushed_at = :pushedAt, open_issues_count = :openIssuesCount"
                + "where link_id = :linkId",
            Map.of(
                "name",
                response.name(),
                "fork",
                response.fork(),
                "forksCount",
                response.forksCount(),
                "watchersCount",
                response.watchersCount(),
                "pushedAt",
                response.pushedAt(),
                "openIssuesCount",
                response.openIssuesCount(),
                "linkId",
                linkId
            ),
            rowMapper
        );
    }
}
