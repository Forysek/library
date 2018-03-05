package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TitlesDto {
    private long id;
    private String title;
    private String author;
    private String publicationDate;
}
