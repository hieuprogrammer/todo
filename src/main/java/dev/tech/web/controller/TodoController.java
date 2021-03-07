package dev.tech.web.controller;

import dev.tech.dto.mapper.TodoDto;
import dev.tech.service.EmailService;
import dev.tech.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);
    private final TodoService todoService;
    private final EmailService emailService;

    @Autowired
    public TodoController(TodoService todoService, EmailService emailService) {
        this.todoService = todoService;
        this.emailService = emailService;
    }

    @GetMapping
    public String getTodos(final Model model) {
        this.logger.info("INFO: Todo Controller - getTodos() method called.");
        this.logger.debug("DEBUG: Todo Controller - getTodos() method called.");
        this.logger.trace("TRACE: Todo Controller - getTodos() method called.");
        this.logger.warn("WARN: Todo Controller - getTodos() method called.");
        this.logger.error("ERROR: Todo Controller - getTodos() method called.");

//        model.addAttribute("todos", this.todoService.findAll());
//        return "index";

        return this.getTodosPaginated(1, "task", "asc", model);
    }

    @GetMapping("/{page}")
    public String getTodosPaginated(
    		final @PathVariable(value = "page") int page,
    		final @RequestParam("sortField") String sortField,
    		final @RequestParam("sortDirection") String sortDirection,
    		final Model model
	) {
        this.logger.info("INFO: Todo Controller - getTodosPaginated() method called.");
        this.logger.debug("DEBUG: Todo Controller - getTodosPaginated() method called.");
        this.logger.trace("TRACE: Todo Controller - getTodosPaginated() method called.");
        this.logger.warn("WARN: Todo Controller - getTodosPaginated() method called.");
        this.logger.error("ERROR: Todo Controller - getTodosPaginated() method called.");

        int size = 7;
        Page<TodoDto> todosPaginated = this.todoService.findAllPaginated(page, size, sortField, sortDirection);
        List<TodoDto> todos = todosPaginated.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", todosPaginated.getTotalPages());
        model.addAttribute("totalElements", todosPaginated.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("todos", todos);

        return "index";
    }

    @GetMapping("/new")
    public String getAddTodoHtmlForm(final Model model) {
        this.logger.info("INFO: Todo Controller - getAddTodoHtmlForm() method called.");
        this.logger.debug("DEBUG: Todo Controller - getAddTodoHtmlForm() method called.");
        this.logger.trace("TRACE: Todo Controller - getAddTodoHtmlForm() method called.");
        this.logger.warn("WARN: Todo Controller - getAddTodoHtmlForm() method called.");
        this.logger.error("ERROR: Todo Controller - getAddTodoHtmlForm() method called.");

        model.addAttribute("todoDto", new TodoDto());
        return "add";
    }

    @PostMapping(value = "/new")
    public String addTodo(
            final Model model,
            final @ModelAttribute("todoDto") TodoDto todoDto,
            final BindingResult bindingResult
    ) {
        this.logger.info("INFO: Todo Controller - addTodo() method called.");
        this.logger.debug("DEBUG: Todo Controller - addTodo() method called.");
        this.logger.trace("TRACE: Todo Controller - addTodo() method called.");
        this.logger.warn("WARN: Todo Controller - addTodo() method called.");
        this.logger.error("ERROR: Todo Controller - addTodo() method called.");

        if (bindingResult.hasErrors()) {
            return "add";
        } else {
            try {
                this.todoService.addTodo(todoDto);
//                this.emailService.sendEmail();
//                this.emailService.sendEmailWithAttachments();
                this.emailService.sendEmailWithHtmlContent();
                return "redirect:/todos";
            } catch (Exception exception) {
                model.addAttribute("error", exception.toString());
                exception.printStackTrace();
            }
        }

        return "add";
    }

    @GetMapping("/detail")
    public String getTodoDetailById(final Model model, final @RequestParam("id") Long id) {
        this.logger.info("INFO: Todo Controller - getTodoDetailById() method called.");
        this.logger.debug("DEBUG: Todo Controller - getTodoDetailById() method called.");
        this.logger.trace("TRACE: Todo Controller - getTodoDetailById() method called.");
        this.logger.warn("WARN: Todo Controller - getTodoDetailById() method called.");
        this.logger.error("ERROR: Todo Controller - getTodoDetailById() method called.");

        model.addAttribute("todoDto", this.todoService.findById(id));
        return "detail";
    }

    @GetMapping("/update")
    public String getUpdateTodoHtmlForm(final Model model, final @RequestParam("id") Long id) {
        this.logger.info("INFO: Todo Controller - getUpdateTodoHtmlForm() method called.");
        this.logger.debug("DEBUG: Todo Controller - getUpdateTodoHtmlForm() method called.");
        this.logger.trace("TRACE: Todo Controller - getUpdateTodoHtmlForm() method called.");
        this.logger.warn("WARN: Todo Controller - getUpdateTodoHtmlForm() method called.");
        this.logger.error("ERROR: Todo Controller - getUpdateTodoHtmlForm() method called.");

        model.addAttribute("todoDto", this.todoService.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateTodo(
            final Model model,
            final @RequestParam("id") Long id,
            final @ModelAttribute("todoDto") TodoDto todoDto,
            final BindingResult bindingResult
    ) {
        this.logger.info("INFO: Todo Controller - updateTodo() method called.");
        this.logger.debug("DEBUG: Todo Controller - updateTodo() method called.");
        this.logger.trace("TRACE: Todo Controller - updateTodo() method called.");
        this.logger.warn("WARN: Todo Controller - updateTodo() method called.");
        this.logger.error("ERROR: Todo Controller - updateTodo() method called.");

        if (bindingResult.hasErrors()) {
            return "update";
        } else {
            try {
                this.todoService.update(id, todoDto);
                return "redirect:/todos";
            } catch (Exception exception) {
                model.addAttribute("error", exception.toString());
                exception.printStackTrace();
            }
        }

        return "update";
    }

    @GetMapping("/delete")
    public String deleteTodoById(final Model model, final @RequestParam("id") Long id) {
        this.logger.info("INFO: Todo Controller - deleteTodoById() method called.");
        this.logger.debug("DEBUG: Todo Controller - deleteTodoById() method called.");
        this.logger.trace("TRACE: Todo Controller - deleteTodoById() method called.");
        this.logger.warn("WARN: Todo Controller - deleteTodoById() method called.");
        this.logger.error("ERROR: Todo Controller - deleteTodoById() method called.");

        if (this.todoService.findById(id) == null) {
            return "redirect:/todos";
        } else {
            try {
                this.todoService.deleteById(id);
                return "redirect:/todos";
            } catch (Exception exception) {
                model.addAttribute("error", exception.toString());
                exception.printStackTrace();
            }
        }

        return "redirect:/todos";
    }

    @GetMapping("/delete-all")
    public String deleteAll(final Model model) {
        this.logger.info("INFO: Todo Controller - deleteAll() method called.");
        this.logger.debug("DEBUG: Todo Controller - deleteAll() method called.");
        this.logger.trace("TRACE: Todo Controller - deleteAll() method called.");
        this.logger.warn("WARN: Todo Controller - deleteAll() method called.");
        this.logger.error("ERROR: Todo Controller - deleteAll() method called.");

        if (this.todoService.findAll().isEmpty()) {
            model.addAttribute("error", "You don't have any tasks to delete.");
            return "redirect:/todos";
        } else {
            try {
                this.todoService.deleteAll();
                return "redirect:/todos";
            } catch (Exception exception) {
                model.addAttribute("error", exception.toString());
                exception.printStackTrace();
            }
        }

        return "redirect:/todos";
    }
}