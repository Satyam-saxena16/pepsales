package com.example.pepsales.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a request is invalid or malformed
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidRequestException(String message) {
        super(message);
    }
    
    public InvalidRequestException(String requestPart, String issue, Object value) {
        super(String.format("Invalid request: %s has issue '%s' with value: '%s'", requestPart, issue, value));
    }
}