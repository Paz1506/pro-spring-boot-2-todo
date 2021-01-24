package com.apress.todo.controller;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Привычные нам анноотации, но вызовы являются асинхронными неблокирующими
 * В качестве альтернативы, можем использовать функции маршрутизации, см. ToDoRouter
 *
 * @author Pavel Zaytsev
 */
//@RestController
public class ToDoControllerAsPlainOldRestController {

    private final ToDoRepository repository;

    @Autowired
    public ToDoControllerAsPlainOldRestController(ToDoRepository repository) {this.repository = repository;}

    @GetMapping("/todo/{id}")
    public Mono<ToDo> getToDo(@PathVariable String id) {
        return this.repository.findById(id);
    }

    @GetMapping("/todo")
    public Flux<ToDo> getToDos() {
        return this.repository.findAll();
    }

    @GetMapping("/todo/create")
    public void createToDo(@RequestParam("description") String description) {
        this.repository.create(description);
    }
}
