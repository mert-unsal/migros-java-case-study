package com.migros.casestudy.domain.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConsumerConfigDto {
    private String topic;
    private String retryTopic;
    private String errorTopic;
    private String dataClass;
    private String partitions;
    private String replicas;
    private Map<String, Object> props;
}
