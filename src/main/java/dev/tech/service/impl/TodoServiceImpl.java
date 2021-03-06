package dev.tech.service.impl;

import dev.tech.domain.Status;
import dev.tech.domain.Todo;
import dev.tech.dto.mapper.TodoDto;
import dev.tech.dto.model.TodoMapper;
import dev.tech.repository.TodoRepository;
import dev.tech.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void addTodo(TodoDto todoDto) {
        try {
            this.todoRepository.save(TodoMapper.toEntity(todoDto));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public TodoDto findById(Long id) {
        try {
            return TodoMapper.toDto(this.todoRepository.findById(id).get());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TodoDto> findByStatus(Status status) {
        try {
            List<Todo> todos = this.todoRepository.findByStatus(status);
            List<TodoDto> todoDtos = new ArrayList<TodoDto>();
            for (Todo todo : todos) {
                todoDtos.add(TodoMapper.toDto(todo));
            }

            return todoDtos;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public TodoDto findByTask(String task) {
        if (this.todoRepository.findByTask(task) != null) {
            try {
                return TodoMapper.toDto(this.todoRepository.findByTask(task));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            return null;
        }

        return null;
    }

    @Override
    public List<TodoDto> findAll() {
        if (this.todoRepository.findAll().isEmpty()) {
            return null;
        } else {
            try {
                List<Todo> todos = this.todoRepository.findAll();
                List<TodoDto> todoDtos = new ArrayList<TodoDto>();
                for (Todo todo : todos) {
                    todoDtos.add(TodoMapper.toDto(todo));
                }

                return todoDtos;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Page<TodoDto> findAllPaginated(int page, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Todo> todosPaginated = this.todoRepository.findAll(pageable);
        Page<TodoDto> todoDtosPaginated = todosPaginated.map(element -> TodoMapper.toDto(element));

        return todoDtosPaginated;
    }

    @Override
    public TodoDto update(Long id, TodoDto todoDto) {
        if (this.todoRepository.findById(id) != null) {
            try {
                Todo todo = this.todoRepository.findById(id).get();
                todo.setId(todoDto.getId());
                todo.setTask(todoDto.getTask());
                todo.setStatus(todoDto.getStatus());
                this.todoRepository.save(todo);

                return TodoMapper.toDto(todo);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            return null;
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        if (this.todoRepository.findById(id) != null) {
            try {
                this.todoRepository.deleteById(id);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAll() {
        if (!this.todoRepository.findAll().isEmpty()) {
            try {
                this.todoRepository.deleteAll();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}