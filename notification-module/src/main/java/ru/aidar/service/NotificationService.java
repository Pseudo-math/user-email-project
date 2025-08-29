package ru.aidar.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.aidar.dto.NotificationRequestDto;
import ru.aidar.model.MessageType;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNotification(NotificationRequestDto request) {
        if (request == null || request.getEmail() == null || request.getMessageType() == null) {
            throw new IllegalArgumentException("Запрос на уведомление не может быть null и должен содержать email и messageType.");
        }

        switch (request.getMessageType()) {
            case WELCOME:
                sendWelcomeEmail(request.getEmail());
                break;
            case GOODBYE:
                sendGoodbyeEmail(request.getEmail());
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый тип сообщения: " + request.getMessageType());
        }
    }

    private void sendWelcomeEmail(String email) {
        String subject = "Добро пожаловать!";
        String text = "Здравствуйте! Ваш аккаунт на сайте 'ваш сайт' был успешно создан.";
        this.sendEmail(email, subject, text);
    }

    private void sendGoodbyeEmail(String email) {
        String subject = "Аккаунт удалён";
        String text = "Здравствуйте! Ваш аккаунт был удалён.";
        this.sendEmail(email, subject, text);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}