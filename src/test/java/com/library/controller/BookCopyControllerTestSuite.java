package com.library.controller;

import com.google.gson.Gson;
import com.library.domain.BookCopy;
import com.library.domain.Reader;
import com.library.domain.dto.BookCopyDto;
import com.library.mapper.BookCopyMapper;
import com.library.service.ServiceBookCopy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @MockBean
    private BookCopyMapper mapper;

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
        mockMvc.perform(post("/v1/library/books/bookCopy")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateStatus() throws Exception {
        //Given
        BookCopyDto bookCopyDto = new BookCopyDto(
                1L,
                "Available",
                null,
                null
        );
        BookCopy bookCopy = new BookCopy(
                1L,
                "Available",
                null,
                null
        );
        when(service.getBookCopyById(anyLong())).thenReturn(ofNullable(bookCopy));
        when(mapper.mapToDto(any(BookCopy.class))).thenReturn(bookCopyDto);


        //When & Then
        mockMvc.perform(put("/v1/library/books/statusChange")
                .characterEncoding("UTF-8")
                .param("id", "1")
                .param("status", "Not Available"))
                .andExpect(status().isOk());

        assertTrue(bookCopyDto.getStatus().equals("Not Available"));
    }

    @Test
    public void shouldFetchEmptyBookCopiesList() throws Exception {
        //Given
        List<BookCopyDto> emptyList = new ArrayList<>();
        when(mapper.mapToBooksCopiesDtoList(service.getBooksCopiesList())).thenReturn(emptyList);

        //When & Then
        mockMvc.perform(get("/v1/library/books/getBooksCopies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchBookCopiesList() throws Exception {
        //Given
        List<BookCopyDto> bookCopyDtoList = new ArrayList<>();
        bookCopyDtoList.add(new BookCopyDto(
                1L,
                "Available",
                null,
                null
        ));
        when(mapper.mapToBooksCopiesDtoList(service.getBooksCopiesList())).thenReturn(bookCopyDtoList);

        //When & Then
        mockMvc.perform(get("/v1/library/books/getBooksCopies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].status", is("Available")))
                .andExpect(jsonPath("$[0].title", isEmptyOrNullString()))
                .andExpect(jsonPath("$[0].reader", isEmptyOrNullString()));
    }

    @Test
    public void shouldRentBook() throws Exception {
        //Given
        List<BookCopy> bookCopies = new ArrayList<>();
        Reader reader = new Reader(
                2L,
                "Antonio",
                "Banderas",
                LocalDate.of(2018, 4, 1),
                bookCopies
        );
        BookCopy bookCopy = new BookCopy(
                1L,
                "Not Available",
                null,
                reader
        );

        when(mapper.mapToBookCopy(any(BookCopyDto.class))).thenReturn(bookCopy);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopy);

        mockMvc.perform(put("/v1/library/books/rentBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is("Not Available")))
                .andExpect(jsonPath("$.title", isEmptyOrNullString()))
                .andExpect(jsonPath("$.reader.id", is(2)))
                .andExpect(jsonPath("$.reader.firstName", is("Antonio")))
                .andExpect(jsonPath("$.reader.lastName", is("Banderas")))
                .andExpect(jsonPath("$.reader.creationDate", is("2018-04-01")))
                .andExpect(jsonPath("$.reader.booksCopies", hasSize(1)))
                .andExpect(jsonPath("$.reader.booksCopies[0].id", is(1)));
    }

    @Test
    public void shouldReturnBook() throws Exception {
        //Given
        BookCopy bookCopy = new BookCopy(
                1L,
                "Available",
                null,
                null
        );
        when(mapper.mapToBookCopy(any(BookCopyDto.class))).thenReturn(bookCopy);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopy);

        mockMvc.perform(put("/v1/library/books/returnBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is("Available")))
                .andExpect(jsonPath("$.title", isEmptyOrNullString()));
    }
}
