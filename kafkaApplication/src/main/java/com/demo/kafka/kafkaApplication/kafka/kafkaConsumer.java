package com.demo.kafka.kafkaApplication.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(kafkaConsumer.class);

    @KafkaListener(topics = "demoKafka",groupId = "myGroup")
    public void comsume(String messsage){
        LOGGER.info(String.format("Message Received from Server -> %s",messsage));
    }
}
