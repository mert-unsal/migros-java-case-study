package com.migros.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.migros.casestudy.service.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(CourierController.BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class CourierController {
    public static final String BASE_PATH = "courier";
    private static final String TAG = "Courier Controllers";
    private final CourierService courierService;

    @GetMapping("totalDistance")
    @Operation(tags = TAG, summary = "get total distance")
    public Double getTotalTravelDistance(@RequestParam Long courierId) {
        return courierService.getTotalTravelDistance(courierId);
    }


}
