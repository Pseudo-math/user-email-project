package ru.aidar.handler;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HandlerUserEvent {
    private final String topic = "user_topic";

    @KafkaListener(topics = topic, groupId = "notification-group")
    public void handleUserEvent(String value) {
        System.out.println("value " + value + " пришло от producer-а");
    }

}
