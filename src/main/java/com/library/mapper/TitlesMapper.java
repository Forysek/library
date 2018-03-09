package com.library.mapper;

import com.library.domain.Titles;
import com.library.domain.dto.TitlesDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitlesMapper {
    public Titles mapToTitles(final TitlesDto titlesDto) {
        return new Titles(
                titlesDto.getId(),
                titlesDto.getTitle(),
                titlesDto.getAuthor(),
                titlesDto.getPublicationDate(),
                titlesDto.getBooksCopies());
    }

    public TitlesDto mapToTitlesDto(final Titles titles) {
        return new TitlesDto(
                titles.getId(),
                titles.getTitle(),
                titles.getAuthor(),
                titles.getPublicationDate(),
                titles.getBooksCopies());
    }

    public List<TitlesDto> mapToTitlesDtoList(final List<Titles> titlesList) {
        return titlesList.stream()
                .map(m -> new TitlesDto(m.getId(), m.getTitle(), m.getAuthor(), m.getPublicationDate(), m.getBooksCopies()))
                .collect(Collectors.toList());
    }
}
