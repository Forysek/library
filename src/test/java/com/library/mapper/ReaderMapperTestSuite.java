package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ReaderMapperTestSuite {

    @Test
    public void mapToReaderTest() {
        //Given
        ReaderMapper mapper = new ReaderMapper();
        ReaderDto readerDto = new ReaderDto(
                1L,
                "Antonio",
                "Banderas",
                "01/01/2018",
                null
        );

        //When
        Reader reader = mapper.mapToReader(readerDto);

        //Then
        assertTrue(reader.getId().equals(1L));
        assertEquals("Antonio", reader.getFirstName());
        assertEquals("Banderas", reader.getLastName());
        assertEquals("01/01/2018", reader.getCreationDate());
        assertNull(reader.getBooksCopies());
    }

    @Test
    public void mapToReaderDtoTest() {
        //Given
        ReaderMapper mapper = new ReaderMapper();
        Reader reader = new Reader(
                1L,
                "Antonio",
                "Banderas",
                "01/01/2018",
                null
        );

        //When
        ReaderDto readerDto = mapper.mapToReaderDto(reader);

        //Then
        assertTrue(readerDto.getId().equals(1L));
        assertEquals("Antonio", readerDto.getFirstName());
        assertEquals("Banderas", readerDto.getLastName());
        assertEquals("01/01/2018", readerDto.getCreationDate());
        assertNull(readerDto.getBooksCopies());
    }

    @Test
    public void mapToReadersDtoListTest() {
        //Given
        ReaderMapper mapper = new ReaderMapper();
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader(
                1L,
                "Antonio",
                "Banderas",
                "01/01/2018",
                null
        ));

        //When
        List<ReaderDto> mappedList = mapper.mapToReadersDtoList(readers);

        //Then
        assertTrue(mappedList.get(0).getId().equals(1L));
        assertEquals("Antonio", mappedList.get(0).getFirstName());
        assertEquals("Banderas", mappedList.get(0).getLastName());
        assertEquals("01/01/2018", mappedList.get(0).getCreationDate());
        assertNull(mappedList.get(0).getBooksCopies());
    }
}
