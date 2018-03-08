package com.library.domain.dto;

import com.library.domain.BooksRented;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ReadersDto {
    private long id;
    private String firstName;
    private String lastName;
    private Date creationDate;
    private List<BooksRented> booksRented;
}
