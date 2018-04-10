package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.dto.BookCopyDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookCopyMapper {
    public static BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getStatus(),
                bookCopyDto.getTitle(),
                bookCopyDto.getReader());
    }

    public static BookCopyDto mapToDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getStatus(),
                bookCopy.getTitle(),
                bookCopy.getReader());
    }

    public static List<BookCopyDto> mapToBooksCopiesDtoList(final List<BookCopy> booksCopiesList) {
        return booksCopiesList.stream()
                .map(m -> new BookCopyDto(m.getId(), m.getStatus(), m.getTitle(), m.getReader()))
                .collect(Collectors.toList());
    }
}
