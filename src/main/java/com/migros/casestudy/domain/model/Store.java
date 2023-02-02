package com.migros.casestudy.domain.model;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Store {
    private String name;
    private BigDecimal lat;
    private BigDecimal lng;
}
