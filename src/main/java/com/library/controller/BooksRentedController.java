package com.library.controller;

import com.library.domain.dto.BooksRentedDto;
import com.library.mapper.BooksRentedMapper;
import com.library.service.ServiceBooksRented;
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
@RequestMapping("/v1/library/rental/")
public class BooksRentedController {
    @Autowired
    private ServiceBooksRented service;

    @Autowired
    private BooksRentedMapper booksRentedMapper;

    @PutMapping(value = "newRental", consumes = APPLICATION_JSON_VALUE)
    public void createRental(@RequestBody BooksRentedDto booksRentedDto) {

        service.saveBooksRented(booksRentedMapper.mapToBooksRented(booksRentedDto));
    }
}
