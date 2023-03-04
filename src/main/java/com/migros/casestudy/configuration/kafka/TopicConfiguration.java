//package com.migros.casestudy.configuration.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//public class TopicConfiguration {
////    private final PropertiesConfiguration kafkaPropertiesConfiguration;
//
//    //TODO: Lets conver Topic builder part on library if its possible
//    @Bean("topics")
//    public Map<String, NewTopic> topic() {
//        HashMap<String, NewTopic> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("case-study-consumer", TopicBuilder.name("migros.case.study.courier.topic")
//                .partitions(10)
//                .replicas(1)
//                .build());
//        return objectObjectHashMap;
//    }
//}
