package com.migros.casestudy.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.casestudy.domain.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("storeFactory")
@RequiredArgsConstructor
public class StoreFactory implements Factory {

    @Value("classpath:data/stores.json")
    private Resource resourceFile;

    private final ObjectMapper objectMapper;

    @Override
    public List<Store> readResource() {
        List<Store> storeList = new ArrayList<>();
        try {
            storeList.addAll(objectMapper.readValue(resourceFile.getFile(), new TypeReference<List<Store>>() {}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storeList;
    }
}