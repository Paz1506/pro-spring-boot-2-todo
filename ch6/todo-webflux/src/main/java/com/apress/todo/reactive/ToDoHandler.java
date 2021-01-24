package com.apress.todo.reactive;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author Pavel Zaytsev
 */
@Component
public class ToDoHandler {

    private final ToDoRepository repository;

    @Autowired
    public ToDoHandler(ToDoRepository repository) {this.repository = repository;}

    public Mono<ServerResponse> getToDo(ServerRequest request) {
        String toDoId = request.pathVariable("id");

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        Mono<ToDo> toDo = this.repository.findById(toDoId);

        return toDo.flatMap(t -> ServerResponse.ok()
                                               .contentType(MediaType.APPLICATION_JSON)
                                               // TODO: Will see, how to invoke now
                                               .body(fromObject(t)))
                   .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getToDos(ServerRequest request) {
        Flux<ToDo> toDos = this.repository.findAll();

        return ServerResponse.ok()
//                             .headers() // можно также доббавлять заголовки
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(toDos, ToDo.class);
    }
}
