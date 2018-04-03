package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.dto.BookCopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {
    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getStatus(),
                bookCopyDto.getTitle(),
                bookCopyDto.getReader());
    }

    public BookCopyDto mapToDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getStatus(),
                bookCopy.getTitle(),
                bookCopy.getReader());
    }

    public List<BookCopyDto> mapToBooksCopiesDtoList(final List<BookCopy> booksCopiesList) {
        return booksCopiesList.stream()
                .map(m -> new BookCopyDto(m.getId(), m.getStatus(), m.getTitle(), m.getReader()))
                .collect(Collectors.toList());
    }
}
