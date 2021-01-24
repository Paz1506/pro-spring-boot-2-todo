package com.apress.todo.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Второй способ объявления маршрутизации запросов, наряду с  ToDoControllerAsPlainOldRestController,
 * использующий функциональный подход к объявлению функций роутинга
 * <p>
 * // TODO: для использования этого, закомментируйте @RestController в ToDoControllerAsPlainOldRestController
 *
 * @author Pavel Zaytsev
 */
@Configuration
public class ToDoRouter {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(ToDoHandler toDoHandler) {
        return route(GET("/todo/{id}").and(accept(MediaType.APPLICATION_JSON)), toDoHandler::getToDo)
                .andRoute(GET("/todo").and(accept(MediaType.APPLICATION_JSON)), toDoHandler::getToDos);
    }
}
