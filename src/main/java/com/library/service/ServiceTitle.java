package com.library.service;

import com.library.controller.exceptions.TitleNameNotFoundException;
import com.library.domain.Title;
import com.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTitle {
    @Autowired
    private TitleRepository titleRepository;

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public void deleteTitle(final Long id){
        titleRepository.deleteById(id);
    }

    public Optional<Title> getTitleById(Long id){
        return titleRepository.findById(id);
    }

    public Integer getCopiesListByTitle(String title) throws TitleNameNotFoundException {
        return (titleRepository.findByTitle(title).orElseThrow(TitleNameNotFoundException::new)).getBooksCopies().size();
    }

}
