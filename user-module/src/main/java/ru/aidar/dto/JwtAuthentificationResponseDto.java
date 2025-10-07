package ru.aidar.dto;

public class JwtAuthentificationResponseDto {
    private String token;
    private final String tokenType = "Bearer";

    public JwtAuthentificationResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }
}
