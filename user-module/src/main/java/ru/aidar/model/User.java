package ru.aidar.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Schema(description = "Entity for User")
@Entity
@Table(name = "users")
public class User {
    @Schema(description = "Unique identificator for user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Username for user")
    @Column(nullable = false, unique = true)
    private String username;

    @Schema(description = "email for user")
    @Column(nullable = false, unique = true)
    private String email;

    @Schema(description = "password for user")
    @Column(nullable = false)
    private String password;

    @Schema(description = "время создания for user")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Геттеры и сеттеры (можно сгенерировать в IDE или использовать Lombok)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}