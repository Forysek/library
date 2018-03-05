package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class BooksRentedDto {
    private Date rentDate;
    private Date returnDate;
}
