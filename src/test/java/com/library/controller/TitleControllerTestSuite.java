package com.library.controller;

import com.google.gson.Gson;
import com.library.domain.Title;
import com.library.domain.dto.TitleDto;
import com.library.service.ServiceTitle;
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
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TitleController.class)
public class TitleControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceTitle service;

    @Captor
    private ArgumentCaptor<Title> titleArgumentCaptor;

    @Test
    public void shouldCreateNewTitle() throws Exception {
        //Given
        TitleDto titleDto = new TitleDto(
                1L,
                "Test Title",
                "Test Author",
                "01/01/2018",
                null
        );
        Gson gson = new Gson();
        String jsonContent = gson.toJson(titleDto);

        //When & Then
        mockMvc.perform(post("/v1/library/titles")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(service, times(1)).saveTitle(titleArgumentCaptor.capture());
        Title capturedValue = titleArgumentCaptor.getValue();
        assertThat(capturedValue.getTitle(), is("Test Title"));
        assertThat(capturedValue.getAuthor(), is("Test Author"));
        assertThat(capturedValue.getPublicationDate(), is("01/01/2018"));
        assertThat(capturedValue.getBooksCopies(), is(nullValue()));
    }

    @Test
    public void shouldGetAllTitlesEmptyList() throws Exception {
        //Given
        List<Title> emptyList = new ArrayList<>();
        when(service.getAllTitles()).thenReturn(emptyList);

        //When & Then
        mockMvc.perform(get("/v1/library/titles/allTitles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetAllTitlesList() throws Exception{
        //Given
        List<Title> titleList = new ArrayList<>();
        titleList.add(new Title(
                1L,
                "Test Title",
                "Test Author",
                "01/01/2018",
                null
        ));
        when(service.getAllTitles()).thenReturn(titleList);

        //When & Then
        mockMvc.perform(get("/v1/library/titles/allTitles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test Title")))
                .andExpect(jsonPath("$[0].author", is("Test Author")))
                .andExpect(jsonPath("$[0].publicationDate", is("01/01/2018")))
                .andExpect(jsonPath("$[0].booksCopies", is(nullValue())));
    }

    @Test
    public void shouldGetSingleTitle() throws Exception {
        //Given
        Long id = 1L;
        Title title = new Title(
                1L,
                "Test Title",
                "Test Author",
                "01/01/2018",
                null
        );
        when(service.getTitleById(id)).thenReturn(Optional.of(title));

        //When & Then
        mockMvc.perform(get("/v1/library/titles/singleTitle?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.author", is("Test Author")))
                .andExpect(jsonPath("$.publicationDate", is("01/01/2018")))
                .andExpect(jsonPath("$.booksCopies", is(nullValue())));
    }

    @Test
    public void shouldDeleteTitle() throws Exception {
        mockMvc.perform(delete("/v1/library/titles/deleteTitle?titleId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("titleId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAvailableByTitleAmount() throws Exception {
        //When & Then
        mockMvc.perform(get("/v1/library/titles/availableByTitle")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "Test"))
                .andExpect(jsonPath("$", isA(Integer.class)));
    }
}
