package com.demo.KafkaSpringBoot.config;

import com.demo.KafkaSpringBoot.entity.WikimediaData;
import com.demo.KafkaSpringBoot.repositpory.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;

    @KafkaListener(topics = "wikimedia_recentChange",groupId = "myGroup")
    public void consumeMessage(String eventMessage){
        LOGGER.info(String.format("Event Message Consumed --> %S",eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);

        wikimediaDataRepository.save(wikimediaData);
    }
}
