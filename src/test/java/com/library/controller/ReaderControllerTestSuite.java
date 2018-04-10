package com.library.controller;

import com.google.gson.Gson;
import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import com.library.service.ServiceReader;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReaderController.class)
public class ReaderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceReader service;

    @Captor
    private ArgumentCaptor<Reader> readerArgumentCaptor;

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto(
                1L,
                "Antonio",
                "Banderas",
                "01/01/2018",
                null
        );
        Gson gson = new Gson();
        String jsonContent = gson.toJson(readerDto);

        //When && Then
        mockMvc.perform(post("/v1/library/readers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(service, times(1)).saveReader(readerArgumentCaptor.capture());
        Reader capturedValue = readerArgumentCaptor.getValue();
        assertThat(capturedValue.getId(), is(1L));
        assertThat(capturedValue.getFirstName(), is("Antonio"));
        assertThat(capturedValue.getLastName(), is("Banderas"));
        assertThat(capturedValue.getCreationDate(), is("01/01/2018"));
        assertThat(capturedValue.getBooksCopies(), is(nullValue()));


    }

    @Test
    public void shouldGetAllUsersEmptyList() throws Exception{
        //Given
        List<Reader> emptyList = new ArrayList<>();
        when(service.getAllReaders()).thenReturn(emptyList);

        //When & Then
        mockMvc.perform(get("/v1/library/readers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetAllUsersList() throws Exception{
        //Given
        List<Reader> readersList = new ArrayList<>();
        readersList.add(new Reader(
                1L,
                "Antonio",
                "Banderas",
                "01/01/2018",
                null
        ));
        when(service.getAllReaders()).thenReturn(readersList);

        //When & Then
        mockMvc.perform(get("/v1/library/readers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Antonio")))
                .andExpect(jsonPath("$[0].lastName", is("Banderas")))
                .andExpect(jsonPath("$[0].creationDate", is("01/01/2018")))
                .andExpect(jsonPath("$[0].booksCopies", is(nullValue())));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/v1/library/readers")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());
    }
}
