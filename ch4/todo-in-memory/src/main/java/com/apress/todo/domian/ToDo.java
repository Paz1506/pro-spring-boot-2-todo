package com.apress.todo.domian;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Основной класс доменной модели - Заметко/Дело
 *
 * @author Pavel Zaytsev
 */
@Data
public class ToDo {

    @NotNull
    private String id;

    @NotNull
    @NotEmpty
    private String description;

    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;

    public ToDo() {
        LocalDateTime date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = date;
        this.modified = date;
    }

    public ToDo(@NotNull @NotEmpty String description) {
        this();
        this.description = description;
    }
}
