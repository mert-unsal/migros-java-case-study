package com.migros.casestudy.domain.event;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CourierEvent {
    private Long courierId;
    private Long time;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
