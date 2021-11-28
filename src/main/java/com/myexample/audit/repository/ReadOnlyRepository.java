package com.myexample.audit.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface ReadOnlyRepository<T, I> extends Repository<T, I> {
    T findById(I id);

    boolean existsById(I id);

    List<T> findAll();

    List<T> findAllById(Iterable<I> iterable);
}
