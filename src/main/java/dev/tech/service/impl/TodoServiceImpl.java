package dev.tech.service.impl;

import dev.tech.domain.Status;
import dev.tech.domain.Todo;
import dev.tech.service.dto.TodoDto;
import dev.tech.service.mapper.TodoMapper;
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
    private final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void addTodo(TodoDto todoDto) {
        this.logger.info("INFO: Todo Service - addTodo() method called.");
        this.logger.debug("DEBUG: Todo Service - addTodo() method called.");
        this.logger.trace("TRACE: Todo Service - addTodo() method called.");
        this.logger.warn("WARN: Todo Service - addTodo() method called.");
        this.logger.error("ERROR: Todo Service - addTodo() method called.");

        try {
            this.todoRepository.save(TodoMapper.toEntity(todoDto));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public TodoDto findById(Long id) {
        this.logger.info("INFO: Todo Service - findById() method called.");
        this.logger.debug("DEBUG: Todo Service - findById() method called.");
        this.logger.trace("TRACE: Todo Service - findById() method called.");
        this.logger.warn("WARN: Todo Service - findById() method called.");
        this.logger.error("ERROR: Todo Service - findById() method called.");

        try {
            return TodoMapper.toDto(this.todoRepository.findById(id).get());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TodoDto> findByStatus(Status status) {
        this.logger.info("INFO: Todo Service - findByStatus() method called.");
        this.logger.debug("DEBUG: Todo Service - findByStatus() method called.");
        this.logger.trace("TRACE: Todo Service - findByStatus() method called.");
        this.logger.warn("WARN: Todo Service - findByStatus() method called.");
        this.logger.error("ERROR: Todo Service - findByStatus() method called.");

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
        this.logger.info("INFO: Todo Service - findByTask() method called.");
        this.logger.debug("DEBUG: Todo Service - findByTask() method called.");
        this.logger.trace("TRACE: Todo Service - findByTask() method called.");
        this.logger.warn("WARN: Todo Service - findByTask() method called.");
        this.logger.error("ERROR: Todo Service - findByTask() method called.");

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
        this.logger.info("INFO: Todo Service - findAll() method called.");
        this.logger.debug("DEBUG: Todo Service - findAll() method called.");
        this.logger.trace("TRACE: Todo Service - findAll() method called.");
        this.logger.warn("WARN: Todo Service - findAll() method called.");
        this.logger.error("ERROR: Todo Service - findAll() method called.");

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
        this.logger.info("INFO: Todo Service - findAllPaginated() method called.");
        this.logger.debug("DEBUG: Todo Service - findAllPaginated() method called.");
        this.logger.trace("TRACE: Todo Service - findAllPaginated() method called.");
        this.logger.warn("WARN: Todo Service - findAllPaginated() method called.");
        this.logger.error("ERROR: Todo Service - findAllPaginated() method called.");

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Todo> todosPaginated = this.todoRepository.findAll(pageable);
        Page<TodoDto> todoDtosPaginated = todosPaginated.map(element -> TodoMapper.toDto(element));

        return todoDtosPaginated;
    }

    @Override
    public TodoDto update(Long id, TodoDto todoDto) {
        this.logger.info("INFO: Todo Service - update() method called.");
        this.logger.debug("DEBUG: Todo Service - update() method called.");
        this.logger.trace("TRACE: Todo Service - update() method called.");
        this.logger.warn("WARN: Todo Service - update() method called.");
        this.logger.error("ERROR: Todo Service - update() method called.");

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
        this.logger.info("INFO: Todo Service - deleteById() method called.");
        this.logger.debug("DEBUG: Todo Service - deleteById() method called.");
        this.logger.trace("TRACE: Todo Service - deleteById() method called.");
        this.logger.warn("WARN: Todo Service - deleteById() method called.");
        this.logger.error("ERROR: Todo Service - deleteById() method called.");

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
        this.logger.info("INFO: Todo Service - deleteAll() method called.");
        this.logger.debug("DEBUG: Todo Service - deleteAll() method called.");
        this.logger.trace("TRACE: Todo Service - deleteAll() method called.");
        this.logger.warn("WARN: Todo Service - deleteAll() method called.");
        this.logger.error("ERROR: Todo Service - deleteAll() method called.");

        if (!this.todoRepository.findAll().isEmpty()) {
            try {
                this.todoRepository.deleteAll();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}