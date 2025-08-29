package ru.aidar.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aidar.entity.UserCredentional;
import ru.aidar.repository.UserCredentionalRepository;

@Service
public class AuthService {
    private UserCredentionalRepository repository;
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;

    public AuthService(UserCredentionalRepository repository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    //в 22 строчке я поменял credentional.getPassword() на
    public String saveUser(UserCredentional credentional) {
        credentional.setPassword(passwordEncoder.encode(credentional.getPassword()));
        repository.save(credentional);
        return "UserCredentional added to the System";
    }

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }


}
