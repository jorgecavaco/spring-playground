package com.github.spring.kafka.controller;

import com.github.spring.kafka.model.KafkaMessage;
import com.github.spring.kafka.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceMessageController {

    private final KafkaProducerService kafkaProducerService;

    public ProduceMessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/produce")
    public void publishMessage(@RequestBody KafkaMessage message){
        kafkaProducerService.sendMessage(message);
    }
}
