package ru.tinkoff.edu.java.scrapper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tinkoff.edu.java.scrapper.domain.entity.Link;
import ru.tinkoff.edu.java.scrapper.dto.api.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.api.ListLinksResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    @Mapping(target = "id", source = "linkId")
    LinkResponse toLinkResponse(Link link);

    default ListLinksResponse toListLinksResponse(List<Link> links) {
        return new ListLinksResponse(links.stream().map(this::toLinkResponse).toList(), links.size());
    }
}
