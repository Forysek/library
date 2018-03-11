package com.library.controller;

import com.library.domain.dto.BooksCopiesDto;
import com.library.domain.dto.ReadersDto;
import com.library.domain.dto.TitlesDto;
import com.library.mapper.BooksCopiesMapper;
import com.library.mapper.BooksRentedMapper;
import com.library.mapper.ReadersMapper;
import com.library.mapper.TitlesMapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/library")
public class LibraryController {
    @Autowired
    private DbService service;

    @Autowired
    private BooksCopiesMapper booksCopiesMapper;

    @Autowired
    private BooksRentedMapper booksRentedMapper;

    @Autowired
    private ReadersMapper readersMapper;

    @Autowired
    private TitlesMapper titlesMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody ReadersDto readersDto) {
        service.saveReader(readersMapper.mapToReaders(readersDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTitle", consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitlesDto titlesDto) {
        service.saveTitle(titlesMapper.mapToTitles(titlesDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTitle", consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody BooksCopiesDto booksCopiesDto) {
        service.saveBooksCopies(booksCopiesMapper.mapToBooksCopies(booksCopiesDto));
    }


}
