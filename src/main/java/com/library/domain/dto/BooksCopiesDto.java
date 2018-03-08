package com.library.domain.dto;

import com.library.domain.BooksRented;
import com.library.domain.Titles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BooksCopiesDto {
    private long id;
    private String status;
    private Titles titlesId;
    private List<BooksRented> booksRented;
}
