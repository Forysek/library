package com.library.mapper;

import com.library.domain.BooksRented;
import com.library.domain.dto.BooksRentedDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BooksRentedMapper {
    public BooksRented mapToBooksRented(final BooksRentedDto booksRentedDto) {
        return new BooksRented(
                booksRentedDto.getId(),
                booksRentedDto.getReaders(),
                booksRentedDto.getBooksCopies(),
                booksRentedDto.getRentDate(),
                booksRentedDto.getReturnDate());
    }

    public BooksRentedDto mapToBooksRentedDto(final BooksRented booksRented) {
        return new BooksRentedDto(
                booksRented.getId(),
                booksRented.getReaders(),
                booksRented.getBooksCopies(),
                booksRented.getRentDate(),
                booksRented.getReturnDate());
    }

    public List<BooksRentedDto> mapToBooksRentedDtoList(final List<BooksRented> booksRentedList) {
        return booksRentedList.stream()
                .map(m -> new BooksRentedDto(m.getId(), m.getReaders(), m.getBooksCopies(), m.getRentDate(), m.getReturnDate()))
                .collect(Collectors.toList());
    }
}
