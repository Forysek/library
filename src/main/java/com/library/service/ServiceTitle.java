package com.library.service;

import com.library.controller.exceptions.TitleNameNotFoundException;
import com.library.domain.BookCopy;
import com.library.domain.Title;
import com.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Integer getCopiesListByTitleAmount(String title) throws TitleNameNotFoundException {
        List<BookCopy> availableBookCopies = titleRepository.findByTitle(title)
                .orElseThrow(TitleNameNotFoundException::new)
                .getBooksCopies();
        if (availableBookCopies.size() == 0) {
            return 0;
        } else {
            Integer availableBooks = availableBookCopies.stream()
                    .filter(bookCopy -> bookCopy.getStatus().equals("Available"))
                    .collect(Collectors.toList()).size();
            return availableBooks;
        }
    }
}
