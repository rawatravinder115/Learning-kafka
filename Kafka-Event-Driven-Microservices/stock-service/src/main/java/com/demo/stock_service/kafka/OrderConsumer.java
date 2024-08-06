package com.demo.stock_service.kafka;

import com.demo.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.producer.topic-name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumerMessage(OrderEvent orderEvent){
        LOGGER.info(String.format("Message Received --> %s",orderEvent.toString()));
    }

}
