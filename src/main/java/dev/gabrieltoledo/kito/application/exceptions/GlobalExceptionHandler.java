package dev.gabrieltoledo.kito.application.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.gabrieltoledo.kito.web.dtos.response.ErrorResponseDTO;
import dev.gabrieltoledo.kito.web.dtos.response.FieldErrorsDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<FieldErrorsDTO> errorsList = fieldErrors
                .stream()
                .map(fe -> new FieldErrorsDTO(fe.getField(), fe.getDefaultMessage()))
                .toList();

        return new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", errorsList);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return ErrorResponseDTO.conflictResponse(e.getMessage(), null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleUserNotFoundException(UserNotFoundException e) {
        return ErrorResponseDTO.notFoundResponse(e.getMessage(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ErrorResponseDTO.badRequestResponse(e.getMostSpecificCause().getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleUntreatedException(RuntimeException e) {
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
    }

}
