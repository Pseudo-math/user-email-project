package ru.aidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aidar.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserName(String name);
}
