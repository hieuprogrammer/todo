package dev.tech.service;

import dev.tech.domain.Status;
import dev.tech.dto.mapper.TodoDto;

import java.util.List;

public interface TodoService {
    void addTodo(TodoDto todoDto);

    TodoDto findById(Long id);

    TodoDto findByTask(String task);

    List<TodoDto> findByStatus(Status status);

    List<TodoDto> findAll();

    TodoDto update(Long id, TodoDto todoDto);

    void deleteById(Long id);

    void deleteAll();
}