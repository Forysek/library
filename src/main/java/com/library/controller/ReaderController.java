package com.library.controller;

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
@RequestMapping("/v1/library/readers/")
public class ReaderController {
    @Autowired
    private ServiceReader service;

    @Autowired
    private ReaderMapper readerMapper;

    @PostMapping(value = "newUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody ReaderDto readerDto) {
        service.saveReader(readerMapper.mapToReader(readerDto));
    }

    @GetMapping(value = "users")
    public List<ReaderDto> getAllUsers(){
        return readerMapper.mapToReadersDtoList(service.getAllReaders());
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long id){
        service.deleteReaderById(id);
    }
}
