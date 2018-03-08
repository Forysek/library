package com.library.mapper;

import com.library.domain.BooksCopies;
import com.library.domain.dto.BooksCopiesDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BooksCopiesMapper {
    public BooksCopies mapToBooksCopies(final BooksCopiesDto booksCopiesDto) {
        return new BooksCopies(
                booksCopiesDto.getId(),
                booksCopiesDto.getStatus(),
                booksCopiesDto.getTitlesId(),
                booksCopiesDto.getBooksRented()
        );
    }

    public BooksCopiesDto mapToDto(final BooksCopies booksCopies) {
        return new BooksCopiesDto(
                booksCopies.getId(),
                booksCopies.getStatus(),
                booksCopies.getTitles(),
                booksCopies.getBooksRented()
        );
    }

    public List<BooksCopiesDto> mapToBooksCopiesDtoList(final List<BooksCopies> booksCopies) {
        return booksCopies.stream()
                .map(m -> new BooksCopiesDto(m.getId(), m.getStatus(), m.getTitles(), m.getBooksRented()))
                .collect(Collectors.toList());
    }
}
