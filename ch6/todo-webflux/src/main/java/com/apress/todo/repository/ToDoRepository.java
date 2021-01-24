package com.apress.todo.repository;

import com.apress.todo.domain.ToDo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Zaytsev
 */
@Repository
public class ToDoRepository {

    private List<ToDo> toDoList;

    public ToDoRepository() {
        this.toDoList = new ArrayList<>();
        this.toDoList.add(new ToDo("Do homework"));
        this.toDoList.add(new ToDo("Workout", true));
        this.toDoList.add( new ToDo("Make dinner"));
        this.toDoList.add(new ToDo("Clean", true));
    }

    public Mono<ToDo> findById(String id) {
        return Mono.from(Flux.fromIterable(toDoList)
                             .filter(todo -> todo.getId().equals(id)));
    }

    public Flux<ToDo> findAll() {
        return Flux.fromIterable(toDoList);
    }

    public void create(String description) {
        toDoList.add(new ToDo(description));
    }

}
