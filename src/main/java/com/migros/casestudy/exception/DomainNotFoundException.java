package com.migros.casestudy.exception;

import org.springframework.http.HttpStatus;

public class DomainNotFoundException extends BaseException {
    public <T> DomainNotFoundException(String code, T id) {
        super(HttpStatus.BAD_REQUEST, code, new Object[]{id});
    }
    public <T> DomainNotFoundException(String code) {
        super(HttpStatus.BAD_REQUEST, code, new Object[]{});
    }
}
