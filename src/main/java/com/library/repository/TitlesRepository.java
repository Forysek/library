package com.library.repository;

import com.library.domain.Titles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TitlesRepository extends CrudRepository<Titles, Long>{

    @Override
    List<Titles> findAll();

    @Override
    Optional<Titles> findById(Long id);

    @Override
    Titles save(Titles titles);

    @Override
    void deleteById(Long id);
}
