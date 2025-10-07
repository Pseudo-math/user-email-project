package ru.aidar.controller;

import ru.aidar.dto.NotificationRequestDto;
import ru.aidar.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDto request) {
        notificationService.sendNotification(request);
        return ResponseEntity.ok("Запрос на отправку " + request.getMessageType() + " письма принят для: " + request.getEmail());
    }
}