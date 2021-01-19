package com.apress.todo.client;

import com.apress.todo.client.ToDoRestClient;
import com.apress.todo.client.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoClientApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(TodoClientApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE); // не поднимаем веб-контекст у себя, т.к. нам нужен только клиент
        springApplication.run(args);
    }

    private Logger log = LoggerFactory.getLogger(TodoClientApplication.class);

    @Bean
    public CommandLineRunner process(ToDoRestClient client) {
        return args -> {

            log.info("## > findAll:");
            Iterable<ToDo> toDos = client.findAll();
            assert toDos != null;
            toDos.forEach(toDo -> log.info(toDo.toString()));

            log.info("## > upsert:");
            ToDo newToDo = client.upsert(new ToDo("Drink plenty of Water daily!"));
            assert newToDo != null;
            log.info(newToDo.toString());

            log.info("## > findById:");
            ToDo toDo = client.findById(newToDo.getId());
            assert toDos != null;
            log.info(toDo.toString());

            log.info("## > setCompleted:");
            ToDo completed = client.setCompleted(newToDo.getId());
            assert completed.isCompleted();
            log.info(completed.toString());

            log.info("## > delete:");
            client.delete(newToDo.getId());
            assert client.findById(newToDo.getId()) == null;
        };
    }

}
