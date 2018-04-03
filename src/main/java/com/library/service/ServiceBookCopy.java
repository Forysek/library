package com.library.service;

import com.library.domain.BookCopy;
import com.library.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBookCopy {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        bookCopy.setStatus("Available");
        return bookCopyRepository.save(bookCopy);
    }

    public Optional<BookCopy> getBookCopyById(final Long id){
        return bookCopyRepository.findById(id);
    }

    public List<BookCopy> getBooksCopiesList() {
        return bookCopyRepository.findAll();
    }

    public BookCopy rentBookCopy(final BookCopy bookCopy) {
        bookCopy.setStatus("Not Available");
        return bookCopyRepository.save(bookCopy);
    }
}
