package com.migros.casestudy.producer;

import com.migros.casestudy.factory.Factory;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class CourierProducer {

    private final Map<Integer, KafkaTemplate<String, Object>> kafkaTemplateMap;
    private final Factory factory;
    @Value("${kafka-configuration.topics.case-study-consumer-topic}")
    private String caseStudyConsumerTopic;

    public CourierProducer(Map<Integer, KafkaTemplate<String, Object>> kafkaTemplateMap, @Qualifier("courierFactory") Factory factory) {
        this.kafkaTemplateMap = kafkaTemplateMap;
        this.factory = factory;
    }

    @PostConstruct
    public void sendCourierEvents() throws IOException {
        factory.readResource().forEach(courierEvent -> {
            kafkaTemplateMap.get("default".hashCode()).send(caseStudyConsumerTopic,courierEvent);
            log.info("Event is produced to topic : {} CourierEvent : {}", caseStudyConsumerTopic,courierEvent);
        });
    }

}