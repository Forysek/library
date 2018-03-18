package com.library.service;

import com.library.domain.BooksRented;
import com.library.repository.BooksRentedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBooksRented {
    @Autowired
    private BooksRentedRepository booksRentedRepository;

    public BooksRented saveBooksRented(final BooksRented booksRented) {
        return booksRentedRepository.save(booksRented);
    }

}
