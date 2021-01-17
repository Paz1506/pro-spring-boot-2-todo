package com.apress.todo.domian;

/**
 * Фабрика для создания экземпляров ToDo
 *
 * @author Pavel Zaytsev
 */
public class ToDoBuilder {

    private static ToDoBuilder instance = new ToDoBuilder();
    private String id = null;
    private String description = "";

    public static ToDoBuilder create() {
        return instance;
    }

    private ToDoBuilder() {
    }

    public ToDoBuilder withDescription(String description) {
        this.description = description;
        return instance;
    }

    public ToDoBuilder withId(String id) {
        this.id = id;
        return instance;
    }

    public ToDo build() {
        ToDo result = new ToDo(this.description);
        if (this.id != null) {
            result.setId(this.id);
        }

        return result;
    }

}
