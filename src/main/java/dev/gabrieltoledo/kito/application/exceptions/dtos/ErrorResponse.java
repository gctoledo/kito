package dev.gabrieltoledo.kito.application.exceptions.dtos;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int status, String message) {

    public static ErrorResponse badRequestResponse(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static ErrorResponse conflictResponse(String message) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message);
    }

    public static ErrorResponse notFoundResponse(String message) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), message);
    }
}
