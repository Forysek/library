package com.library.controller;

import com.google.gson.Gson;
import com.library.domain.BookCopy;
import com.library.domain.Reader;
import com.library.domain.dto.BookCopyDto;
import com.library.mapper.BookCopyMapper;
import com.library.service.ServiceBookCopy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookCopyController.class)
public class BookCopyControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceBookCopy service;

    @Captor
    private ArgumentCaptor<BookCopy> bookCopyArgumentCaptor;

    @Test
    public void shouldCreateBookCopy() throws Exception {
        //Given
        BookCopyDto bookCopyDto = new BookCopyDto(
                1L,
                "Available",
                null,
                null
        );
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopyDto);

        //When & Then
        mockMvc.perform(post("/v1/library/books")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(service, times(1)).saveBookCopy(bookCopyArgumentCaptor.capture());
        BookCopy capturedValue = bookCopyArgumentCaptor.getValue();
        assertThat(capturedValue.getId(), is(1L));
        assertThat(capturedValue.getStatus(), is("Available"));
    }

    @Test
    public void shouldFetchEmptyBookCopiesList() throws Exception {
        //Given
        List<BookCopy> emptyList = new ArrayList<>();
        when(service.getBooksCopiesList()).thenReturn(emptyList);

        //When & Then
        mockMvc.perform(get("/v1/library/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchBookCopiesList() throws Exception {
        //Given
        List<BookCopy> bookCopyList = new ArrayList<>();
        bookCopyList.add(new BookCopy(
                1L,
                "Available",
                null,
                null
        ));
        when(service.getBooksCopiesList()).thenReturn(bookCopyList);

        //When & Then
        mockMvc.perform(get("/v1/library/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].status", is("Available")))
                .andExpect(jsonPath("$[0].title", is(nullValue())))
                .andExpect(jsonPath("$[0].reader", is(nullValue())));
    }

    @Test
    public void shouldRentBook() throws Exception {
        //Given
        List<BookCopy> bookCopies = new ArrayList<>();
        Reader reader = new Reader(
                2L,
                "Antonio",
                "Banderas",
                "01/01/2018",
                bookCopies
        );
        BookCopy bookCopy = new BookCopy(
                1L,
                "Not Available",
                null,
                reader
        );

        Gson gson = new Gson();
        BookCopyDto bookCopyDto =  BookCopyMapper.mapToDto(bookCopy);
        String jsonContent = gson.toJson(bookCopyDto);

        mockMvc.perform(put("/v1/library/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());

        verify(service, times(1)).moveBookCopy(bookCopyArgumentCaptor.capture());
        BookCopy capturedValue = bookCopyArgumentCaptor.getValue();
        assertThat(capturedValue.getId(), is(1L));
        assertThat(capturedValue.getStatus(), is("Not Available"));
        assertThat(capturedValue.getTitle(), is(nullValue()));
        assertThat(capturedValue.getReader().getId(), is(2L));
        assertThat(capturedValue.getReader().getFirstName(), is("Antonio"));
        assertThat(capturedValue.getReader().getLastName(), is("Banderas"));
        assertThat(capturedValue.getReader().getCreationDate(), is("01/01/2018"));
        assertThat(capturedValue.getReader().getBooksCopies(), hasSize(0));
    }
}
