package com.apress.reactor.example;

import com.apress.reactor.example.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @author Pavel Zaytsev
 */
@Configuration
public class FluxExample {

    static final Logger LOG = LoggerFactory.getLogger(FluxExample.class);

    @Bean
    public CommandLineRunner runFluxExample() {
        return args -> {
            EmitterProcessor<ToDo> stream = EmitterProcessor.create();

            Mono<List<ToDo>> promise = stream.filter(ToDo::isCompleted)
                                             .doOnNext(s -> LOG.info("FLUX >> ToDo: {}", s.getDescription()))
                                             .collectList()
                                             .subscribeOn(Schedulers.single());

            stream.onNext(new ToDo("Read a book", true));
            stream.onNext(new ToDo("This is not completed", false));
            stream.onNext(new ToDo("This is completed not set"));
            stream.onNext(new ToDo("Be happy", true));
            stream.onNext(new ToDo("Go to the car wash", true));

            stream.onComplete(); // Завершим контейнер

            promise.block(); // ждем неограниченное время
        };
    }
}
