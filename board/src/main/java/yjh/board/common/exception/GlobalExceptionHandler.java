package yjh.board.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yjh.board.common.response.ErrorApi;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorApi handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(fieldError -> String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ErrorApi.of(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                errors
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorApi handleNoSuchElementException(Exception e) {
        log.error(e.getMessage());

        return ErrorApi.of(
                NOT_FOUND.value(),
                LocalDateTime.now(),
                List.of(e.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorApi handleException(Exception e) {
        log.error(e.getMessage());

        return ErrorApi.of(
                INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                List.of(INTERNAL_SERVER_ERROR.getReasonPhrase())
        );
    }
}
