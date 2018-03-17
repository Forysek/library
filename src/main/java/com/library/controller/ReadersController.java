package com.library.controller;

import com.library.domain.dto.ReadersDto;
import com.library.mapper.ReadersMapper;
import com.library.service.ServiceReaders;
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
public class ReadersController {
    @Autowired
    private ServiceReaders service;

    @Autowired
    private ReadersMapper readersMapper;

    @PostMapping(value = "newUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody ReadersDto readersDto) {
        service.saveReader(readersMapper.mapToReaders(readersDto));
    }

    @GetMapping(value = "getUsers")
    public List<ReadersDto> getAllUsers(){
        return readersMapper.mapToReadersDtoList(service.getAllReaders());
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long id){
        service.deleteReaderById(id);
    }

}
