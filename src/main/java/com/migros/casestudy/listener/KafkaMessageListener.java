package com.migros.casestudy.listener;

import com.migros.casestudy.domain.event.CourierEvent;
import com.migros.casestudy.exception.DomainNotFoundException;
import com.migros.casestudy.service.CourierService;
import com.munsal.kafkaconfiguration.annotation.DependsOnKafkaConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.migros.casestudy.exception.ErrorCode.CUSTOMER_COULD_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Component
@DependsOnKafkaConfiguration
public class KafkaMessageListener {
    private final CourierService courierService;

    @KafkaListener(
            topics = "#{kafkaConfiguration.consumers['case-study-consumer'].topic}",
            groupId = "#{kafkaConfiguration.consumers['case-study-consumer'].props['group.id']}",
            containerFactory = "#{kafkaListenerContainerFactoryMap['case-study-consumer']}")
    public void consumeMessage(CourierEvent courierEvent) {
        try {
            log.info("Event is received by KafkaMessageListener : CourierEvent -> {}", courierEvent);
//            courierService.processEvent(courierEvent);
            throw new DomainNotFoundException(CUSTOMER_COULD_NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

}