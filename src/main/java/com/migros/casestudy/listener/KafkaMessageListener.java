package com.migros.casestudy.listener;

import com.migros.casestudy.domain.event.CourierEvent;
import com.migros.casestudy.service.CourierService;
import com.munsal.kafkaconfiguration.annotation.DependsOnKafkaConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Component
@DependsOnKafkaConfiguration
public class KafkaMessageListener {
    private final CourierService courierService;

    @Value("#{kafkaConfiguration.consumers['case-study-consumer'].topic}")
    private String value;

    @Value("#{kafkaConfiguration.consumers['case-study-consumer'].props['group.id']}")
    private String value2;

    @KafkaListener(
            topics = "#{kafkaConfiguration.consumers['case-study-consumer'].topic}",
            groupId = "#{kafkaConfiguration.consumers['case-study-consumer'].props['group.id']}",
            containerFactory = "#{kafkaListenerContainerFactoryMap['case-study-consumer']}")
    public void consumeMessage(CourierEvent courierEvent) {
        try {
            log.info("Event is received by KafkaMessageListener : CourierEvent -> {}", courierEvent);
            courierService.processEvent(courierEvent);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PostConstruct()
    public void display() {
        System.out.println(value);
        System.out.println(value2);
    }
}