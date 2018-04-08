package com.library.mapper;

import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TitleMapperTestSuite {

    @Test
    public void mapToTitleTest() {
        //Given
        TitleMapper mapper = new TitleMapper();
        TitleDto titleDto = new TitleDto(
                1L,
                "Test Title",
                "Test Author",
                "01/01/2018",
                null
        );

        //When
        Title title = mapper.mapToTitle(titleDto);

        //Then
        assertTrue(title.getId().equals(1L));
        assertEquals("Test Title", title.getTitle());
        assertEquals("Test Author", title.getAuthor());
        assertEquals("01/01/2018", title.getPublicationDate());
        assertNull(title.getBooksCopies());
    }

    @Test
    public void mapToTitleDtoTest() {
        //Given
        TitleMapper mapper = new TitleMapper();
        Title title = new Title(
                1L,
                "Test Title",
                "Test Author",
                "01/01/2018",
                null
        );

        //When
        TitleDto titleDto = mapper.mapToTitleDto(title);

        //Then
        assertTrue(titleDto.getId().equals(1L));
        assertEquals("Test Title", titleDto.getTitle());
        assertEquals("Test Author", titleDto.getAuthor());
        assertEquals("01/01/2018", titleDto.getPublicationDate());
        assertNull(titleDto.getBooksCopies());
    }

    @Test
    public void mapToTitleDtoListTest() {
        //Given
        TitleMapper mapper = new TitleMapper();
        List<Title> titles = new ArrayList<>();
        titles.add(new Title(
                1L,
                "Test Title",
                "Test Author",
                "01/01/2018",
                null
        ));

        //When
        List<TitleDto> mappedList = mapper.mapToTitlesDtoList(titles);

        //Then
        assertTrue(mappedList.get(0).getId().equals(1L));
        assertEquals("Test Title", mappedList.get(0).getTitle());
        assertEquals("Test Author", mappedList.get(0).getAuthor());
        assertEquals("01/01/2018", mappedList.get(0).getPublicationDate());
        assertNull(mappedList.get(0).getBooksCopies());
    }
}
