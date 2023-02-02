package com.migros.casestudy.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.casestudy.domain.event.CourierEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierFactory implements Factory {

    @Value("classpath:data/courier-dummy-information.json")
    Resource resourceFile;

    private final ObjectMapper objectMapper;

    @Override
    public List<CourierEvent> readResource() {
        List<CourierEvent> courierEvents = new ArrayList<>();
        try {
            courierEvents.addAll(objectMapper.readValue(resourceFile.getFile(), new TypeReference<List<CourierEvent>>() {}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courierEvents;
    }
}
