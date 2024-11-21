package org.yjh.common.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String errorMessage;
    private final String timestamp;

    private ErrorResponse(String errorMessage, String timestamp) {
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public static ErrorResponse of(String errorMessage, LocalDateTime localDateTime) {
        return new ErrorResponse(errorMessage, localDateTime.toString());
    }
}
