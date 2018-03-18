package com.library.domain.dto;

import com.library.domain.BooksRented;
import com.library.domain.Titles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BooksCopiesDto {
    private Long id;
    private String status;
    private Titles titles;
    private BooksRented booksRented;
}
