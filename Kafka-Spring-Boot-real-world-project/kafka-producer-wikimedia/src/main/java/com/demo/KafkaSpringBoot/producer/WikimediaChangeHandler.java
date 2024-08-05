package com.demo.KafkaSpringBoot.producer;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChangeHandler implements BackgroundEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangeHandler.class);

    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangeHandler(){}

    public WikimediaChangeHandler(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("kafkaTemplate --> %s",kafkaTemplate.toString()));
        LOGGER.info(String.format("Event Data --> %s",messageEvent.getData()));
        kafkaTemplate.send("wikimedia_recentChange",messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
