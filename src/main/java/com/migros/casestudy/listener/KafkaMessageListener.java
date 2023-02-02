package com.migros.casestudy.listener;

import com.migros.casestudy.domain.event.CourierEvent;
import com.migros.casestudy.service.CourierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaMessageListener {
    private final CourierService courierService;

    @KafkaListener(id = "courierConsumer1", groupId = "${kafka-config.case-study-consumer.props.[group.id]}",containerFactory = "kafkaListenerContainerFactory", topics = "#{topics['case-study-consumer'].name}")
    public void consumeMessage(CourierEvent courierEvent) {
        try{
            log.info("Event is received by KafkaMessageListener : CourierEvent -> {}", courierEvent);
            courierService.processEvent(courierEvent);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}