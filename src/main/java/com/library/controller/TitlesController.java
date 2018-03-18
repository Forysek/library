package com.library.controller;

import com.library.controller.exceptions.CopyNotFoundException;
import com.library.domain.dto.TitlesDto;
import com.library.mapper.TitlesMapper;
import com.library.service.ServiceTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/library/titles/")
public class TitlesController {
    @Autowired
    private ServiceTitles service;

    @Autowired
    private TitlesMapper titlesMapper;

    @PostMapping(value = "newTitle", consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitlesDto titlesDto) {
        service.saveTitle(titlesMapper.mapToTitles(titlesDto));
    }

    @GetMapping(value = "getTitles")
    public List<TitlesDto> getAllTitles() {
        return titlesMapper.mapToTitlesDtoList(service.getAllTitles());
    }

    @GetMapping(value = "getSingleTitle")
    public TitlesDto getTitle(@RequestParam Long id) throws CopyNotFoundException{
        return titlesMapper.mapToTitlesDto(service.getTitleById(id).orElseThrow(CopyNotFoundException::new));
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(@RequestParam Long titleId){
        service.deleteTitle(titleId);
    }

}
