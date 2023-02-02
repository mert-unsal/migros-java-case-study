package com.migros.casestudy.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "courier")
@SequenceGenerator(name = "courier_seq_gen", allocationSize = 1, sequenceName = "courier_seq")
public class CourierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courier_seq_gen")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name")
    private String name;

    @NotNull
    @DecimalMin(value = "-90.00", message = "latitude should not be less than -90.00")
    @DecimalMin(value = "90.00", message = "latitude should not be less than 90.00")
    @Column(name = "latitude")
    private BigDecimal latitude;

    @NotNull
    @DecimalMin(value = "-180.00", message = "Longitude should not be less than -180.00")
    @DecimalMin(value = "180.00", message = "Longitude should not be less than 180.00")
    @Column(name= "longitude")
    private BigDecimal longitude;

    @NotNull
    @Column(name= "eventTime")
    @Temporal(TemporalType.DATE)
    private Date eventTime;

}
