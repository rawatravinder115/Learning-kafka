package com.demo.kafka.kafkaApplication.controller;

import com.demo.kafka.kafkaApplication.kafka.JsonKafkaProducer;
import com.demo.kafka.kafkaApplication.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/kafka")
public class JsonMessageController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMessageController.class);


    private JsonKafkaProducer jsonKafkaProducer;

    public JsonMessageController(JsonKafkaProducer jsonKafkaProducer){
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
//        LOGGER.info("Message Sent --> %s ",user.toString());
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Json message sent to kafka topic ");
    }

}
