package com.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.library.domain.BookCopy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private String publicationDate;
    private List<BookCopy> booksCopies;
}
