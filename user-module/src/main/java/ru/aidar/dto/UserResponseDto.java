package ru.aidar.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

// Этот DTO безопасен для отправки клиенту
@Schema
public class UserResponseDto {
    @Schema(description = "Unique identificator for user", example = "12")
    private Long id;
    @Schema(description = "Username for user", example = "valera")
    private String username;
    @Schema(description = "email for user", example = "valer@gmail.com")
    private String email;
    @Schema(description = "Created time for user", example = "i dont now")
    private LocalDateTime createdAt;

    // Пустой конструктор для Jackson
    public UserResponseDto() {}

    // Конструктор для маппинга
    public UserResponseDto(Long id, String username, String email, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}