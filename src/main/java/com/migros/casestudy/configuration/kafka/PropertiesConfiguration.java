package com.migros.casestudy.configuration.kafka;

import com.migros.casestudy.domain.model.ConsumerConfigDto;
import com.migros.casestudy.domain.model.ProducerConfigDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class PropertiesConfiguration {
    public ConsumerConfigDto caseStudyConsumer;
    public ProducerConfigDto caseStudyProducer;

    public PropertiesConfiguration() {
    }
}
