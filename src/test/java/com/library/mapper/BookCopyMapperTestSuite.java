package com.library.mapper;

import com.library.domain.BookCopy;
import com.library.domain.dto.BookCopyDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BookCopyMapperTestSuite {

    @Test
    public void mapToBookCopyTest() {
        //Given
        BookCopyMapper mapper = new BookCopyMapper();
        BookCopyDto bookCopyDto = new BookCopyDto(
                1L,
                "Test Status",
                null,
                null
        );

        //When
        BookCopy bookCopy = mapper.mapToBookCopy(bookCopyDto);

        //Then
        assertTrue(bookCopy.getId().equals(1L));
        assertEquals("Test Status", bookCopy.getStatus());
        assertNull(bookCopy.getTitle());
        assertNull(bookCopy.getReader());
    }

    @Test
    public void mapToBookCopyDtoTest() {
        //Given
        BookCopyMapper mapper = new BookCopyMapper();
        BookCopy bookCopy = new BookCopy(
                1L,
                "Test Status",
                null,
                null
        );

        //When
        BookCopyDto bookCopyDto = mapper.mapToDto(bookCopy);

        //Then
        assertTrue(bookCopyDto.getId().equals(1L));
        assertEquals("Test Status", bookCopyDto.getStatus());
        assertNull(bookCopyDto.getTitle());
        assertNull(bookCopyDto.getReader());
    }

    @Test
    public void mapToBooksCopiesDtoListTest() {
        //Given
        BookCopyMapper mapper = new BookCopyMapper();
        List<BookCopy> bookCopies = new ArrayList<>();
        bookCopies.add(new BookCopy(
                1L,
                "Test Status",
                null,
                null
        ));

        //When
        List<BookCopyDto> mappedList = mapper.mapToBooksCopiesDtoList(bookCopies);

        //Then
        assertTrue(mappedList.get(0).getId().equals(1L));
        assertEquals("Test Status", mappedList.get(0).getStatus());
        assertNull(mappedList.get(0).getTitle());
        assertNull(mappedList.get(0).getReader());
    }
}
