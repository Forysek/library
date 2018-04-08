package com.library.controller;

import com.google.gson.Gson;
import com.library.domain.dto.ReaderDto;
import com.library.mapper.ReaderMapper;
import com.library.service.ServiceReader;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
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

    @MockBean
    private ReaderMapper mapper;


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
        mockMvc.perform(post("/v1/library/readers/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllUsersEmptyList() throws Exception{
        //Given
        List<ReaderDto> emptyList = new ArrayList<>();
        when(mapper.mapToReadersDtoList(service.getAllReaders())).thenReturn(emptyList);

        //When & Then
        mockMvc.perform(get("/v1/library/readers/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetAllUsersList() throws Exception{
        //Given
        List<ReaderDto> readersDtoList = new ArrayList<>();
        readersDtoList.add(new ReaderDto(
                1L,
                "Antonio",
                "Banderas",
                "01/01/2018",
                null
        ));
        when(mapper.mapToReadersDtoList(service.getAllReaders())).thenReturn(readersDtoList);

        //When & Then
        mockMvc.perform(get("/v1/library/readers/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Antonio")))
                .andExpect(jsonPath("$[0].lastName", is("Banderas")))
                .andExpect(jsonPath("$[0].creationDate", is("01/01/2018")))
                .andExpect(jsonPath("$[0].booksCopies", isEmptyOrNullString()));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/v1/library/readers/deleteUser")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());
    }
}
