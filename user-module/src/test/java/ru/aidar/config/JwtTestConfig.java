package ru.aidar.config; // Используем пакет config для удобства импорта

import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import ru.aidar.service.JwtTokenProvider; // Ваш класс генератора

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
// Импортируем только класс генератора, SecurityConfig не нужен
@Import({JwtTokenProvider.class})
public class JwtTestConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    // --- 1. JwtDecoder (Как в SecurityConfig, но без @EnableWebSecurity) ---
    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] secretBytes = Decoders.BASE64.decode(jwtSecret);
        SecretKey secretKey = new SecretKeySpec(secretBytes, "HmacSHA512");

        return NimbusJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    // --- 2. Мокирование зависимостей JwtTokenProvider ---
    // (Если JwtTokenProvider имеет другие зависимости, кроме @Value, их нужно мокировать здесь.
    // Если его конструктор пуст или он зависит только от @Value, этот шаг можно пропустить.)
    // Пример (если бы он зависел от UserService):
    // @Bean
    // public UserService userService() {
    //     return Mockito.mock(UserService.class);
    // }
}