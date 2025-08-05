package ru.aidar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//TODO: подключить бд к модулю, добавить endpoints для добавления/удаления пользователей
//optional: +kafka for docker compose
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}