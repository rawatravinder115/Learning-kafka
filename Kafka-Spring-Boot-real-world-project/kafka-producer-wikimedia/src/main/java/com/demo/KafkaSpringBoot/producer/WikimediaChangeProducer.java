package com.demo.KafkaSpringBoot.producer;

import com.launchdarkly.eventsource.ConnectStrategy;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangeProducer {


    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangeProducer.class);

    private String topicName = "wikimedia_recentChange";

    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangeProducer(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        LOGGER.info(String.format("KafkaTemplate --> %s",kafkaTemplate.toString()));
        BackgroundEventHandler eventHandler = new WikimediaChangeHandler(kafkaTemplate);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        BackgroundEventSource event = new BackgroundEventSource.Builder(eventHandler,
                new EventSource
                        .Builder(ConnectStrategy
                        .http(URI.create(url)))).build();
        event.start();
        TimeUnit.MINUTES.sleep(10);
    }


}
