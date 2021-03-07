package dev.tech.web.api.rest;

import dev.tech.domain.Status;
import dev.tech.dto.mapper.TodoDto;
import dev.tech.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoRestApi {
    private final Logger logger = LoggerFactory.getLogger(TodoRestApi.class);
    private final TodoService todoService;

    @Autowired
    public TodoRestApi(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public HttpEntity<?> addTodo(@RequestBody TodoDto todoDto) {
        this.logger.info("INFO: Todo REST API - addTodo() method called.");
        this.logger.debug("DEBUG: Todo REST API - addTodo() method called.");
        this.logger.trace("TRACE: Todo REST API - addTodo() method called.");
        this.logger.warn("WARN: Todo REST API - addTodo() method called.");
        this.logger.error("ERROR: Todo REST API - addTodo() method called.");

        this.todoService.addTodo(todoDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public HttpEntity<?> getTodos() {
        this.logger.info("INFO: Todo REST API - getTodos() method called.");
        this.logger.debug("DEBUG: Todo REST API - getTodos() method called.");
        this.logger.trace("TRACE: Todo REST API - getTodos() method called.");
        this.logger.warn("WARN: Todo REST API - getTodos() method called.");
        this.logger.error("ERROR: Todo REST API - getTodos() method called.");

        if (this.todoService.findAll().isEmpty()) {
            try {
                return new ResponseEntity<String>("You don't have any tasks to do.", HttpStatus.NO_CONTENT);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving all tasks." + exception.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            try {
                return new ResponseEntity<List<TodoDto>>(this.todoService.findAll(), HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving all tasks." + exception.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findTodoById(@PathVariable("id") Long id) {
        this.logger.info("INFO: Todo REST API - findTodoById() method called.");
        this.logger.debug("DEBUG: Todo REST API - findTodoById() method called.");
        this.logger.trace("TRACE: Todo REST API - findTodoById() method called.");
        this.logger.warn("WARN: Todo REST API - findTodoById() method called.");
        this.logger.error("ERROR: Todo REST API - findTodoById() method called.");

        if (this.todoService.findById(id) == null) {
            try {
                return new ResponseEntity<String>("Task with ID number: " + id + " does not exist.", HttpStatus.NOT_FOUND);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving task with ID number: " + id + "." + exception.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            try {
                return new ResponseEntity<TodoDto>(this.todoService.findById(id), HttpStatus.FOUND);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving task with ID number: " + id + "." + exception.toString(), HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }

    @GetMapping("/{task}")
    public HttpEntity<?> findTodoByTask(@PathVariable("task") String task) {
        this.logger.info("INFO: Todo REST API - findTodoByTask() method called.");
        this.logger.debug("DEBUG: Todo REST API - findTodoByTask() method called.");
        this.logger.trace("TRACE: Todo REST API - findTodoByTask() method called.");
        this.logger.warn("WARN: Todo REST API - findTodoByTask() method called.");
        this.logger.error("ERROR: Todo REST API - findTodoByTask() method called.");

        if (this.todoService.findByTask(task) == null) {
            try {
                return new ResponseEntity<String>("To-do with task detail: \"" + task + "\" does not exist.", HttpStatus.NOT_FOUND);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving to-do with task: \"" + task + "\"." + exception.toString(), HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                return new ResponseEntity<TodoDto>(this.todoService.findByTask(task), HttpStatus.FOUND);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving to-do with task detail: \"" + task + "\"." + exception.toString(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/{status}")
    public HttpEntity<?> findTodosByStatus(@PathVariable("status") Status status) {
        this.logger.info("INFO: Todo REST API - findTodosByStatus() method called.");
        this.logger.debug("DEBUG: Todo REST API - findTodosByStatus() method called.");
        this.logger.trace("TRACE: Todo REST API - findTodosByStatus() method called.");
        this.logger.warn("WARN: Todo REST API - findTodosByStatus() method called.");
        this.logger.error("ERROR: Todo REST API - findTodosByStatus() method called.");

        if (this.todoService.findByStatus(status) == null) {
            try {
                return new ResponseEntity<String>("You don't have any tasks with status: \"" + status + "\".", HttpStatus.NO_CONTENT);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving tasks with status: \"" + status + "\"." + exception.toString(), HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                return new ResponseEntity<List<TodoDto>>(this.todoService.findByStatus(status), HttpStatus.FOUND);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving tasks with status: \"" + status + "\"." + exception.toString(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
        this.logger.info("INFO: Todo REST API - updateTodo() method called.");
        this.logger.debug("DEBUG: Todo REST API - updateTodo() method called.");
        this.logger.trace("TRACE: Todo REST API - updateTodo() method called.");
        this.logger.warn("WARN: Todo REST API - updateTodo() method called.");
        this.logger.error("ERROR: Todo REST API - updateTodo() method called.");

        if (this.todoService.findById(id) == null) {
            try {
                return new ResponseEntity<String>("You don't have any task with ID number: " + id + ".", HttpStatus.NOT_FOUND);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured looking for task with ID number: " + id + " to update.",  HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                return new ResponseEntity<TodoDto>(this.todoService.update(id, todoDto), HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured looking for task with ID number: " + id + " to update.",  HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTodoById(@PathVariable("id") Long id) {
        this.logger.info("INFO: Todo REST API - deleteTodoById() method called.");
        this.logger.debug("DEBUG: Todo REST API - deleteTodoById() method called.");
        this.logger.trace("TRACE: Todo REST API - deleteTodoById() method called.");
        this.logger.warn("WARN: Todo REST API - deleteTodoById() method called.");
        this.logger.error("ERROR: Todo REST API - deleteTodoById() method called.");

        if (this.todoService.findById(id) == null) {
            try {
                return new ResponseEntity<String>("You don't have any task with ID number: " + id + ".", HttpStatus.NOT_FOUND);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured looking for task with ID number: " + id + " to delete.",  HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                this.todoService.deleteById(id);
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured looking for task with ID number: " + id + " to delete.",  HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping
    public HttpEntity<?> deleteAllTodos() {
        this.logger.info("INFO: Todo REST API - deleteAllTodos() method called.");
        this.logger.debug("DEBUG: Todo REST API - deleteAllTodos() method called.");
        this.logger.trace("TRACE: Todo REST API - deleteAllTodos() method called.");
        this.logger.warn("WARN: Todo REST API - deleteAllTodos() method called.");
        this.logger.error("ERROR: Todo REST API - deleteAllTodos() method called.");

        if (this.todoService.findAll().isEmpty()) {
            try {
                return new ResponseEntity<String>("You don't have any tasks to delete.", HttpStatus.NO_CONTENT);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving all tasks to delete.",  HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                this.todoService.deleteAll();
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } catch (Exception exception) {
                return new ResponseEntity<String>("Unknown error(s) occured retrieving all tasks to delete.",  HttpStatus.BAD_REQUEST);
            }
        }
    }
}