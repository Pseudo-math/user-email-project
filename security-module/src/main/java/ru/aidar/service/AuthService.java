package ru.aidar.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aidar.entity.RefreshToken;
import ru.aidar.entity.UserCredentional;
import ru.aidar.repository.RefreshTokenRepository;
import ru.aidar.repository.UserCredentionalRepository;

import java.util.Optional;
import java.util.OptionalInt;

@Service
public class AuthService {
    private UserCredentionalRepository userCredentionalRepository;
    private RefreshTokenRepository refreshTokenRepository;
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;

    public AuthService(UserCredentionalRepository userCredentionalRepository, RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userCredentionalRepository = userCredentionalRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String saveUser(UserCredentional credentional) {
        credentional.setPassword(passwordEncoder.encode(credentional.getPassword()));
        userCredentionalRepository.save(credentional);
        return "UserCredentional added to the System";
    }
    public String generateRefreshToken(String username) {
        Optional<RefreshToken> token  = refreshTokenRepository.findByUserName(username);
        return jwtUtil.generateToken(username);
    }
    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }


}
