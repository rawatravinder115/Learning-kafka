package com.demo.kafka.kafkaApplication.controller;

import com.demo.kafka.kafkaApplication.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class kafkaController {

    private KafkaProducer kafkaProducer;

    public kafkaController(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(kafkaController.class);

//    URL - http://localhost:8080/api/v1/kafka/publish
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
        kafkaProducer.sentMessage(message);
        LOGGER.info(String.format("Message Received from API %s",message));
        return ResponseEntity.ok("Message Succesfully Sent to Topic");
    }

}
