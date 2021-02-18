package dev.tech.dto.model;

import dev.tech.domain.Todo;
import dev.tech.dto.mapper.TodoDto;

public class TodoMapper {
    public static TodoDto toDto(Todo todo) {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setTask(todo.getTask());
        todoDto.setStatus(todo.getStatus());
        return todoDto;
    }

    public static Todo toEntity(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTask(todoDto.getTask());
        todo.setStatus(todoDto.getStatus());
        return todo;
    }
}