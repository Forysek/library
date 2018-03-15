package com.library.controller;

import com.library.domain.dto.BooksCopiesDto;
import com.library.mapper.BooksCopiesMapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/library")
public class BooksCopiesController {
    @Autowired
    private DbService service;

    @Autowired
    private BooksCopiesMapper booksCopiesMapper;

    @PostMapping(value = "copy", consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody BooksCopiesDto booksCopiesDto) {
        service.saveBooksCopies(booksCopiesMapper.mapToBooksCopies(booksCopiesDto));
    }

    @PutMapping(value = "statusChange", consumes = APPLICATION_JSON_VALUE)
    public BooksCopiesDto updateStatus(@RequestBody BooksCopiesDto booksCopiesDto) {
        return booksCopiesMapper.mapToDto(service.saveBooksCopies(booksCopiesMapper.mapToBooksCopies(booksCopiesDto)));
    }


}
