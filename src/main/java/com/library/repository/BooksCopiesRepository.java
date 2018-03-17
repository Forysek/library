package com.library.repository;

import com.library.domain.BooksCopies;
import com.library.domain.Titles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface BooksCopiesRepository extends CrudRepository<BooksCopies, Long> {

    @Override
    List<BooksCopies> findAll();

    @Override
    Optional<BooksCopies> findById(Long id);

    @Override
    BooksCopies save(BooksCopies booksCopies);

    @Override
    void deleteById(Long id);

    Optional<BooksCopies> findByTitles(Titles titles);

}