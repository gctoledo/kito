package dev.gabrieltoledo.kito.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.gabrieltoledo.kito.web.dtos.response.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return ErrorResponseDTO.conflictResponse(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleUntreatedException(RuntimeException e) {
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
