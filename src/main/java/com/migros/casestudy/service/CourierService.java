package com.migros.casestudy.service;


import com.migros.casestudy.domain.entity.CourierEntity;
import com.migros.casestudy.domain.event.CourierEvent;
import com.migros.casestudy.domain.model.Store;
import com.migros.casestudy.repository.CourierRepository;
import com.migros.casestudy.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourierService {
    private final StoreService storeService;

    private final CourierRepository courierRepository;

    @Transactional
    public void processEvent(CourierEvent courierEvent) {
        HashMap<String, Double> closestStoreMap = closestStore(courierEvent);
        if (closestStoreMap.isEmpty()) {
            log.info("Event is not in circumference CourierEvent -> {}", courierEvent);
        } else {
            closestStoreMap.forEach((key, value) -> {
                log.info("For the Event : {}, the distance is {} for the target store is {} ", courierEvent, value, key);
                CourierEntity courier = CourierEntity
                        .builder()
                        .name(courierEvent.getName())
                        .eventTime(new Date(courierEvent.getTime()))
                        .latitude(courierEvent.getLatitude())
                        .longitude(courierEvent.getLongitude())
                        .build();

                courierRepository.findByName(key).ifPresentOrElse(courierEntity -> {
                    Instant instant = new Date(courierEvent.getTime()).toInstant();
                    Instant courierEventTimeSubtracted = instant.minus(1, ChronoUnit.MINUTES);
                    if(courierEventTimeSubtracted.isAfter(courierEntity.getEventTime().toInstant())) {
                        log.info("Courier with name : {} has entered the migros store zone : {}",courier.getName(),key);
                        courierRepository.save(courier);
                    }
                },()-> {
                    log.info("Courier with name : {} has entered the migros store zone : {}",courier.getName(),key);
                    courierRepository.saveAndFlush(courier);
                });
            });
        }
    }

    private HashMap<String, Double> closestStore(CourierEvent courierEvent) {
        return storeService
                .getStoreList()
                .stream()
                .collect(Collectors.toMap(Store::getName,
                        store -> DistanceCalculator.distance(
                                courierEvent.getLatitude().doubleValue(),
                                store.getLat().doubleValue(),
                                courierEvent.getLongitude().doubleValue(),
                                store.getLng().doubleValue())))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .filter(stringDoubleEntry -> stringDoubleEntry.getValue() < 100)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, HashMap::new));
    }
}
