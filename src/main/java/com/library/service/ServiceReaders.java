package com.library.service;

import com.library.domain.Readers;
import com.library.repository.ReadersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceReaders {
    @Autowired
    private ReadersRepository readersRepository;

    public Readers saveReader(final Readers readers) {
        return readersRepository.save(readers);
    }

    public List<Readers> getAllReaders() {
        return readersRepository.findAll();
    }

    public void deleteReaderById(final long id){
        readersRepository.deleteById(id);
    }

}
