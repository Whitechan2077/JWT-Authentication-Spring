package org.jwtauth.core.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException{
    private final HttpStatus httpStatus;

    private final String errorCode;

    private final String errorMessage;

    private Map<String, Object> details;

    public CustomException(HttpStatus httpStatus, String errorCode, String errorMessage, Map<String, Object> details) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.details = details;
    }
}
