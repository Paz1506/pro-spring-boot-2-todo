package com.apress.todo.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, для проверки корректности данных, хранящий возникающие ошибки
 *
 * @author Pavel Zaytsev
 */
public class ToDoValidationError {

    @Getter
    /** пустые не будут включены */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    @Getter
    private final String errorMessage;


    public ToDoValidationError(String errorMessage) {this.errorMessage = errorMessage;}

    public void addValidaationError(String error) {
        this.errors.add(error);
    }
}
