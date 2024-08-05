package com.demo.KafkaSpringBoot;

import com.demo.KafkaSpringBoot.producer.WikimediaChangeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }

    public ProducerApplication(){}

    @Autowired
    private WikimediaChangeProducer wikimediaChangeProducer;

    @Override
    public void run(String... args) throws Exception {
        wikimediaChangeProducer.sendMessage();
    }
}
