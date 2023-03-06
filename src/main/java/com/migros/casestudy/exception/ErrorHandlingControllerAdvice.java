package com.migros.casestudy.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class ErrorHandlingControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(DomainNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ErrorResponseDto> customerNotFoundException(DomainNotFoundException domainNotFoundException, Locale locale) {
        return getApiError(domainNotFoundException, locale);
    }

    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ErrorResponseDto> insufficientStockException(InsufficientStockException insufficientStockException, Locale locale) {
        return getApiError(insufficientStockException, locale);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ErrorResponseDto> onConstraintValidationException(DataIntegrityViolationException dataIntegrityViolationException, Locale locale) {
        BaseException generalException = new BaseException(HttpStatus.BAD_REQUEST, "9001", dataIntegrityViolationException);
        return getApiError(generalException, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ErrorResponseDto> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponseDto.ErrorResponseDtoBuilder builder = ErrorResponseDto.builder();
        if (!CollectionUtils.isEmpty(e.getAllErrors())) {
            builder.message(e.getAllErrors().stream().findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("Unknown validation exception"));
            builder.code("6000");
        }
        logError(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.build());
    }



    private ResponseEntity<ErrorResponseDto> getApiError(BaseException e, Locale locale) {
        HttpStatus status = e.getStatus();
        ErrorResponseDto.ErrorResponseDtoBuilder errorResponseDtoBuilder = ErrorResponseDto.builder();
        errorResponseDtoBuilder.message(messageSource.getMessage(e.getCode(), e.getMessageParams(), locale));
        errorResponseDtoBuilder.code(e.getCode());
        logError(e.getMaybeCause().isPresent() ? e.getMaybeCause().get() : e);
        return ResponseEntity.status(status).body(errorResponseDtoBuilder.build());
    }

    private void logError(Exception e) {
        log.error("Error occurred. Message: {}", e.getMessage(), e);
    }
}