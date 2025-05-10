package dev.gabrieltoledo.kito.web.dtos.response;

import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(int status, String message) {
    public static ErrorResponseDTO conflictResponse(String message) {
        return new ErrorResponseDTO(HttpStatus.CONFLICT.value(), message);
    }

}
