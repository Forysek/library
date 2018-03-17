package com.library.service;

import com.library.repository.BooksCopiesRepository;
import com.library.domain.BooksCopies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBooksCopies {
    @Autowired
    private BooksCopiesRepository booksCopiesRepository;

    public BooksCopies saveBooksCopies(final BooksCopies booksCopies) {
        return booksCopiesRepository.save(booksCopies);
    }

    public Optional<BooksCopies> getBookCopyById(final Long id){
        return booksCopiesRepository.findById(id);
    }

    public List<BooksCopies> getBooksCopiesList() {
        return booksCopiesRepository.findAll();
    }
}
