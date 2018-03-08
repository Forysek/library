package com.library.domain.dto;

import com.library.domain.BooksCopies;
import com.library.domain.Readers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BooksRentedDto {
    private long id;
    private Date rentDate;
    private Date returnDate;
    private Readers readers;
    private BooksCopies booksCopies;
}
