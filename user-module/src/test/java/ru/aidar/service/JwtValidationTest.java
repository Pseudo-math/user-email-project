package ru.aidar.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;

import ru.aidar.config.JwtTestConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {JwtTestConfig.class})
public class JwtValidationTest {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Test
    void generatedTokenShouldBeValidForDecoder() {
        String testEmail = "test@example.com";
        Long testUserId = 999L;

        String jwtString = tokenProvider.generateToken(testEmail, testUserId);

        // 2. Act: Попытка декодировать токен с помощью JwtDecoder
        Jwt decodedJwt = jwtDecoder.decode(jwtString);

        // 3. Assert: Проверка
        assertNotNull(decodedJwt, "Токен не должен быть null после декодирования.");
        assertEquals(testEmail, decodedJwt.getSubject(), "Subject должен совпадать с email.");
        // Используем явное преобразование, как мы обсуждали
        assertEquals(testUserId, ((Number) decodedJwt.getClaim("userId")).longValue(), "Claim 'userId' должен совпадать.");

        // 4. Дополнительный тест: Проверка Invalid Signature
        String invalidJwt = jwtString.substring(0, jwtString.length() - 5) + "XABCD"; // <-- ИСПРАВЛЕНИЕ: Инициализация переменной

        assertThrows(BadJwtException.class, () -> {
            jwtDecoder.decode(invalidJwt);
        }, "Декодер должен выбросить ошибку при неверной подписи.");
    }
}