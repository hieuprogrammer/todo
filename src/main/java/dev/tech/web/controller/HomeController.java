package dev.tech.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "redirect:/todos";
    }

    @GetMapping("swagger")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/403")
    public String _403() {
        return "403";
    }

    @GetMapping("/404")
    public String _404() {
        return "404";
    }
}