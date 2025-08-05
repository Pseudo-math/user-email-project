package ru.aidar.dto;

import ru.aidar.model.MessageType;

public class NotificationRequestDto {
    private String email;
    private MessageType messageType; // Добавляем это поле

    public NotificationRequestDto() {
    }

    public NotificationRequestDto(String email, MessageType messageType) {
        this.email = email;
        this.messageType = messageType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}