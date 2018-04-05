package com.library.controller;

import com.library.controller.exceptions.CopyIdNotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/library/books/")
public class BookCopyController {
    @Autowired
    private ServiceBookCopy service;

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @PostMapping(value = "bookCopy", consumes = APPLICATION_JSON_VALUE)
    public void createBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        service.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @PutMapping(value = "statusChange")
    public void updateStatus(@RequestParam Long id, @RequestParam String status) throws CopyIdNotFoundException {
        BookCopyDto bookCopyDto = bookCopyMapper.mapToDto(service.getBookCopyById(id).orElseThrow(CopyIdNotFoundException::new));
        bookCopyDto.setStatus(status);
        service.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @GetMapping(value = "getBooksCopies")
    public List<BookCopyDto> getAllBooksCopies() {
        return bookCopyMapper.mapToBooksCopiesDtoList(service.getBooksCopiesList());
    }

    @PutMapping(value = "rentBook")
    public void rentBook(@RequestBody BookCopyDto bookCopyDto) {
        service.rentBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestBody BookCopyDto bookCopyDto) {
        service.returnBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }
}
