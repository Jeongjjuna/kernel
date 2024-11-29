package org.yjh.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(fieldError -> String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ErrorResponse.of(
                errors.toString(),
                LocalDateTime.now()
        );
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return ErrorResponse.of(
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
        log.error(e.getMessage());
        return ErrorResponse.of(
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(BaseException.class)
    public ErrorResponse handleBaseException(BaseException e) {
        log.error(e.getMessage());
        return ErrorResponse.of(
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(BaseException.class)
    public String handleBaseException(BaseException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error(e.getMessage());
        return ErrorResponse.of(
                INTERNAL_SERVER_ERROR.name(),
                LocalDateTime.now()
        );
    }
}
