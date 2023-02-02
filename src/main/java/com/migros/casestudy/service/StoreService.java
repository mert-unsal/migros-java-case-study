package com.migros.casestudy.service;

import com.migros.casestudy.domain.model.Store;
import com.migros.casestudy.factory.StoreFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private List<Store> storeList;

    private final StoreFactory storeFactory;

    @PostConstruct
    public void initializeStoreList () {
        storeList = storeFactory.readResource();
    }

    public List<Store> getStoreList() {
        return storeList;
    }

}
