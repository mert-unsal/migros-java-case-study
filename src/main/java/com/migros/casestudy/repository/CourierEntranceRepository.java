package com.migros.casestudy.repository;

import com.migros.casestudy.domain.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierEntranceRepository extends JpaRepository<CourierEntity, Long> {
    Optional<CourierEntity> findFirstByNameOrderByEventTimeDesc(String courierName);
}
