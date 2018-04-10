package com.library.service;

import com.library.domain.BookCopy;
import com.library.domain.Title;
import com.library.repository.TitleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTitleTest {
    @InjectMocks
    private ServiceTitle service;

    @Mock
    private TitleRepository titleRepository;

    @Test
    public void getEmptyCopiesListByTitleAmount() throws Exception {
        //Given
        List<BookCopy> emptyList = new ArrayList<>();
        Title title = new Title(
                1L,
                "Title",
                "Author",
                null,
                emptyList
        );
        when(titleRepository.findByTitle(anyString())).thenReturn(ofNullable(title));

        //When
        Integer noAvailableTitles = service.getCopiesListByTitleAmount("Random String");
        System.out.println(emptyList.size());

        //Then
        assertTrue(noAvailableTitles == 0);
    }

    @Test
    public void getCopiesListByTitleAmount() throws Exception {
        //Given
        List<BookCopy> bookCopies = new ArrayList<>();
        bookCopies.add(new BookCopy(
                1L,
                "Available",
                null,
                null
        ));
        Title title = new Title(
                1L,
                "Title",
                "Author",
                null,
                bookCopies
        );
        when(titleRepository.findByTitle(anyString())).thenReturn(ofNullable(title));

        //When
        Integer availableTitles = service.getCopiesListByTitleAmount("Title");

        //Then
        assertTrue(availableTitles == 1);
    }



}