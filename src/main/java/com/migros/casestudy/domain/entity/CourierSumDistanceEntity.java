package com.migros.casestudy.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "courier_sum")
@SequenceGenerator(name = "courier_sum_seq_gen", allocationSize = 1, sequenceName = "courier_sum_seq")
public class CourierSumDistanceEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courier_sum_seq_gen")
    private Long id;

    @JoinColumn(name = "courier_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CourierEntity courier;

    @NotNull
    @DecimalMin(value = "-90.00", message = "latitude should not be less than -90.00")
    @DecimalMax(value = "90.00", message = "latitude should not be less than 90.00")
    @Column(name = "last_latitude")
    private BigDecimal lastLatitude;

    @NotNull
    @DecimalMin(value = "-180.00", message = "Longitude should not be less than -180.00")
    @DecimalMax(value = "180.00", message = "Longitude should not be less than 180.00")
    @Column(name= "last_longitude")
    private BigDecimal lastLongitude;

    @Column(name = "sum_distance")
    private BigDecimal sumDistance;

}
