package ru.aidar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aidar.entity.UserCredentional;
import ru.aidar.service.AuthService;
//TODO: globalExeceptionHandler
@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewUser(@RequestBody UserCredentional user) {
        return authService.saveUser(user);
    }

    @GetMapping("/token")
    public String generateToken(@RequestBody UserCredentional userCredentional) {
        return authService.generateToken(userCredentional.getName());
    }

    @GetMapping("/refresh-token")
    public String generateRefreshToken(@RequestBody UserCredentional userCredentional) {
        return "";
    }

    @PostMapping("/refresh")
    public String generateTokenByRefreshToken(@RequestBody UserCredentional userCredentional) {
        return "";
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }


}
