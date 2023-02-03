package com.migros.casestudy.service;


import com.migros.casestudy.domain.entity.CourierEntity;
import com.migros.casestudy.domain.entity.CourierSumDistanceEntity;
import com.migros.casestudy.domain.event.CourierEvent;
import com.migros.casestudy.domain.model.Store;
import com.migros.casestudy.repository.CourierEntranceRepository;
import com.migros.casestudy.repository.CourierTravelSumRepository;
import com.migros.casestudy.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourierService {
    private final StoreService storeService;

    private final CourierEntranceRepository courierEntranceRepository;
    private final CourierTravelSumRepository courierTravelSumRepository;

    @Transactional
    public void processEvent(CourierEvent courierEvent) {
        updateCourierTravelDistance(courierEvent);
        HashMap<String, Double> closestStoreMap = closestStore(courierEvent);
        if (closestStoreMap.isEmpty()) {
            log.info("Event is not in circumference CourierEvent -> {}", courierEvent);
        } else {
            closestStoreMap.forEach((key, value) -> {
                log.info("For the Event : {}, the distance is {} for the target store is {} ", courierEvent, value, key);
                CourierEntity courier = CourierEntity
                        .builder()
                        .name(courierEvent.getName())
                        .eventTime(courierEvent.getTime())
                        .latitude(courierEvent.getLatitude())
                        .longitude(courierEvent.getLongitude())
                        .build();

                courierEntranceRepository.findFirstByNameOrderByEventTimeDesc(courierEvent.getName()).ifPresentOrElse(courierEntity -> {
                    ZonedDateTime courierEventTimeSubtracted = new Date(courierEvent.getTime()).toInstant().atZone(ZoneId.of("UTC")).minus(1, ChronoUnit.MINUTES).minus(1,ChronoUnit.MILLIS);
                    ZonedDateTime courierEntityEventTimeUTC = new Date(courierEntity.getEventTime()).toInstant().atZone(ZoneId.of("UTC"));
                    if(courierEventTimeSubtracted.isAfter(courierEntityEventTimeUTC)) {
                        log.info("Courier with name : {} has entered the migros store zone : {}",courier.getName(),key);
                        courierEntranceRepository.save(courier);
                    }
                },()-> {
                    log.info("Courier with name : {} has entered the migros store zone : {}",courier.getName(),key);
                    courierEntranceRepository.save(courier);
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
                .filter(stringDoubleEntry -> stringDoubleEntry.getValue() < 100)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, HashMap::new));
    }

    @Transactional
    public void updateCourierTravelDistance(CourierEvent courierEvent) {
        CourierSumDistanceEntity courierSumDistanceEntity = CourierSumDistanceEntity
                .builder()
                .courierName(courierEvent.getName())
                .lastLatitude(courierEvent.getLatitude())
                .lastLongitude(courierEvent.getLongitude())
                .sumDistance(BigDecimal.ZERO)
                .build();

        courierTravelSumRepository.findByCourierName(courierEvent.getName()).ifPresent(courierSumDistanceEntity1 -> {
            courierSumDistanceEntity.setId(courierSumDistanceEntity1.getId());
            courierSumDistanceEntity.setSumDistance(BigDecimal.valueOf(DistanceCalculator.distance(
                    courierEvent.getLatitude().doubleValue(),
                    courierSumDistanceEntity1.getLastLatitude().doubleValue(),
                    courierEvent.getLongitude().doubleValue(),
                    courierSumDistanceEntity1.getLastLongitude().doubleValue())));
            courierSumDistanceEntity.setLastLatitude(courierEvent.getLatitude());
            courierSumDistanceEntity.setLastLongitude(courierEvent.getLongitude());
        });
        courierTravelSumRepository.save(courierSumDistanceEntity);
    }

}
