package dev.gabrieltoledo.kito.web;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import dev.gabrieltoledo.kito.application.exceptions.EmailAlreadyExistsException;
import dev.gabrieltoledo.kito.application.exceptions.dtos.ErrorResponse;
import dev.gabrieltoledo.kito.application.exceptions.dtos.ValidationErrorResponse;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Should handle EmailAlreadyExistsException")
    void handleEmailAlreadyExistsExceptionReturnsConflict() {
        EmailAlreadyExistsException exception = new EmailAlreadyExistsException();

        ErrorResponse response = handler.handleEmailAlreadyExistsException(exception);

        assertEquals(409, response.status());
        assertEquals("Email already exists", response.message());
    }

    @Test
    @DisplayName("Should handle RuntimeException")
    void handleRuntimeExceptionReturnsInternalServerError() {
        String message = "Something went wrong";
        RuntimeException exception = new RuntimeException(message);

        ErrorResponse response = handler.handleUntreatedException(exception);

        assertEquals(500, response.status());
        assertEquals(message, response.message());
    }

    @Test
    @DisplayName("Should handle MethodArgumentNotValidException")
    void handleMethodArgumentNotValidExceptionReturnsValidationErrors() {
        var target = new Object();
        var bindingResult = new BeanPropertyBindingResult(target, "request");

        bindingResult.addError(new FieldError("request", "email", "Email is required"));
        bindingResult.addError(new FieldError("request", "name", "Name is required"));

        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(null, bindingResult);

        ValidationErrorResponse response = handler.handleMethodArgumentNotValidException(exception);

        assertEquals(422, response.status());
        assertEquals(2, response.errors().size());

        List<String> fields = response.errors().stream().map(e -> e.field()).toList();
        assertTrue(fields.contains("email"));
        assertTrue(fields.contains("name"));
    }
}
