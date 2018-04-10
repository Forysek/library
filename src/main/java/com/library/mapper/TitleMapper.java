package com.library.mapper;

import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {
    public static Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationDate(),
                titleDto.getBooksCopies());
    }

    public static TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationDate(),
                title.getBooksCopies());
    }

    public static List<TitleDto> mapToTitlesDtoList(final List<Title> titlesList) {
        return titlesList.stream()
                .map(m -> new TitleDto(m.getId(), m.getTitle(), m.getAuthor(), m.getPublicationDate(), m.getBooksCopies()))
                .collect(Collectors.toList());
    }
}
