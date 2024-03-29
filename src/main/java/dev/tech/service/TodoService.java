package dev.tech.service;

import dev.tech.domain.Status;
import dev.tech.service.dto.TodoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoService {
    void addTodo(TodoDto todoDto);

    TodoDto findById(Long id);

    TodoDto findByTask(String task);

    List<TodoDto> findByStatus(Status status);

    List<TodoDto> findAll();

    Page<TodoDto> findAllPaginated(int page, int size, String sortField, String sortDirection);

    TodoDto update(Long id, TodoDto todoDto);

    void deleteById(Long id);

    void deleteAll();
}