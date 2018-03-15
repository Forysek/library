package com.library.controller;

import com.library.domain.dto.ReadersDto;
import com.library.mapper.ReadersMapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/library")
public class ReadersController {
    @Autowired
    private DbService service;

    @Autowired
    private ReadersMapper readersMapper;

    @PostMapping(value = "user", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody ReadersDto readersDto) {
        service.saveReader(readersMapper.mapToReaders(readersDto));
    }

}
