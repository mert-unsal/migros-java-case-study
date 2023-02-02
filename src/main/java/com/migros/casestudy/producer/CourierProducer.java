package com.migros.casestudy.producer;

import com.migros.casestudy.factory.Factory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class CourierProducer {

    private final KafkaTemplate kafkaTemplate;
    private final Factory factory;

    @Value("#{topics['case-study-consumer'].name}")
    private String topic;

    public CourierProducer(KafkaTemplate kafkaTemplate, @Qualifier("courierFactory") Factory factory) {
        this.kafkaTemplate = kafkaTemplate;
        this.factory = factory;
    }

    @PostConstruct
    public void sendCourierEvents() {
        factory.readResource().forEach(courierEvent -> {
            kafkaTemplate.send(topic,courierEvent);
            log.info("Event is produced to topic : {} CourierEvent : {}", topic,courierEvent);
        });
    }

}