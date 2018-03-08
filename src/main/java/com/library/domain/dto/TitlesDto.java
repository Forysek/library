package com.library.domain.dto;

import com.library.domain.BooksCopies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TitlesDto {
    private long id;
    private String title;
    private String author;
    private String publicationDate;
    private List<BooksCopies> booksCopies;
}
