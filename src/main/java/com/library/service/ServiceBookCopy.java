package com.library.service;

import com.library.domain.BookCopy;
import com.library.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBookCopy {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> getBooksCopiesList() {
        return bookCopyRepository.findAll();
    }

    public BookCopy moveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }
}
