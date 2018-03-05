package com.library.domain.dao;

import com.library.domain.BooksRented;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface BooksRentedDao extends CrudRepository<BooksRented, Long>{

    @Override
    List<BooksRented> findAll();

    @Override
    Optional<BooksRented> findById(Long id);

    @Override
    BooksRented save(BooksRented booksRented);

    @Override
    void deleteById(Long id);
}
