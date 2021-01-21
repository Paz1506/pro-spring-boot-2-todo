package com.apress.reactor.example.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Pavel Zaytsev
 */
@Data
public class ToDo {

    private String id;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;

    public ToDo() {
    }

    public ToDo(String description) {
        this.description = description;
    }

    public ToDo(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }
}
