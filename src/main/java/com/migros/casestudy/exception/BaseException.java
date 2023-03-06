package com.migros.casestudy.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Getter
public class BaseException extends ResponseStatusException {
    private HttpStatus status;
    private String code;
    private Object[] messageParams;
    private final Optional<Exception> maybeCause;

    public BaseException(HttpStatus status, String code) {
        super(status, code);
        this.code = code;
        this.status = status;
        this.maybeCause = Optional.empty();
    }

    public BaseException(HttpStatus status, String code, Exception t) {
        super(status, code);
        this.code = code;
        this.status = status;
        this.maybeCause = Optional.of(t);
    }

    public BaseException(HttpStatus status, String code, Object[] params){
        super(status, code);
        this.code = code;
        this.status = status;
        this.messageParams = params;
        this.maybeCause = Optional.empty();
    }
}