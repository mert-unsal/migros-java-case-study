package com.migros.casestudy.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.casestudy.domain.event.CourierEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourierFactory implements Factory {

//    @Value("classpath:data/courier-dummy-information.json")
//    Resource resourceFile;

    private final ObjectMapper objectMapper;

    @Override
    public List<CourierEvent> readResource() throws IOException {
        String contents;
        try (InputStream inputStream = getClass().getResourceAsStream("/data/courier-dummy-information.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            contents = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        List<CourierEvent> courierEvents = new ArrayList<>();
        try {
            courierEvents.addAll(objectMapper.readValue(contents, new TypeReference<List<CourierEvent>>() {}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courierEvents;
    }
}
