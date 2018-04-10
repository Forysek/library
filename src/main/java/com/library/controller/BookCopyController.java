package com.library.controller;

import com.library.domain.BookCopy;
import com.library.domain.dto.BookCopyDto;
import com.library.mapper.BookCopyMapper;
import com.library.service.ServiceBookCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/library/books")
public class BookCopyController {
    @Autowired
    private ServiceBookCopy service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public BookCopyDto createBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = BookCopyMapper.mapToBookCopy(bookCopyDto);
        service.saveBookCopy(bookCopy);
        BookCopyDto tempBookCopyDto = BookCopyMapper.mapToDto(bookCopy);
        return tempBookCopyDto;
    }

    @GetMapping
    public List<BookCopyDto> getAllBooksCopies() {
        List<BookCopy> booksCopyList = service.getBooksCopiesList();
        List<BookCopyDto> bookCopyDtoList = BookCopyMapper.mapToBooksCopiesDtoList(booksCopyList);
        return bookCopyDtoList;
    }

    @PutMapping
    public void moveBook(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = BookCopyMapper.mapToBookCopy(bookCopyDto);
        service.moveBookCopy(bookCopy);
    }
}
