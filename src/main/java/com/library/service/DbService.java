package com.library.service;

import com.library.dao.BooksCopiesDao;
import com.library.dao.BooksRentedDao;
import com.library.dao.ReadersDao;
import com.library.dao.TitlesDao;
import com.library.domain.BooksCopies;
import com.library.domain.Readers;
import com.library.domain.Titles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DbService {
    @Autowired
    private BooksCopiesDao booksCopiesDao;

    @Autowired
    private BooksRentedDao booksRentedDao;

    @Autowired
    private ReadersDao readersDao;

    @Autowired
    private TitlesDao titlesDao;


    public Readers saveReader(final Readers readers) {
        return readersDao.save(readers);
    }

    public Titles saveTitle(final Titles titles) {
        return titlesDao.save(titles);
    }

    public BooksCopies saveBooksCopies(final BooksCopies booksCopies) {
        return booksCopiesDao.save(booksCopies);
    }


}
