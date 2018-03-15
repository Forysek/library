package com.library.controller;

import com.library.domain.dto.TitlesDto;
import com.library.mapper.TitlesMapper;
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
public class TitlesController {
    @Autowired
    private DbService service;

    @Autowired
    private TitlesMapper titlesMapper;

    @PostMapping(value = "book", consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitlesDto titlesDto) {
        service.saveTitle(titlesMapper.mapToTitles(titlesDto));
    }

}
