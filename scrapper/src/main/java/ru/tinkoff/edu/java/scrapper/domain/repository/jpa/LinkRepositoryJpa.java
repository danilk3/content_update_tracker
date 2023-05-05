package ru.tinkoff.edu.java.scrapper.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;

import java.util.List;

@Repository
public interface LinkRepositoryJpa extends JpaRepository<Link, Long> {

    Integer deleteByTgChatIdAndUrl(Long tgChatId, String url);

    List<Link> findAll();

    List<Link> findAllByTgChatId(Long tgChatId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update ru.tinkoff.edu.java.scrapper.domain.entity.Link link set link.updatedAt = now() where link.linkId = :linkId", nativeQuery = true)
    Link updateUpdatedTime(Long linkId);

}
