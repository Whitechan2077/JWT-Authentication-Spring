package org.jwtauth.core.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.jwtauth.core.exception.CustomException;
import org.jwtauth.core.model.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<Object> handle(CustomException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage(), ex.getDetails());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), ex.getHttpStatus());
    }
    @ExceptionHandler(value = { JWTVerificationException.class })
    protected ResponseEntity<Object> handleMismatchedSignatureException(JWTVerificationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("MismatchedSignatureError",
                "The provided Algorithm doesn't match the one defined in the JWT's Header",
                null);
        log.info("Decode token fail");
        return new ResponseEntity<>(errorResponse, new HttpHeaders(),UNAUTHORIZED);
    }
}
