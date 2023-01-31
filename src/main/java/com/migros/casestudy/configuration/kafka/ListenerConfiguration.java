package com.migros.casestudy.configuration.kafka;

import com.migros.casestudy.domain.event.CourierEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class ListenerConfiguration {

    private final PropertiesConfiguration kafkaPropertiesConfiguration;

    @Bean("consumerFactory")
    public ConsumerFactory<String, CourierEvent> consumerFactory() {
        JsonDeserializer<CourierEvent> deserializer = new JsonDeserializer<>(CourierEvent.class, false);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(kafkaPropertiesConfiguration.caseStudyConsumer.getProps(), new StringDeserializer(), deserializer);
    }

    @Bean("kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CourierEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CourierEvent> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory());
        return containerFactory;
    }

}