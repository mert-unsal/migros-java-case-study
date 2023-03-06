package com.migros.casestudy.exception;

import org.springframework.http.HttpStatus;

public class InsufficientStockException extends BaseException {
    public InsufficientStockException(String code) {
        super(HttpStatus.BAD_REQUEST, code);
    }
}
