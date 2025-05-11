package dev.gabrieltoledo.kito.web.dtos.response;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(int status, String message, List<FieldErrorsDTO> errors) {

    public static ErrorResponseDTO badRequestResponse(String message, List<FieldErrorsDTO> errors) {
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, errors);
    }

    public static ErrorResponseDTO conflictResponse(String message, List<FieldErrorsDTO> errors) {
        return new ErrorResponseDTO(HttpStatus.CONFLICT.value(), message, errors);
    }

    public static ErrorResponseDTO notFoundResponse(String message, List<FieldErrorsDTO> errors) {
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), message, errors);
    }

}
