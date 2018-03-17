package com.library.controller;

import com.library.controller.exceptions.CopyNotFoundException;
import com.library.domain.dto.BooksCopiesDto;
import com.library.mapper.BooksCopiesMapper;
import com.library.service.ServiceBooksCopies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/library/books/")
public class BooksCopiesController {
    @Autowired
    private ServiceBooksCopies service;

    @Autowired
    private BooksCopiesMapper booksCopiesMapper;

    @PostMapping(value = "bookCopy", consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody BooksCopiesDto booksCopiesDto) {
        service.saveBooksCopies(booksCopiesMapper.mapToBooksCopies(booksCopiesDto));
    }

    @PutMapping(value = "statusChange", consumes = APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestParam Long id, @RequestParam String newStatus) throws CopyNotFoundException {
        BooksCopiesDto booksCopiesDto = booksCopiesMapper.mapToDto(service.getBookCopyById(id).orElseThrow(CopyNotFoundException::new));
        booksCopiesDto.setStatus(newStatus);
        service.saveBooksCopies(booksCopiesMapper.mapToBooksCopies(booksCopiesDto));
    }

    @GetMapping(value = "getBooksCopies")
    public List<BooksCopiesDto> getAllBooksCopies() {
        return booksCopiesMapper.mapToBooksCopiesDtoList(service.getBooksCopiesList());
    }

    @GetMapping(value = "availableCount")
    public int availableBooksCount(
}
