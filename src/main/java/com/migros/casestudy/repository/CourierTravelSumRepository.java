package com.migros.casestudy.repository;

import com.migros.casestudy.domain.entity.CourierSumDistanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierTravelSumRepository extends JpaRepository<CourierSumDistanceEntity, Long> {
    Optional<CourierSumDistanceEntity> findByCourier_Id(Long courierId);
}
