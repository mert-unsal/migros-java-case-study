package com.migros.casestudy.producer;

import com.migros.casestudy.factory.CourierFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@EnableScheduling
@RequiredArgsConstructor
@Component
public class CourierProducer {

    private final KafkaTemplate kafkaTemplate;
    private final CourierFactory courierFactory;

    @Value("#{topics['case-study-consumer'].name}")
    private String topic;

    @PostConstruct
    public void sendCourierEvents() {
        courierFactory.readResource().forEach(courierEvent -> {
            kafkaTemplate.send(topic,courierEvent);
        });
    }

}