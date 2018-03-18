package com.library.domain.dto;

import com.library.domain.BooksRented;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReadersDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate creationDate;
    private BooksRented booksRented;
}
