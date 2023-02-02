package com.migros.casestudy.service;

import com.migros.casestudy.domain.model.Store;
import com.migros.casestudy.factory.Factory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class StoreService {

    private List<Store> storeList;

    private final Factory factory;

    public StoreService(@Qualifier("storeFactory") Factory factory) {
        this.factory = factory;
    }

    @PostConstruct
    public void initializeStoreList() {
        storeList = factory.readResource();
    }

    public List<Store> getStoreList() {
        return storeList;
    }

}
