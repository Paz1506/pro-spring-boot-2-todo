package com.apress.reactor.example;

import com.apress.reactor.example.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;


/**
 * @author Pavel Zaytsev
 */
@Configuration
public class MonoExample {

    static final Logger LOG = LoggerFactory.getLogger(MonoExample.class);

    @Bean
    public CommandLineRunner runMonoExample() {
        return args -> {
            MonoProcessor<ToDo> promise = MonoProcessor.create();

            Mono<ToDo> result = promise.doOnSuccess(p -> LOG.info("MONO >> ToDo: {}", p.getDescription()))
                                         .doOnTerminate(() -> LOG.info("MONO >> Done"))
                                         .doOnError(t -> LOG.error(t.getMessage()))
                                         .subscribeOn(Schedulers.single());

            promise.onNext(new ToDo("Be happy!"));

//            promise.onError(new IllegalArgumentException("Some error")); // Бросать искл. мы не хотим
            result.block(Duration.ofMillis(1000));
        };
    }
}
