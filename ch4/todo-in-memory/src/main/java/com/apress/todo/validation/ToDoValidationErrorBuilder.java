package com.apress.todo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Фабрика, для создания экземпляров ToDoValidationError
 *
 * @author Pavel Zaytsev
 */
public class ToDoValidationErrorBuilder {

    public static ToDoValidationError fromBindingErrors(Errors errors) {
        ToDoValidationError error = new ToDoValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");

        for (ObjectError errorError : errors.getAllErrors()) {
            error.addValidaationError(errorError.getDefaultMessage());
        }

        return error;
    }
}
