package com.github.spring.kafka.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class KafkaMessage {
    private final String id = UUID.randomUUID().toString();
    private final LocalDateTime messageDate = LocalDateTime.now();
    private String message;

    public String getId() {
        return id;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public KafkaMessage(String message) {
        this.message = message;
    }

    public KafkaMessage() {
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "id='" + id + '\'' +
                ", messageDate=" + messageDate +
                ", message='" + message + '\'' +
                '}';
    }
}
