package com.library.dao;

import com.library.domain.Readers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ReadersDao extends CrudRepository<Readers, Long> {

    @Override
    List<Readers> findAll();

    @Override
    Optional<Readers> findById(Long id);

    @Override
    Readers save(Readers readers);

    @Override
    void deleteById(Long id);
}
