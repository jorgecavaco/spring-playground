package com.github.spring.kafka.service;

import com.github.spring.kafka.model.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}")
    public void listener(@Payload KafkaMessage message){
        LOGGER.info("Received :: {}", message);
    }
}
