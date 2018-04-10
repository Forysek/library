package com.library.domain.dto;

import com.library.domain.BookCopy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
