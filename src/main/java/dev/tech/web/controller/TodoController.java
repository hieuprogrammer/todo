package dev.tech.web.controller;

import dev.tech.dto.mapper.TodoDto;
import dev.tech.service.EmailService;
import dev.tech.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final EmailService emailService;

    @Autowired
    public TodoController(TodoService todoService, EmailService emailService) {
        this.todoService = todoService;
        this.emailService = emailService;
    }

    @GetMapping
    public String getTodos(final Model model) {
        model.addAttribute("todos", this.todoService.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String getAddTodoHtmlForm(final Model model) {
        model.addAttribute("todoDto", new TodoDto());
        return "add";
    }

    @PostMapping(value = "/new")
    public String addTodo(final Model model, final @ModelAttribute("todoDto") TodoDto todoDto, final BindingResult bindingResult) {
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
        model.addAttribute("todoDto", this.todoService.findById(id));
        return "detail";
    }

    @GetMapping("/update")
    public String getUpdateTodoHtmlForm(final Model model, final @RequestParam("id") Long id) {
        model.addAttribute("todoDto", this.todoService.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateTodo(final Model model, final @RequestParam("id") Long id, final @ModelAttribute("todoDto") TodoDto todoDto, final BindingResult bindingResult) {
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