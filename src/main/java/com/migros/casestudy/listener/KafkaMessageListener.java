package com.migros.casestudy.listener;

import com.migros.casestudy.domain.event.CourierEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class KafkaMessageListener {
    @KafkaListener(id = "courierConsumer1", groupId = "${kafka-config.case-study-consumer.props.[group.id]}",containerFactory = "kafkaListenerContainerFactory", topics = "#{topics['case-study-consumer'].name}")
    public void consumeMessage(CourierEvent courierEvent) {
        log.info("Message received : -> {}", courierEvent);
    }
}