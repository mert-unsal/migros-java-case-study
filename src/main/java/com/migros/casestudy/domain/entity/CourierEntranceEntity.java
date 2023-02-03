package com.migros.casestudy.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "courier_entrance")
@SequenceGenerator(name = "courier_entrance_seq_gen", allocationSize = 1, sequenceName = "courier_entrance_seq")
public class CourierEntranceEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courier_seq_gen")
    private Long id;

    @JoinColumn(name = "courier_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CourierEntity courier;

    @NotNull
    @DecimalMin(value = "-90.00", message = "latitude should not be less than -90.00")
    @DecimalMax(value = "90.00", message = "latitude should not be less than 90.00")
    @Column(name = "latitude")
    private BigDecimal latitude;

    @NotNull
    @DecimalMin(value = "-180.00", message = "Longitude should not be less than -180.00")
    @DecimalMax(value = "180.00", message = "Longitude should not be less than 180.00")
    @Column(name= "longitude")
    private BigDecimal longitude;

    @NotNull
    @Column(name= "eventTime")
    private Long eventTime;

}
