package com.migros.casestudy.repository;

import com.migros.casestudy.domain.entity.CourierEntity;
import com.migros.casestudy.domain.entity.CourierEntranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourierRepository extends JpaRepository<CourierEntity, Long> {
    List<CourierEntity> findAllByName(String courierName);
    Optional<CourierEntity> findById(Long courierId);
}
