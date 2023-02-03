package com.migros.casestudy.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.casestudy.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("storeFactory")
@RequiredArgsConstructor
public class StoreFactory implements Factory {

//    @Value("classpath:data/stores.json")
//    private Resource resourceFile;

    private final ObjectMapper objectMapper;

    @Override
    public List<Store> readResource() throws IOException {
        String contents;
        try (InputStream inputStream = getClass().getResourceAsStream("/data/stores.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            contents = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        List<Store> storeList = new ArrayList<>();
        try {
            storeList.addAll(objectMapper.readValue(contents, new TypeReference<List<Store>>() {}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storeList;
    }
}