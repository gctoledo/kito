package dev.gabrieltoledo.kito.application.exceptions.dtos;

import java.util.List;

public record ValidationErrorResponse(int status, List<FieldErrors> errors) {
}
