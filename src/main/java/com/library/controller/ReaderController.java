package com.library.controller;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import com.library.mapper.ReaderMapper;
import com.library.service.ServiceReader;
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
@RequestMapping("/v1/library/readers")
public class ReaderController {
    @Autowired
    private ServiceReader service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ReaderDto createUser(@RequestBody ReaderDto readerDto) {
        Reader reader = ReaderMapper.mapToReader(readerDto);
        service.saveReader(reader);
        ReaderDto tempReaderDto = ReaderMapper.mapToReaderDto(reader);
        return tempReaderDto;
    }

    @GetMapping
    public List<ReaderDto> getAllUsers(){
        List<Reader> readerList = service.getAllReaders();
        List<ReaderDto> readerDtoList = ReaderMapper.mapToReadersDtoList(readerList);
        return readerDtoList;
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id){
        service.deleteReaderById(id);
    }
}
