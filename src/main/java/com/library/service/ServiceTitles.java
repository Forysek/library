package com.library.service;

import com.library.domain.Titles;
import com.library.repository.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTitles {
    @Autowired
    private TitlesRepository titlesRepository;

    public Titles saveTitle(final Titles titles) {
        return titlesRepository.save(titles);
    }

    public List<Titles> getAllTitles() {
        return titlesRepository.findAll();
    }

    public void deleteTitle(final Long id){
        titlesRepository.deleteById(id);
    }

    public Optional<Titles> getTitleById(Long id){
        return titlesRepository.findById(id);
    }

}
