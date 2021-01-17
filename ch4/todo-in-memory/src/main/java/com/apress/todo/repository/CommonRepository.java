package com.apress.todo.repository;

import java.util.Collection;

/**
 * @author Pavel Zaytsev
 */
public interface CommonRepository<T> {

    T save(T domain);

    Iterable<T> save(Collection<T> domains);

    void delete(T domain);

    T findById(String id);

    Iterable<T> findAll();
}
