package ru.aidar.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class UserCredentional {
    private String id;
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }
}
