package com.library.controller;

import com.library.controller.exceptions.TitleIdNotFoundException;
import com.library.controller.exceptions.TitleNameNotFoundException;
import com.library.domain.dto.TitleDto;
import com.library.mapper.TitleMapper;
import com.library.service.ServiceTitle;
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
public class TitleController {
    @Autowired
    private ServiceTitle service;

    @Autowired
    private TitleMapper titleMapper;

    @PostMapping(value = "newTitle", consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitleDto titleDto) {
        service.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @GetMapping(value = "titles")
    public List<TitleDto> getAllTitles() {
        return titleMapper.mapToTitlesDtoList(service.getAllTitles());
    }

    @GetMapping(value = "singleTitle")
    public TitleDto getTitle(@RequestParam Long id) throws TitleIdNotFoundException {
        return titleMapper.mapToTitleDto(service.getTitleById(id).orElseThrow(TitleIdNotFoundException::new));
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(@RequestParam Long titleId){
        service.deleteTitle(titleId);
    }

    @GetMapping(value = "availableByTitle")
    public Integer getAvailableByTitle(@RequestParam String title) throws TitleNameNotFoundException {
        return service.getCopiesListByTitle(title);
    }

}
