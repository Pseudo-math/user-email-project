package ru.aidar.repository;

import ru.aidar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA автоматически создаст запрос для поиска по имени пользователя
    Optional<User> findByUsername(String username);

    // И для поиска по почте
    Optional<User> findByEmail(String email);

    // Также можно проверять существование
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}