//package com.migros.casestudy.configuration.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//@Configuration
//@RequiredArgsConstructor
//public class ProducerConfiguration {
//
//    private final PropertiesConfiguration kafkaPropertiesConfiguration;
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(kafkaPropertiesConfiguration.caseStudyProducer.getProps());
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
//
//
//}