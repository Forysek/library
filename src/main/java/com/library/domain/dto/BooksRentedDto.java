package com.library.domain.dto;

import com.library.domain.BooksCopies;
import com.library.domain.Readers;
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
public class BooksRentedDto {
    private Long id;
    private List<Readers> readers;
    private List<BooksCopies> booksCopies;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
