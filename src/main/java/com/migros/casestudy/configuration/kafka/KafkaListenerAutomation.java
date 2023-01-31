package com.migros.casestudy.configuration.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;
  
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerAutomation {
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
  
    public boolean startListener(String listenerId) {
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(listenerId);
        assert listenerContainer != null : false;
        listenerContainer.start();
        log.info("{} Kafka Listener Started", listenerId);
        return true;
    }
  
    public boolean stopListener(String listenerId) {
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(listenerId);
        assert listenerContainer != null : false;
        listenerContainer.stop();
        log.info("{} Kafka Listener Stopped.", listenerId);
        return true;
    }
}