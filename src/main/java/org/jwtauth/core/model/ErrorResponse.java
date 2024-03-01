package org.jwtauth.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private ErrorInfo error;

    public ErrorResponse(String code, String message, Map<String, Object> details) {
        this.error = new ErrorInfo(code, message, details);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorInfo {

        private String code;

        private String message;

        private Map<String, Object> details;

    }

}
