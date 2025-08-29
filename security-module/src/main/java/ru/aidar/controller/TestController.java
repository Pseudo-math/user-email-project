package ru.aidar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aidar.service.AuthService;

@RequestMapping("/secure")
@RestController
public class TestController {
    private AuthService authService;

    @GetMapping
    public String getThing() {
        return "Test Method";
    }
}
