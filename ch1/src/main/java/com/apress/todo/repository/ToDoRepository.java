package com.apress.todo.repository;

import com.apress.todo.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Pavel Zaytsev
 */
public interface ToDoRepository extends CrudRepository<ToDo, String> {
}
