package com.migros.casestudy.repository;

import com.migros.casestudy.domain.entity.CourierEntranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierEntranceRepository extends JpaRepository<CourierEntranceEntity, Long> {
    Optional<CourierEntranceEntity> findFirstByCourier_IdOrderByEventTimeDesc(Long courierId);
}
