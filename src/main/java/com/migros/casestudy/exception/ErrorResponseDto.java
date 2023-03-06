package com.migros.casestudy.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ErrorResponseDto {
    private String code;
    private String message;
}
